package com.chrisbaileydeveloper.bookshelf.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.chrisbaileydeveloper.bookshelf.domain.Mongobook;
import com.chrisbaileydeveloper.bookshelf.repository.MongobookRepository;
import com.google.common.collect.Lists;

@Service
public class MongobookService {
	final Logger logger = LoggerFactory.getLogger(MongobookService.class);

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
	public void restoreDefaultMongobooks() {
		ClassPathResource resource = new ClassPathResource("/config/mongobooks.csv");

		BufferedReader br = null;

		try {

			br = new BufferedReader(new InputStreamReader(
					resource.getInputStream()));

			// Skip first line that only holds headers.
			br.readLine();

			String line;

			while ((line = br.readLine()) != null) {
				String[] words = line.split("~");

				String name = words[0];
				String publisher = words[1];

				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				DateTime dateOfPublication = formatter.parseDateTime(words[2]);

				String description = words[3];
				String photo = words[4];

				String id = new ObjectId().toString();
				Mongobook mb = new Mongobook(id, name, publisher, dateOfPublication,
						description, photo);

				mongobookRepository.save(mb);
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

	}
}
