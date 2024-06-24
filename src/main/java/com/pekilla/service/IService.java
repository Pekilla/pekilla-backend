package com.pekilla.service;

import org.springframework.stereotype.Service;

@Service
public interface IService<T> {
    
    String create(T ent);
    
    String delete(long id); 

    String update(long id, T ent);
}
