package com.pekilla.service;

import org.springframework.http.ResponseEntity;

public interface IService<T> {
    
    ResponseEntity<T> getById(long id);
    
    // Title of Post / Name of User
    ResponseEntity<T> getByName(String name);

    ResponseEntity<String> create(T ent);
    
    ResponseEntity<String> delete(long id); 

    ResponseEntity<String> update(long id, T ent);
    
    
}
