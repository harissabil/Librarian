package com.harissabil.librarian.data;

import com.harissabil.librarian.data.model.Book;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<Book> provideBooks() {
        List<Book> books = new ArrayList<>();

        books.add(new Book(1, "Clean Code", "Robert C. Martin", 2008, "https://m.media-amazon.com/images/I/51E2055ZGUL._AC_UF1000,1000_QL80_.jpg", false));
        books.add(new Book(2, "Kotlin in Action, Second Edition", "Roman Elizarov", 2024, "https://d28hgpri8am2if.cloudfront.net/book_images/onix/cvr9781617299605/kotlin-in-action-second-edition-9781617299605_hr.jpg", false));
        books.add(new Book(3, "ようこそ実力至上主義の教室へ　２年生編９．５", "衣笠彰梧", 2023, "https://cdn.kdkw.jp/cover_500/322303/322303000119.jpg", false));
        books.add(new Book(4, "Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 1994, "https://learning.oreilly.com/library/cover/0201633612/250w/", false));
        books.add(new Book(5, "Refactoring: Improving the Design of Existing Code", "Martin Fowler", 2018, "https://martinfowler.com/books/refact2.jpg", false));
        books.add(new Book(6, "Fundamentals of Software Architecture: An Engineering Approach", "Mark Richards", 2020, "https://m.media-amazon.com/images/I/9193iMIxVTL._SL1500_.jpg", false));
        books.add(new Book(7, "Computer Organization and Architecture, Global Edition, 11th Edition", "William Stallings", 2022, "https://www.pearson.com/uk/content/dam/one-dot-com/one-dot-com/netherlands/Higher-Education/9781292420103.jpg.transform/big-size-xl/img.jpeg", false));
        books.add(new Book(8, "Automate the Boring Stuff with Python, 2nd Edition: Practical Programming for Total Beginners", "Al Sweigart", 2019, "https://nostarch.com/sites/default/files/styles/uc_product_full/public/automate_cover-blurb_2B.png?itok=B8PV7_on", false));
        books.add(new Book(9, "Naked Statistics: Stripping the Dread from the Data", "Charles Wheelan", 2013, "https://m.media-amazon.com/images/I/71L9LVXVSML._SL1500_.jpg", false));
        books.add(new Book(10, "Introduction to the Theory of Computation", "Michael Sipser", 2012, "https://m.media-amazon.com/images/I/61dPNb6AUJL._AC_UF1000,1000_QL80_.jpg", false));

        return books;
    }
}
