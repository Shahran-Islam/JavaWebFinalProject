package com.example.java_web_final_project.service;

import com.example.java_web_final_project.model.Book;
import com.example.java_web_final_project.service.BookRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return repository.findAll();
    }

    @PostMapping
    public Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Book replaceBook(@RequestBody Book newBook, @PathVariable Long id) {
        return repository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    return repository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
