package com.harissabil.librarian.core.data_structure.searching;

import androidx.core.util.Pair;

import com.harissabil.librarian.data.model.Book;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility class to perform linear search on a list of books.
 */
public class LinearSearch {

    /**
     * Method to perform linear search on a list of books by title.
     *
     * @param books List of books to search.
     * @param title Title to search for.
     * @return List of matching books.
     */
    public static List<Book> linearSearchByTitle(List<Book> books, String title) {
        List<Book> matchingBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingBooks.add(book);
            }
        }

        return matchingBooks;
    }

    /**
     * Method to perform linear search on a linked list of books by title.
     *
     * @param books Linked list of books to search.
     * @param title Title to search for.
     * @return Linked list of matching books.
     */
    public static LinkedList<Pair<Long, Book>> linearSearchByTitle(LinkedList<Pair<Long, Book>> books, String title) {
        LinkedList<Pair<Long, Book>> matchingBooks = new LinkedList<>();

        for (Pair<Long, Book> book : books) {
            if (book.second.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matchingBooks.add(book);
            }
        }

        return matchingBooks;
    }
}
