package com.example.JBDL33twelveminorproject1.controllers;

import com.example.JBDL33twelveminorproject1.request.BookCreateRequest;
import com.example.JBDL33twelveminorproject1.request.BookFilterType;
import com.example.JBDL33twelveminorproject1.request.BookSearchOperationType;
import com.example.JBDL33twelveminorproject1.response.BookSearchResponse;
import com.example.JBDL33twelveminorproject1.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

//    //wiring custom property
//    @Value("${custom.my_prop}")
//    String sampleText;
//
//    @GetMapping("/sample")
//    public String getSampleText(){
//        return this.sampleText;
//    }

    @PostMapping("/book")
    public void createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest ){
        bookService.createOrUpdate(bookCreateRequest);
    }

    //GET - Filter functionality / search
    @GetMapping("/books/search")
    public List<BookSearchResponse> findBooks(
            @RequestParam("filterType") BookFilterType bookFilterType,
            @RequestParam("value") String value){
        return bookService
                .find(bookFilterType, value)
                .stream()
                .map(book -> book.to())
                .collect(Collectors.toList());
    }

    //GET - Filter functionality / search
    @GetMapping("/books/robust2")
    public List<BookSearchResponse> findBooks3(
            @RequestParam("filterType") List<BookFilterType> bookFilterType,
            @RequestParam("value") List<String> value,
            @RequestParam("bookSearchOperationType") List<BookSearchOperationType> bookSearchOperationType){
        return bookService
                .find3(bookFilterType, value, bookSearchOperationType)
                .stream()
                .map(book -> book.to())
                .collect(Collectors.toList());
    }

}
