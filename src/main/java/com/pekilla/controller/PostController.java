package com.pekilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pekilla.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/post")
public class PostController {
    
    @Autowired private PostService postService;

    @GetMapping("/{postId}")
    public String getPostById(@PathVariable long postId) {
        return new String();
    }

    @PostMapping("/create")
    public String createPost(@RequestParam String title, @RequestParam String content) {
        //TODO: process POST request
        
        return null;
    }
    
    

}
