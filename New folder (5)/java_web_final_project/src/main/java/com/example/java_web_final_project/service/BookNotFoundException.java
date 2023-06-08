package com.example.java_web_final_project.service;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Could not find book with id " + id);
    }
}