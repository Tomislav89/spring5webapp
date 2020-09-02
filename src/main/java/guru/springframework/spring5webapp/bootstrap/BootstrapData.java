package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Bootstrap komponenta
@Component
public class BootstrapData implements CommandLineRunner {

    //CLR interface koji definira što će se sve pokretati u isto vrijeme dok se app boota,
    //nešto što će se izvršiti prije nego li se app potpuno boota

    //Dependency injection - uvodjenje AuthorRepo, BookRepo, PublisherRepo u Spring context
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher tomPublisher = new Publisher("Tomislav", "Bozirdara Magovca 109", "Zagreb", "Croatia", "10000");
        publisherRepository.save(tomPublisher);


        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book( "Domain Driven Design","123123");
        eric.getBooks().add(ddd);
        ddd.getAuthor().add(eric);

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Developement without EJB","32155452");
        rod.getBooks().add(noEJB);
        noEJB.getAuthor().add(rod);

        ddd.setPublisher(tomPublisher);
        tomPublisher.getBooks().add(ddd);

        noEJB.setPublisher(tomPublisher);
        tomPublisher.getBooks().add(noEJB);

        // spremanje u H2 database
        authorRepository.save(eric);
        bookRepository.save(ddd);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());


    }
}
