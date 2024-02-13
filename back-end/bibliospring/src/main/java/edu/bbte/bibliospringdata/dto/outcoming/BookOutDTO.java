package edu.bbte.bibliospringdata.dto.outcoming;

import edu.bbte.bibliospringdata.model.Author;

import java.util.List;

public class BookOutDTO {

    private String uuid;

    private Long id;

    private String title;

    private int pageNumber;

    private String publishingDate;

    private String authorsNames;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAuthorsNames() {
        return authorsNames;
    }

    public void setAuthorsNames(String authorsNames) {
        this.authorsNames = authorsNames;
    }
}
