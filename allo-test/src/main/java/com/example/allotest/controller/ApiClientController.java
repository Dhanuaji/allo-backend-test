package com.example.allotest.controller;

import com.example.allotest.constants.ApiResponseConstants;
import com.example.allotest.dtos.BaseResponseDto;
import com.example.allotest.exceptions.NoPathAvailableException;
import com.example.allotest.service.IApiClientService;
import com.example.allotest.strategy.IDataFetcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiClientController {
    private final Map<String, IDataFetcher> strategies;
    private final IApiClientService apiClientService;

    public ApiClientController(Map<String, IDataFetcher> strategies,
                               IApiClientService apiClientService) {
        this.strategies = strategies;
        this.apiClientService = apiClientService;
    }

    @GetMapping("/api/finance/data/{resourceType}")
    public ResponseEntity<BaseResponseDto<Object[]>> getData(@PathVariable String resourceType) {
        if (strategies.get(resourceType) == null) {
            throw new NoPathAvailableException("unknown path: " + resourceType, resourceType);
        }

        Object[] data = apiClientService.fetchData(resourceType);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponseDto<>(
                        ApiResponseConstants.OK_STATUS_CODE,
                        ApiResponseConstants.OK_STATUS_MESSAGE,
                        resourceType,
                        data));
    }
}
