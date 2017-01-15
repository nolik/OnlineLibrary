package com.NovikIgor.onlineLibrary.dao.interfaces;

import com.NovikIgor.onlineLibrary.entities.Author;
import com.NovikIgor.onlineLibrary.entities.Book;
import com.NovikIgor.onlineLibrary.entities.Genre;

import java.util.List;

/**
 * Created by nolik on 16.01.17.
 */
public interface BookDAO {

    List<Book> getBooks();

    List<Book> getBooks(Author author);

    List<Book> getBooks(String bookName);

    List<Book> getBooks(Genre genre);

    List<Book> getBooks(Character letter);

}

