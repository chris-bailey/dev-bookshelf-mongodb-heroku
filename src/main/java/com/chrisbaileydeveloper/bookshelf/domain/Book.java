package com.chrisbaileydeveloper.bookshelf.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * A Book.
 */
@Document(collection = "T_BOOK")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    private String id;

    @NotEmpty
    @Size(min = 5, max = 100)
    @Field("name")
    private String name;

    @NotEmpty
    @Field("publisher")
    private String publisher;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
    @Field("date_of_publication")
    private DateTime dateOfPublication;

    @Field("description")
    private String description;

    @Field("photo")
    private String photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public DateTime getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(DateTime dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    
    /** No-args constructor */
    public Book() {
	}

	/** Constructor */
	public Book(String name, String publisher, DateTime dateOfPublication,
			String description, String photo) {
		this.name = name;
		this.publisher = publisher;
		this.dateOfPublication = dateOfPublication;
		this.description = description;
		this.photo = photo;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book book = (Book) o;

        if ( ! Objects.equals(id, book.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "_id=" + id +
                ", name='" + name + "'" +
                ", publisher='" + publisher + "'" +
                ", dateOfPublication='" + dateOfPublication + "'" +
                ", description='" + description + "'" +
                ", photo='" + photo + "'" +
                '}';
    }
}
