package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Test;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ITestRepo;
import com.mitocode.service.ITestService;

@Service
public class TestServiceImpl extends MethodsImpl<Test, Integer> implements ITestService {

    @Autowired
    private ITestRepo repo;

    @Override
    protected IGenericRepo<Test, Integer> getRepo() {
        return repo;
    }

}
