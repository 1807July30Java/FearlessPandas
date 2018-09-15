package com.revature.controller;

import com.revature.domain.Genre;
import com.revature.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Genre>> getAllGenres() {
        return new ResponseEntity<>(genreRepository.getGenres(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable int id) {
    	try {
    		return new ResponseEntity<>(genreRepository.getGenreById(id), HttpStatus.OK);
    	}catch(Exception e) {
    		return new ResponseEntity<>(null, HttpStatus.OK);
    	}
    }
}
