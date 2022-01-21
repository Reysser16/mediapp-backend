package com.mitocode.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.TestCaseObservation;

public interface ITestCaseObservationRepo extends IGenericRepo<TestCaseObservation, Integer>{

	//
	@Modifying
	@Query(value = "INSERT INTO testcase_observation(id_testcase, id_observation) VALUES (:idTestcase, :idObservation)", nativeQuery = true)
	Integer registrar(@Param("idTestcase") Integer idTestcase, @Param("idObservation") Integer idObservation);
	
}
	