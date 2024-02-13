package edu.bbte.bibliospringdata.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "page_number")
    private int pageNumber;

    @Column(name = "publishing_date")
    private String publishingDate;

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "book_author",
               joinColumns = @JoinColumn(name = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;


    public Book(){};

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, int pageNumber, String publishingDate) {
        this.title = title;
        this.pageNumber = pageNumber;
        this.publishingDate = publishingDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(String publishingDate) {
        this.publishingDate = publishingDate;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author){
        if(authors == null){
            authors = new ArrayList<>();
        }

        authors.add(author);
    }

    @Override
    public String toString() {
        return super.toString() + "Book{" +
                "title='" + title + '\'' +
                ", publishingDate='" + publishingDate + '\'' +
                '}';
    }
}
