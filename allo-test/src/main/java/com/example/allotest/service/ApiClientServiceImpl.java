package com.example.allotest.service;

import com.example.allotest.exceptions.NoPathAvailableException;
import com.example.allotest.strategy.IDataFetcher;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApiClientServiceImpl implements IApiClientService {
    private final Map<String, IDataFetcher> strategies;

    public ApiClientServiceImpl(Map<String, IDataFetcher> strategies) {
        this.strategies = strategies;
    }

    @Override
    public Object[] fetchData(String resourceType) {
        IDataFetcher strategy = strategies.get(resourceType);
        if (strategy == null) {
            throw new NoPathAvailableException("unknown path: " + resourceType);
        }
        return strategy.fetchData();
    }
}
