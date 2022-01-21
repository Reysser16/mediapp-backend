package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Tester;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.ITesterRepo;
import com.mitocode.service.ITesterService;

@Service
public class TesterServiceImpl extends MethodsImpl<Tester, Integer> implements ITesterService {

    @Autowired
    private ITesterRepo repo;

    @Override
    protected IGenericRepo<Tester, Integer> getRepo() {
        return repo;
    }


}
