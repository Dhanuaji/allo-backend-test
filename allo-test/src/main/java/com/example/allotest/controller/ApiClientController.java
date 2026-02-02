package com.example.allotest.controller;

import com.example.allotest.constants.ApiResponseConstants;
import com.example.allotest.dtos.BaseResponseDto;
import com.example.allotest.service.IApiClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiClientController {
    private final IApiClientService apiClientService;

    public ApiClientController(IApiClientService apiClientService) {
        this.apiClientService = apiClientService;
    }

    @GetMapping("/api/finance/data/{resourceType}")
    public ResponseEntity<BaseResponseDto<Object[]>> getData(@PathVariable String resourceType) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponseDto<>(
                        ApiResponseConstants.OK_STATUS_CODE,
                        ApiResponseConstants.OK_STATUS_MESSAGE,
                        apiClientService.fetchData(resourceType)));
    }
}
