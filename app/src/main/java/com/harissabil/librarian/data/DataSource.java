package com.harissabil.librarian.data;

import com.harissabil.librarian.data.model.Book;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Book> provideBooks() {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, "Data Structures and Algorithms in Java", "Robert Lafore", 2002, "https://m.media-amazon.com/images/I/91s8Z6lR87L._AC_UF1000,1000_QL80_.jpg", false));
        books.add(new Book(2, "Clean Code", "Robert C. Martin", 2008, "https://images-na.ssl-images-amazon.com/images/I/41jEbK-jG+L._SX258_BO1,204,203,200_.jpg", false));
        books.add(new Book(3, "Head First Design Patterns", "Eric Freeman", 2004, "https://m.media-amazon.com/images/I/91bobQSPQrL._AC_UF1000,1000_QL80_.jpg", false));
        books.add(new Book(4, "Cracking the Coding Interview", "Gayle Laakmann McDowell", 2015, "https://images-na.ssl-images-amazon.com/images/I/51l5XzLln+L._SX348_BO1,204,203,200_.jpg", false));
        books.add(new Book(5, "Introduction to the Theory of Computation", "Michael Sipser", 2012, "https://m.media-amazon.com/images/I/61dPNb6AUJL._AC_UF1000,1000_QL80_.jpg", false));
        books.add(new Book(6, "Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 1994, "https://m.media-amazon.com/images/I/81gtKoapHFL._AC_UF1000,1000_QL80_.jpg", false));
        books.add(new Book(7, "The Pragmatic Programmer", "Andrew Hunt", 1999, "https://images-na.ssl-images-amazon.com/images/I/41uPjEenkFL._SX258_BO1,204,203,200_.jpg", false));
        books.add(new Book(8, "Refactoring: Improving the Design of Existing Code", "Martin Fowler", 2018, "https://m.media-amazon.com/images/I/71e6ndHEwqL._AC_UF1000,1000_QL80_.jpg", false));
        books.add(new Book(9, "Code: The Hidden Language of Computer Hardware and Software", "Charles Petzold", 2000, "https://images.tokopedia.net/img/cache/700/hDjmkQ/2023/10/26/fbae4ddc-2aa9-4b9d-89f1-01f5290451e7.jpg", false));
        books.add(new Book(10, "Atomic Habits", "James Clear", 2018, "https://images-na.ssl-images-amazon.com/images/I/51-nXsSRfZL._SX329_BO1,204,203,200_.jpg", false));

        return books;
    }
}
