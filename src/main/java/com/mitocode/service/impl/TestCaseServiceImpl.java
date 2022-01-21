package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.model.TestCase;
import com.mitocode.model.Observation;
import com.mitocode.repo.ITestCaseObservationRepo;
import com.mitocode.repo.ITestCaseRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ITestCaseService;

@Service
public class TestCaseServiceImpl extends MethodsImpl<TestCase, Integer> implements ITestCaseService {

    @Autowired
    private ITestCaseRepo repo;

    @Autowired
    private ITestCaseObservationRepo ceRepo;

    @Override
    protected IGenericRepo<TestCase, Integer> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public TestCase registerTransactional(TestCase testcase, List<Observation> observations) throws Exception {
        testcase.getDetailTestcase().forEach(det -> det.setTestcase(testcase));
        repo.save(testcase);

        observations.forEach(ex -> ceRepo.registrar(testcase.getIdTestcase(), ex.getIdObservation()));

        return testcase;
    }


}
