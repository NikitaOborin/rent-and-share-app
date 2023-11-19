package com.project.rentAndShareApp.request.controller;

import com.project.rentAndShareApp.request.dto.RequestRequestDto;
import com.project.rentAndShareApp.request.dto.RequestResponseDto;
import com.project.rentAndShareApp.request.dto.RequestWithItemInfoDto;
import com.project.rentAndShareApp.request.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/requests")
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public RequestResponseDto addRequest(@RequestBody @Valid RequestRequestDto requestDto,
                                         @RequestHeader("X-Sharer-User-Id") Long userId) {
        return requestService.addRequest(requestDto, userId);
    }

    @GetMapping
    public List<RequestWithItemInfoDto> getRequests(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return requestService.getRequests(userId);
    }
}
