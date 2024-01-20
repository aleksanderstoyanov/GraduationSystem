package com.graduation.system.config;

import com.graduation.system.exceptions.*;
import com.graduation.system.services.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected String unauthorizedException(Exception exception, Model model){
        return "/error/unauthorized.html";
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String userNotFoundException(UserNotFoundException exception, Model model){
        model.addAttribute("message", exception.getMessage());
        return "/error/not-found.html";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected String userAlreadyExistsException(UserAlreadyExistsException exception, Model model){
        model.addAttribute("message", exception.getMessage());
        return "/error/not-found.html";
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String applicationNotFoundException(ApplicationNotFoundException exception, Model model){
        model.addAttribute("message", exception.getMessage());
        return "/error/not-found.html";
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String studentNotFoundException(StudentNotFoundException exception, Model model){
        model.addAttribute("message", exception.getMessage());
        return "/error/not-found.html";
    }

    @ExceptionHandler(ThesisNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String thesisNotFoundException(ThesisNotFoundException exception, Model model){
        model.addAttribute("message", exception.getMessage());
        return "/error/not-found.html";
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String reviewNotFoundException(ReviewNotFoundException exception, Model model){
        model.addAttribute("message", exception.getMessage());
        return "/error/not-found.html";
    }

    @ExceptionHandler(FacultyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String facultyNotFoundException(FacultyNotFoundException exception, Model model){
        model.addAttribute("message", exception.getMessage());
        return "/error/not-found.html";
    }
}
