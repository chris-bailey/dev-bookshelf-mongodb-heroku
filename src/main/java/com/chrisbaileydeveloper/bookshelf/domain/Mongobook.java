package com.chrisbaileydeveloper.bookshelf.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.chrisbaileydeveloper.bookshelf.domain.util.CustomDateTimeDeserializer;
import com.chrisbaileydeveloper.bookshelf.domain.util.CustomDateTimeSerializer;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Mongobook.
 */
@Document(collection = "T_MONGOBOOK")
public class Mongobook implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @Id
    private String id;

    @NotNull
    @Size(min = 5, max = 100)
    @Field("name")
    private String name;

    @NotNull
    @Field("publisher")
    private String publisher;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Mongobook mongobook = (Mongobook) o;

        if ( ! Objects.equals(id, mongobook.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Mongobook{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", publisher='" + publisher + "'" +
                ", dateOfPublication='" + dateOfPublication + "'" +
                ", description='" + description + "'" +
                ", photo='" + photo + "'" +
                '}';
    }
}
