package com.project.item.service;

import com.project.booking.entity.Booking;
import com.project.booking.entity.BookingStatus;
import com.project.booking.mapper.BookingMapper;
import com.project.booking.repository.BookingRepository;
import com.project.exception.CommentWithoutBookingException;
import com.project.exception.NotFoundException;
import com.project.item.dto.*;
import com.project.item.entity.Comment;
import com.project.item.entity.Item;
import com.project.item.mapper.CommentMapper;
import com.project.item.mapper.ItemMapper;
import com.project.item.repository.CommentRepository;
import com.project.item.repository.ItemRepository;
import com.project.user.entity.User;
import com.project.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final CommentRepository commentRepository;
    private final ItemMapper itemMapper;
    private final BookingMapper bookingMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           UserRepository userRepository,
                           BookingRepository bookingRepository,
                           CommentRepository commentRepository,
                           ItemMapper itemMapper,
                           BookingMapper bookingMapper,
                           CommentMapper commentMapper) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.commentRepository = commentRepository;
        this.itemMapper = itemMapper;
        this.bookingMapper = bookingMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<ItemWithBookingCommentInfoDto> getListItemsByOwnerId(Long ownerId) {
        log.info("ItemService: getListItemsForUserId(): start with ownerId={}", ownerId);
        List<Item> items = itemRepository.getItemsByOwnerIdOrderById(ownerId);
        List<ItemWithBookingCommentInfoDto> itemsDtoList = new ArrayList<>();

        for (Item item : items) {
            Booking lastBooking = bookingRepository.getFirstByItemIdAndStartBeforeAndStatusNotOrderByEndDesc(
                    item.getId(), LocalDateTime.now(), BookingStatus.REJECTED);
            Booking nextBooking = bookingRepository.getFirstByItemIdAndStartAfterAndStatusNotOrderByEnd(
                    item.getId(), LocalDateTime.now(), BookingStatus.REJECTED);

            List<Comment> comments = commentRepository.findByItemId(item.getId());
            List<CommentResponseDto> commentDtoList = new ArrayList<>();

            for (Comment comment : comments) {
                commentDtoList.add(commentMapper.toCommentDto(comment));
            }

            if (lastBooking != null && nextBooking != null) {
                itemsDtoList.add(itemMapper.toItemWithBookingCommentInfoDto(
                        item,
                        bookingMapper.toShortBookingDto(lastBooking),
                        bookingMapper.toShortBookingDto(nextBooking),
                        commentDtoList
                        )
                );
            } else if (lastBooking != null) {
                itemsDtoList.add(itemMapper.toItemWithBookingCommentInfoDto(
                        item,
                        bookingMapper.toShortBookingDto(lastBooking),
                        null,
                        commentDtoList
                        )
                );
            } else if (nextBooking != null) {
                itemsDtoList.add(itemMapper.toItemWithBookingCommentInfoDto(
                        item,
                        null,
                        bookingMapper.toShortBookingDto(nextBooking),
                        commentDtoList
                        )
                );
            } else {
                itemsDtoList.add(itemMapper.toItemWithBookingCommentInfoDto(
                        item, null, null, commentDtoList)
                );
            }
        }

        return itemsDtoList;
    }

    @Override
    public ItemResponseDto addItem(ItemRequestDto itemDto, Long ownerId) {
        log.info("ItemService: addItem(): start with ownerId={} and itemDto='{}'", ownerId, itemDto);
        if (!userRepository.existsById(ownerId)) {
            throw new NotFoundException("user with id=" + ownerId + " not found");
        }

        Item item = itemMapper.toItem(itemDto, null, ownerId);

        item.setOwner(userRepository.getReferenceById(ownerId));

        return itemMapper.toItemDto(itemRepository.save(item));
    }

    @Override
    public ItemResponseDto updateItem(ItemRequestDto itemDto, Long itemId, Long ownerId) {
        log.info("ItemService: updateItem(): start with ownerId={} and itemId={}", ownerId, itemId);
        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("item with id=" + itemId + " not found");
        }

        Item item = itemMapper.toItem(itemDto, itemId, ownerId);
        Item existItem = itemRepository.getReferenceById(itemId);

        if (!existItem.getOwner().getId().equals(ownerId)) {
            throw new NotFoundException("user with id=" + ownerId + "not owner for item with id=" + itemId);
        }

        if (item.getName() != null) {
            existItem.setName(item.getName());
        }

        if (item.getDescription() != null) {
            existItem.setDescription(item.getDescription());
        }

        if (item.getAvailable() != null) {
            existItem.setAvailable(item.getAvailable());
        }

        return itemMapper.toItemDto(itemRepository.save(existItem));
    }

    @Override
    public ItemWithBookingCommentInfoDto getItemById(Long itemId, Long userId) {
        log.info("ItemService: getItemById(): start with itemId={} and userId={}", itemId, userId);
        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("item with id=" + itemId + " not found");
        }

        Item item = itemRepository.getReferenceById(itemId);

        List<Comment> comments = commentRepository.findByItemId(itemId);
        List<CommentResponseDto> commentDtoList = new ArrayList<>();

        for (Comment comment : comments) {
            commentDtoList.add(commentMapper.toCommentDto(comment));
        }

        ItemWithBookingCommentInfoDto itemDto = itemMapper.toItemWithBookingCommentInfoDto(
                item, null, null, commentDtoList);

        if (item.getOwner().getId().equals(userId)) {
            Booking lastBooking = bookingRepository.getFirstByItemIdAndStartBeforeAndStatusNotOrderByEndDesc(
                    item.getId(), LocalDateTime.now(), BookingStatus.REJECTED);
            Booking nextBooking = bookingRepository.getFirstByItemIdAndStartAfterAndStatusNotOrderByEnd(
                    item.getId(), LocalDateTime.now(), BookingStatus.REJECTED);

            if (lastBooking != null && nextBooking != null) {
                itemDto = itemMapper.toItemWithBookingCommentInfoDto(
                                item,
                                bookingMapper.toShortBookingDto(lastBooking),
                                bookingMapper.toShortBookingDto(nextBooking),
                                commentDtoList
                );
            } else if (lastBooking != null) {
                itemDto = itemMapper.toItemWithBookingCommentInfoDto(
                                item,
                                bookingMapper.toShortBookingDto(lastBooking),
                                null,
                                commentDtoList
                );
            } else if (nextBooking != null) {
                itemDto = itemMapper.toItemWithBookingCommentInfoDto(
                                item,
                                null,
                                bookingMapper.toShortBookingDto(nextBooking),
                                commentDtoList
                );
            }
        }

        return itemDto;
    }

    @Override
    public List<ItemResponseDto> searchAvailableItemsBySubstring(String substring) {
        log.info("ItemService: searchAvailableItemsBySubstring(): start with substring='{}'", substring);
        if (substring.isBlank()) {
            return new ArrayList<>();
        }

        List<ItemResponseDto> itemDtoList = new ArrayList<>();
        String s = substring.toUpperCase();
        List<Item> items = itemRepository.
                getByNameIgnoreCaseOrDescriptionIgnoreCaseContainingAndAvailableIsTrueOrderById(s, s);

        for (Item item : items) {
            itemDtoList.add(itemMapper.toItemDto(item));
        }

        return itemDtoList;
    }

    @Override
    public CommentResponseDto addComment(CommentRequestDto commentDto, Long itemId, Long userId) {
        log.info("ItemService: addComment(): start with commentDto='{}', itemId={} and userId={}",
                commentDto, itemId, userId);

        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("item with id=" + itemId + " not found");
        }

        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        Item item = itemRepository.getReferenceById(itemId);
        User user = userRepository.getReferenceById(userId);

        Comment comment = commentMapper.toComment(commentDto, item, user);
        Booking booking = bookingRepository.getReferenceById(userId);

        if (!booking.getItem().getId().equals(itemId) && !booking.getEnd().isBefore(LocalDateTime.now())) {
            throw new CommentWithoutBookingException("user with id=" + userId +
                    " can not create comment for item with id=" + itemId + " with out booking");
        }

        return commentMapper.toCommentDto(commentRepository.save(comment));
    }
}