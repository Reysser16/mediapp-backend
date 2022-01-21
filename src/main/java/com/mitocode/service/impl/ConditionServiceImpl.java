package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Condition;
import com.mitocode.repo.IConditionRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IConditionService;

@Service
public class ConditionServiceImpl extends MethodsImpl<Condition, Integer> implements IConditionService {

    @Autowired
    private IConditionRepo repo;

    @Override
    protected IGenericRepo<Condition, Integer> getRepo() {
        return repo;
    }


}
