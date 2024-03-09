package com.harissabil.librarian.data.model;

import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private String image;
    private boolean isBorrowed;

    public Book(int id, String title, String author, int year, String image, boolean isBorrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.image = image;
        this.isBorrowed = isBorrowed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && year == book.year && isBorrowed == book.isBorrowed && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(image, book.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, year, image, isBorrowed);
    }
}
