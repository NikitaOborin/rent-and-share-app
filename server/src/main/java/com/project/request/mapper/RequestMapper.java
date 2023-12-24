package com.project.request.mapper;

import com.project.item.dto.ItemResponseDto;
import com.project.request.dto.RequestRequestDto;
import com.project.request.dto.RequestResponseDto;
import com.project.request.dto.RequestWithItemInfoDto;
import com.project.request.entity.Request;
import com.project.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RequestMapper {
    public RequestResponseDto toRequestDto(Request request) {
        return new RequestResponseDto(
                request.getId(),
                request.getDescription(),
                request.getCreated()
        );
    }

    public RequestWithItemInfoDto toRequestWithItemInfoDto(Request request, List<ItemResponseDto> itemsDto) {
        return new RequestWithItemInfoDto(
                request.getId(),
                request.getDescription(),
                request.getCreated(),
                itemsDto
        );
    }

    public Request toRequest(RequestRequestDto requestRequestDto, Long userId, LocalDateTime created) {
        Request request = new Request();
        User requester = new User();

        requester.setId(userId);

        request.setDescription(requestRequestDto.getDescription());
        request.setRequester(requester);
        request.setCreated(created);

        return request;
    }
}