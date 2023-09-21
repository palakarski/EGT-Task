package com.example.egttask.service;

import com.example.egttask.model.dto.RequestDto;

public interface RequestService {


    void saveRequest(RequestDto requestDto);

    boolean isDuplicatedRequest(RequestDto requestDto);
}
