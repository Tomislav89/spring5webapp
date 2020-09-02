package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books") // localhost:8090/boooks
    public String getBooks(Model model){
        // <tr th:each="book : ${books}"> books je parametar koji se prosljedi u html te vrši for each iteraciju po listi knjiga
        model.addAttribute("books", bookRepository.findAll());
        return "books/list";
    }
}
