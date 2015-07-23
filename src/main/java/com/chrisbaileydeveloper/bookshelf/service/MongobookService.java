package com.chrisbaileydeveloper.bookshelf.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisbaileydeveloper.bookshelf.controller.ArchiveBookController;
import com.chrisbaileydeveloper.bookshelf.domain.Mongobook;
import com.chrisbaileydeveloper.bookshelf.repository.MongobookRepository;
import com.google.common.collect.Lists;

@Service
public class MongobookService {
	final Logger logger = LoggerFactory.getLogger(ArchiveBookController.class);

	@Inject
	private MongobookRepository mongobookRepository;

	public List<Mongobook> findAll() {
		return Lists.newArrayList(mongobookRepository.findAll());
	}

	public Mongobook findById(String id) {
		return mongobookRepository.findOneById(id);
	}

	public Mongobook save(Mongobook mongobook) {
		return mongobookRepository.save(mongobook);
	}

	public void delete(Mongobook mongobook) {
		mongobookRepository.delete(mongobook);
	}

	/**
	 * Removes all Book entities from database.
	 */
	public void deleteAll() {
		mongobookRepository.deleteAll();
	}

	/**
	 * Restore the original set of books to the database.
	 */
	// CJB -> TODO -> Work on this after many other things are setup.
	/*public void restoreDefaultBooks() {
		ClassPathResource resource = new ClassPathResource("/config/liquibase/books.csv");

		BufferedReader br = null;

		try {

			br = new BufferedReader(new InputStreamReader(
					resource.getInputStream()));

			// Skip first line that only holds headers.
			br.readLine();

			String line;

			while ((line = br.readLine()) != null) {
				String[] words = line.split("~");

				Integer version = Integer.valueOf(words[0]);
				String name = words[1];
				String publisher = words[2];

				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				DateTime dateOfPublication = formatter.parseDateTime(words[3]);

				String description = words[4];
				String photo = words[5];

				Book b = new Book(version, name, publisher, dateOfPublication,
						description, photo);

				bookRepository.save(b);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Release resources.
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}*/
}
