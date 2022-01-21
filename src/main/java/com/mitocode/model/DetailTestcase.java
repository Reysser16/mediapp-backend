package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detail_testcase")
public class DetailTestcase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetailTestcase;
	
	@ManyToOne
	@JoinColumn(name = "id_testcase", nullable = false, foreignKey = @ForeignKey(name = "FK_detail_test_case"))
	private TestCase testcase;

	@Column(name = "observation", nullable = false, length = 70)
	private String observation;

	public Integer getIdDetailTestcase() {
		return idDetailTestcase;
	}

	public void setIdDetailTestcase(Integer idDetailTestcase) {
		this.idDetailTestcase = idDetailTestcase;
	}

	public TestCase getTestcase() {
		return testcase;

	}

	public void setTestcase(TestCase testcase) {
		this.testcase = testcase;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
}
