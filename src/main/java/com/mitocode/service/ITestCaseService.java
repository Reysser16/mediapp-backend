package com.mitocode.service;

import java.util.List;

import com.mitocode.model.TestCase;
import com.mitocode.model.Observation;

public interface ITestCaseService extends IMethods<TestCase, Integer> {
	
	TestCase registerTransactional(TestCase testcase, List<Observation> examenes) throws Exception;

}
