package com.mitocode.service.impl;

import java.util.List;

import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IMethods;

public abstract class MethodsImpl<T, ID> implements IMethods<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T register(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> list() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T listForId(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().deleteById(id);
    }

}
