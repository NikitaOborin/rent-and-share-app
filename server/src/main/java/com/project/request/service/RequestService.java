package com.project.request.service;

import com.project.request.dto.RequestRequestDto;
import com.project.request.dto.RequestResponseDto;
import com.project.request.dto.RequestWithItemInfoDto;

import java.util.List;

public interface RequestService {
    RequestResponseDto addRequest(RequestRequestDto requestDto, Long userId);

    List<RequestWithItemInfoDto> getRequests(Long userId);

    List<RequestWithItemInfoDto> getAllRequests(Long userId, Integer from, Integer size);

    RequestWithItemInfoDto getRequestById(Long requestId, Long userId);
}
