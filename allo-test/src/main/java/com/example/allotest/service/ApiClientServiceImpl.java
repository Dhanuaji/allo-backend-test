package com.example.allotest.service;

import com.example.allotest.exceptions.CustomGlobalException;
import com.example.allotest.store.DataStore;
import org.springframework.stereotype.Service;

@Service
public class ApiClientServiceImpl implements IApiClientService {
    private final DataStore dataStore;

    public ApiClientServiceImpl(DataStore store) {
        this.dataStore = store;
    }

    @Override
    public Object[] fetchData(String resourceType) {
        try {
            return dataStore.getFromStore(resourceType);
        } catch (Exception e) {
            throw new CustomGlobalException("error occured: " + e.getMessage(), resourceType);
        }
    }
}