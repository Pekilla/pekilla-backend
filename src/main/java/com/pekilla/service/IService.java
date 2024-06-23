package com.pekilla.service;

import org.springframework.http.ResponseEntity;

public interface IService<T> {
    
    ResponseEntity<T> getById(long id);
    
    // Title of Post / Name of User
    T getByName(String name);

    String create(T ent);
    
    String delete(long id); 

    String update(long id, T ent);
    
    
}
