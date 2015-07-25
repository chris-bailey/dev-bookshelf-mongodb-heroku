package com.chrisbaileydeveloper.bookshelf.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.chrisbaileydeveloper.bookshelf.Application;
import com.chrisbaileydeveloper.bookshelf.domain.Book;
import com.chrisbaileydeveloper.bookshelf.repository.BookRepository;
import com.chrisbaileydeveloper.bookshelf.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class BookControllerTest {
	private MockMvc mockMvc;
	private static String OBJECTID = "55b13fe48463914e867893c8";
	private static String EXPECTED_NAME = "Name test";
	private static String EXPECTED_PUBLISHER = "Publisher test";
	
	private Book testBook = null;

	@Inject
	private BookRepository bookRepository;

	@Before
	public void setup() {
		BookService bookService = new BookService();
		ReflectionTestUtils.setField(bookService, "bookRepository",	bookRepository);

		BookController bookController = new BookController();
		ReflectionTestUtils.setField(bookController, "bookService", bookService);

		// Reset test database.
		bookService.deleteAll();
		bookService.restoreDefaultBooks();
		
		// Setup test book.
    	testBook = new Book(EXPECTED_NAME, EXPECTED_PUBLISHER, null, "Description test", "iVBORw0KGgoA");
    	testBook.setId(OBJECTID);
    	bookService.save(testBook);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}
	
	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("books/list"))
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("books"))
			.andExpect(model().attribute("books",
					hasItem(allOf(
							hasProperty("id", is(OBJECTID)),
							hasProperty("name", is(EXPECTED_NAME)),
							hasProperty("publisher", is(EXPECTED_PUBLISHER))
							))
			));
	}

	@Test
	public void testShow() throws Exception {
		mockMvc.perform(get("/{id}", OBJECTID))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("books/show"))
			.andExpect(model().size(1))
			.andExpect(model().attribute("book", 
					allOf(
						hasProperty("id", is(OBJECTID)),
						hasProperty("name", is(EXPECTED_NAME)),
                        hasProperty("publisher", is(EXPECTED_PUBLISHER))
					)
			));
	}

	@Test
	public void testUpdateForm() throws Exception {
		mockMvc.perform(get("/update/{id}", OBJECTID))
			.andExpect(status().isOk())
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("book"))
			.andExpect(model().attribute("book", 
					allOf(
						hasProperty("id", is(OBJECTID)),
						hasProperty("name", is(EXPECTED_NAME)),
                        hasProperty("publisher", is(EXPECTED_PUBLISHER))
					)
			));
	}
	
	@Test
	public void testCreateForm() throws Exception {
		mockMvc.perform(get("/create"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("books/create"))
			.andExpect(model().size(1))
			.andExpect(model().attributeExists("book"))
			.andExpect(model().attribute("book", 
					allOf(
						hasProperty("name", equalTo(null)),
                        hasProperty("publisher", equalTo(null))
					)
			));;
	}
	
	@Test
	public void testDownloadPhoto() throws Exception {
		mockMvc.perform(get("/photo/" + OBJECTID)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE))
				.andReturn();
	}
}
