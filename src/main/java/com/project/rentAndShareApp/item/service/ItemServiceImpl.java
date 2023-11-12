package com.project.rentAndShareApp.item.service;

import com.project.rentAndShareApp.booking.entity.Booking;
import com.project.rentAndShareApp.booking.mapper.BookingMapper;
import com.project.rentAndShareApp.booking.repository.BookingRepository;
import com.project.rentAndShareApp.exception.NotFoundException;
import com.project.rentAndShareApp.item.dto.ItemWithBookingInfoDto;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.item.mapper.ItemMapper;
import com.project.rentAndShareApp.item.repository.ItemRepository;
import com.project.rentAndShareApp.user.repository.UserRepository;
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
    private final ItemMapper itemMapper;
    private final BookingMapper bookingMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           UserRepository userRepository,
                           BookingRepository bookingRepository,
                           ItemMapper itemMapper,
                           BookingMapper bookingMapper) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.itemMapper = itemMapper;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public List<ItemWithBookingInfoDto> getListItemsByOwnerId(Long ownerId) {
        log.info("ItemService: getListItemsForUserId(): start with userId={}", ownerId);
        List<Item> items = itemRepository.getItemsByOwnerId(ownerId);
        List<ItemWithBookingInfoDto> itemsDtoList = new ArrayList<>();

        for (Item item : items) {
            Booking lastBooking = bookingRepository.getFirstByItemIdAndEndBeforeOrderByEndDesc(
                    item.getId(), LocalDateTime.now());
            Booking nextBooking = bookingRepository.getFirstByItemIdAndStartAfterOrderByEnd(
                    item.getId(), LocalDateTime.now());

            if (lastBooking != null && nextBooking != null) {
                itemsDtoList.add(itemMapper.toItemWithBookingDto(
                        item,
                        bookingMapper.toShortBookingDto(lastBooking),
                        bookingMapper.toShortBookingDto(nextBooking))
                );
            } else {
                itemsDtoList.add(itemMapper.toItemWithBookingDto(item, null, null));
            }
        }

        return itemsDtoList;
    }

    @Override
    public Item addItem(Item item) {
        log.info("ItemService: addItem(): start with item: '{}'", item);
        Long userId = item.getOwner().getId();

        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        item.setOwner(userRepository.getReferenceById(userId));

        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        log.info("ItemService: updateItem(): start item={}", item);
        Long itemId = item.getId();
        Long userId = item.getOwner().getId();

        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("item with id=" + itemId + " not found");
        }

        Item existItem = itemRepository.getReferenceById(itemId);

        if (!existItem.getOwner().getId().equals(userId)) {
            throw new NotFoundException("user with id=" + userId + "not owner for item with id=" + itemId);
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

        return itemRepository.save(existItem);
    }

    @Override
    public ItemWithBookingInfoDto getItemByIdAndUserId(Long itemId, Long userId) {
        log.info("ItemService: getItemById(): start with itemId = {} and userId={}", itemId, userId);
        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("item with id=" + itemId + " not found");
        }

        Item item = itemRepository.getReferenceById(itemId);
        ItemWithBookingInfoDto itemDto = itemMapper.toItemWithBookingDto(item, null, null);;

        if (item.getOwner().getId().equals(userId)) {
            Booking lastBooking = bookingRepository.getFirstByItemIdAndEndBeforeOrderByEndDesc(
                    item.getId(), LocalDateTime.now());
            Booking nextBooking = bookingRepository.getFirstByItemIdAndStartAfterOrderByEnd(
                    item.getId(), LocalDateTime.now());

            if (lastBooking != null && nextBooking != null) {
                itemDto = itemMapper.toItemWithBookingDto(
                        item,
                        bookingMapper.toShortBookingDto(lastBooking),
                        bookingMapper.toShortBookingDto(nextBooking)
                );
            }
        }

        return itemDto;
    }

    @Override
    public List<Item> searchAvailableItemBySubstring(String substring) {
        log.info("ItemService: getItemById(): start with substring = '{}'", substring);
        List<Item> items = new ArrayList<>();
        String s = substring.toUpperCase();

        if (!substring.isBlank()) {
            items = itemRepository.getByNameIgnoreCaseOrDescriptionIgnoreCaseContainingAndAvailableIsTrueOrderById(s, s);
        }

        return items;
    }
}