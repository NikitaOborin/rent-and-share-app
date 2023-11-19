package com.project.rentAndShareApp.request.mapper;

import com.project.rentAndShareApp.item.dto.ItemWithRequestInfoDto;
import com.project.rentAndShareApp.request.dto.RequestRequestDto;
import com.project.rentAndShareApp.request.dto.RequestResponseDto;
import com.project.rentAndShareApp.request.dto.RequestWithItemInfoDto;
import com.project.rentAndShareApp.request.entity.Request;
import com.project.rentAndShareApp.user.entity.User;
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

    public RequestWithItemInfoDto toRequestWithItemInfoDto(Request request, List<ItemWithRequestInfoDto> itemsDto) {
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