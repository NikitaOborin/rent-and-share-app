package com.project.request.controller;

import com.project.request.dto.RequestRequestDto;
import com.project.request.dto.RequestResponseDto;
import com.project.request.dto.RequestWithItemInfoDto;
import com.project.request.service.RequestService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("requests")
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public RequestResponseDto addRequest(@RequestBody @Valid RequestRequestDto requestDto,
                                         @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("RequestController: addRequest(): start with userId={} and requestDto='{}'", userId, requestDto);
        return requestService.addRequest(requestDto, userId);
    }

    @GetMapping
    public List<RequestWithItemInfoDto> getRequests(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("RequestController: getRequests(): start with userId={}", userId);
        return requestService.getRequests(userId);
    }

    @GetMapping("all")
    public List<RequestWithItemInfoDto> getAllRequests(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                       @RequestParam(defaultValue = "0") @Min(0) Integer from,
                                                       @RequestParam(defaultValue = "20") @Min(1) @Max(100) Integer size) {
        log.info("RequestController: getAllRequests(): start with userId={}, from={} and size={}", userId, from, size);
        return requestService.getAllRequests(userId, from, size);
    }

    @GetMapping("{requestId}")
    public RequestWithItemInfoDto getRequestById(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                 @PathVariable Long requestId) {
        log.info("RequestController: getRequestById(): start with requestId={} and userId={}", requestId, userId);
        return requestService.getRequestById(requestId, userId);
    }
}
