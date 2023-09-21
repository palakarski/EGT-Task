package com.example.egttask.service.impl;

import com.example.egttask.model.dto.RequestDto;
import com.example.egttask.model.entity.RequestHistoryEntity;
import com.example.egttask.repository.RequestHistoryRepository;
import com.example.egttask.service.RequestService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestHistoryRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public void saveRequest(RequestDto requestDto) {
        repository.save(modelMapper.map(requestDto, RequestHistoryEntity.class));
    }

    @Override
    public boolean isDuplicatedRequest(RequestDto requestDto) {
        return repository.findFirstByRequestIdAndClientId(requestDto.getRequestId(), requestDto.getClientId()).isPresent();
    }
}
