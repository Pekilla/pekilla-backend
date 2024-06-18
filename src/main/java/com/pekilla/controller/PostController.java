package com.pekilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pekilla.service.PostService;

@RestController
@RequestMapping(path = "/post")
public class PostController {
    
    @Autowired private PostService postService;

}
