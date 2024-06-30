package com.pekilla.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface IService<T> {
    
    String create(@Valid T ent);
    
    String delete(long id); 

    String update(long id, @Valid T ent);
}
