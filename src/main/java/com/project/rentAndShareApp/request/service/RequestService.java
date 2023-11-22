package com.project.rentAndShareApp.request.service;

import com.project.rentAndShareApp.request.dto.RequestRequestDto;
import com.project.rentAndShareApp.request.dto.RequestResponseDto;
import com.project.rentAndShareApp.request.dto.RequestWithItemInfoDto;

import java.util.List;

public interface RequestService {
    RequestResponseDto addRequest(RequestRequestDto requestDto, Long userId);

    List<RequestWithItemInfoDto> getRequests(Long userId);

    List<RequestWithItemInfoDto> getAllRequests(Long userId, Integer from, Integer size);

    RequestWithItemInfoDto getRequestById(Long requestId, Long userId);
}
