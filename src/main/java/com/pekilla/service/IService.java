package com.pekilla.service;

public interface IService<T> {
    
    String create(T ent);
    
    String delete(long id); 

    String update(long id, T ent);
}
