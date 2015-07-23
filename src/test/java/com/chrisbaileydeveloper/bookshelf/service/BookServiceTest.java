package com.chrisbaileydeveloper.bookshelf.service;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.chrisbaileydeveloper.bookshelf.Application;
import com.chrisbaileydeveloper.bookshelf.domain.Book;

/**
 * Test class for the BookServiceImpl service.
 *
 * @see BookService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class BookServiceTest {
	private static int TOTAL_DEFAULT_BOOKS = 8;
	private static String OBJECTID = "55b13fe48463914e867893c8";
	private static String EXPECTED_NAME = "Name test";
	private static String EXPECTED_PUBLISHER = "Publisher test";
	
	private int totalBooks;
    
	private Book testBook = null;
	
    @Inject
    private BookService bookService;
	
    @Before
	public void setup() {
    	// Initialize test database.
    	bookService.deleteAll();
    	bookService.restoreDefaultBooks();
    	
    	// Setup test book.
    	testBook = new Book(EXPECTED_NAME, EXPECTED_PUBLISHER, null, "Description test", "iVBORw0KGgoA");
    	testBook.setId(OBJECTID);
    	bookService.save(testBook);
    	
    	// Initialize 'totalBooks' counter.
		totalBooks = bookService.findAll().size();
	}
    
    @Test
	public void testFindAll() {
    	assertThat(bookService.findAll()).hasSize(totalBooks);
	}

	@Test
	public void testSave() {
		Book b = new Book("Full Name", "publisher", new DateTime(), "description", "photo");
		bookService.save(b);
		assertThat(bookService.findAll()).hasSize(totalBooks + 1);
	}

	@Test
	public void testFindById() {
        assertThat(bookService.findById(OBJECTID).getName()).isEqualTo(EXPECTED_NAME);
        assertThat(bookService.findById(OBJECTID).getPublisher()).isEqualTo(EXPECTED_PUBLISHER);
	}
	
	@Test
	public void testDelete() {
		// Removing testBook.
		Book b = bookService.findById(OBJECTID);
		bookService.delete(b);
		assertThat(bookService.findAll()).hasSize(totalBooks - 1);
	}

	@Test
	public void testDeleteAll() {
		bookService.deleteAll();
		assertThat(bookService.findAll()).isEmpty();
	}

	@Test
	public void testRestoreDefaultBooks() {
		bookService.deleteAll();
		assertThat(bookService.findAll()).isEmpty();
		bookService.restoreDefaultBooks();
		assertThat(bookService.findAll()).hasSize(TOTAL_DEFAULT_BOOKS);
	}
}
