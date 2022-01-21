package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Observation;
import com.mitocode.repo.IObservationRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IObservationService;

@Service
public class ObservationServiceImpl extends MethodsImpl<Observation, Integer> implements IObservationService {

    @Autowired
    private IObservationRepo repo;

    @Override
    protected IGenericRepo<Observation, Integer> getRepo() {
        return repo;
    }


}
