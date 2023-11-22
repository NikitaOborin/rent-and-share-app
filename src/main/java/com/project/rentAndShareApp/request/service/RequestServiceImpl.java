package com.project.rentAndShareApp.request.service;

import com.project.rentAndShareApp.exception.NotFoundException;
import com.project.rentAndShareApp.item.dto.ItemWithRequestInfoDto;
import com.project.rentAndShareApp.item.entity.Item;
import com.project.rentAndShareApp.item.mapper.ItemMapper;
import com.project.rentAndShareApp.item.repository.ItemRepository;
import com.project.rentAndShareApp.request.dto.RequestRequestDto;
import com.project.rentAndShareApp.request.dto.RequestResponseDto;
import com.project.rentAndShareApp.request.dto.RequestWithItemInfoDto;
import com.project.rentAndShareApp.request.entity.Request;
import com.project.rentAndShareApp.request.mapper.RequestMapper;
import com.project.rentAndShareApp.request.repository.RequestRepository;
import com.project.rentAndShareApp.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RequestServiceImpl implements RequestService {
    private final RequestMapper requestMapper;
    private final ItemMapper itemMapper;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public RequestServiceImpl(RequestMapper requestMapper,
                              ItemMapper itemMapper,
                              UserRepository userRepository,
                              RequestRepository requestRepository,
                              ItemRepository itemRepository) {
        this.requestMapper = requestMapper;
        this.itemMapper = itemMapper;
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public RequestResponseDto addRequest(RequestRequestDto requestDto, Long userId) {
        log.info("RequestService: addRequest(): start with userId={} and requestDto='{}'", userId, requestDto);
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        Request request = requestMapper.toRequest(requestDto, userId, LocalDateTime.now());

        return requestMapper.toRequestDto(requestRepository.save(request));
    }

    @Override
    public List<RequestWithItemInfoDto> getRequests(Long userId) {
        log.info("RequestService: getRequests(): start with userId={}", userId);
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        List<Request> requests = requestRepository.getByRequesterIdOrderByCreatedDesc(userId);
        List<RequestWithItemInfoDto> requestListDto = new ArrayList<>();

        for (Request request : requests) {
            List<Item> items = itemRepository.getByRequestId(request.getId());
            List<ItemWithRequestInfoDto> itemListDto = new ArrayList<>();

            for (Item item : items) {
                itemListDto.add(itemMapper.toItemWithRequestInfoDto(item));
            }

            requestListDto.add(requestMapper.toRequestWithItemInfoDto(request, itemListDto));

        }

        return requestListDto;

    }

    @Override
    public List<RequestWithItemInfoDto> getAllRequests(Long userId, Integer from, Integer size) {
        log.info("RequestService: getAllRequests(): start with userId={}, from={} and size={}", userId, from, size);
        Sort sortByCreated = Sort.by(Sort.Direction.DESC, "created");
        Pageable pageRequest = PageRequest.of(from / size, size, sortByCreated);

        List<Request> requests = requestRepository.getByRequesterIdNot(userId, pageRequest);
        List<RequestWithItemInfoDto> requestListDto = new ArrayList<>();

        for (Request request : requests) {
            List<Item> items = itemRepository.getByRequestId(request.getId());
            List<ItemWithRequestInfoDto> itemListDto = new ArrayList<>();

            for (Item item : items) {
                itemListDto.add(itemMapper.toItemWithRequestInfoDto(item));
            }

            requestListDto.add(requestMapper.toRequestWithItemInfoDto(request, itemListDto));

        }

        return requestListDto;
    }

    @Override
    public RequestWithItemInfoDto getRequestById(Long requestId, Long userId) {
        log.info("RequestService: getRequestById(): start with requestId={} and userId={}", requestId, userId);
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        if (!requestRepository.existsById(requestId)) {
            throw new NotFoundException("request with id=" + requestId + " not found");
        }

        Request request = requestRepository.getReferenceById(requestId);

        List<Item> items = itemRepository.getByRequestId(request.getId());
        List<ItemWithRequestInfoDto> itemListDto = new ArrayList<>();

        for (Item item : items) {
            itemListDto.add(itemMapper.toItemWithRequestInfoDto(item));
        }

        return requestMapper.toRequestWithItemInfoDto(request, itemListDto);
    }
}
