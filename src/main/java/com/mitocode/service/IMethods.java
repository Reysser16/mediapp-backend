package com.mitocode.service;

import java.util.List;

public interface IMethods<T, ID> {

	T register(T t) throws Exception;
	T update(T t) throws Exception;
	List<T> list() throws Exception;
	T listForId(ID id) throws Exception;
	void delete(ID id) throws Exception;
}
