package com.pekilla.service.interfaces;

public interface IService<T> {
    
    String create(T ent);
    
    String delete(long id); 

    String update(long id, T ent);
    
    
}
