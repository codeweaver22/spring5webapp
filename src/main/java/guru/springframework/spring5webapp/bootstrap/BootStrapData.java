package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Desing", "123123");
        Publisher feathers = new Publisher("Feathers company", "Main Rd", "New York", "ABC", "12332");

        publisherRepository.save(feathers);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(feathers);
        feathers.getBookSet().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(feathers);

        Author rod = new Author("Rod", "Jonson");
        Book noeJB = new Book("J2EEE development without EJB", "329329");

        rod.getBooks().add(noeJB);
        noeJB.getAuthors().add(rod);

        noeJB.setPublisher(feathers);
        feathers.getBookSet().add(noeJB);

        authorRepository.save(rod);
        bookRepository.save(noeJB);
        publisherRepository.save(feathers);

        System.out.println("Number of books: " + bookRepository.count());

        System.out.println("Number of books under publisher: " + feathers.getName() + " " + feathers.getBookSet().size());
    }
}
