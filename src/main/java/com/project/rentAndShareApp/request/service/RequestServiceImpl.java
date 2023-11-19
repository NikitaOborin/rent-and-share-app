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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        Request request = requestMapper.toRequest(requestDto, userId, LocalDateTime.now());

        return requestMapper.toRequestDto(requestRepository.save(request));
    }

    @Override
    public List<RequestWithItemInfoDto> getRequests(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("user with id=" + userId + " not found");
        }

        List<Request> requests = requestRepository.getByRequesterId(userId);
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
}
