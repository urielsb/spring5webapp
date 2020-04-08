package guru.springframework.spring5webapp.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.reporitories.AuthorRepository;
import guru.springframework.spring5webapp.reporitories.BookRepository;
import guru.springframework.spring5webapp.reporitories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(BootstrapData.class);

	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	
	
	
	public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		Book book = new Book("Java Thougs", "388d9d8s9");
		Author author = new Author("Jeremy", "Springfield");
		
		author.getBooks().add(book);
		book.getAuthors().add(author);
		
		authorRepository.save(author);
		bookRepository.save(book);
		
		Publisher publisher = new Publisher("ADA", "Great Avenue", "38940");
		publisher.getBooks().add(book);
		
		book.setPublisher(publisher);
		
		publisherRepository.save(publisher);
		bookRepository.save(book);
		
		log.info("Author count: " + authorRepository.count());
		log.info("Book count: " + bookRepository.count());
		log.info("Publisher count: " + publisherRepository.count());
		log.info("Publisher number of books: " + publisher.getBooks().size());
	}

}
