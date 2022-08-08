package com.apache.apibookshop.web.controller;

import com.apache.apibookshop.domain.dto.LendBookDto;
import com.apache.apibookshop.domain.service.LendBookService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lendBooks")
@RestControllerAdvice
public class LendBookController extends ResponseEntityExceptionHandler {
    @Autowired
    private LendBookService lendBookService;

    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Hola", HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<LendBookDto>> getAll(){
        return new ResponseEntity<>(lendBookService.getAll(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LendBookDto> getLendBook(@PathVariable("id") Long id){
        return ResponseEntity.of(lendBookService.getLendBook(id));
    }

    @GetMapping("/user/{typeUser}")
    public ResponseEntity<List<LendBookDto>> getByTypeUser(@PathVariable("typeUser") Integer typeUser){
        return lendBookService.getByTypeUser(typeUser)
                .map(lendBookDto -> new ResponseEntity<>(lendBookDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody LendBookDto lendBookDomain){
        return new ResponseEntity<>(lendBookService.save(lendBookDomain), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        return new ResponseEntity<>(lendBookService.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest webRequest){
        return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
