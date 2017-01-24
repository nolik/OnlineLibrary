package com.NovikIgor.onlineLibrary.dao.impl;

import com.NovikIgor.onlineLibrary.dao.interfaces.BookDAO;
import com.NovikIgor.onlineLibrary.entities.Author;
import com.NovikIgor.onlineLibrary.entities.Book;
import com.NovikIgor.onlineLibrary.entities.Genre;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nolik on 16.01.17.
 */
@Component
public class BookDAOImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private List<Book> books;
    private ProjectionList bookProjection;

    public BookDAOImpl() {
        bookProjection = Projections.projectionList();
        bookProjection.add(Projections.property("id"), "id");
        bookProjection.add(Projections.property("name"), "name");
        bookProjection.add(Projections.property("image"), "image");
        bookProjection.add(Projections.property("genreByGenreId"), "genreByGenreId");
        bookProjection.add(Projections.property("pageCount"), "pageCount");
        bookProjection.add(Projections.property("isbn"), "isbn");
        bookProjection.add(Projections.property("publisherByPublisherId"), "publisherByPublisherId");
        bookProjection.add(Projections.property("authorByAuthorId"), "authorByAuthorId");
        bookProjection.add(Projections.property("publishYear"), "publishYear");
        bookProjection.add(Projections.property("descr"), "descr");
        bookProjection.add(Projections.property("rating"), "rating");
        bookProjection.add(Projections.property("voteCount"), "voteCount");
    }


    @Transactional
    @Override
    public List<Book> getBooks() {
        List<Book> books = createBookList(createBookCriteria());
        return books;
    }

    @Transactional
    @Override
    public List<Book> getBooks(Author author) {
        List<Book> books = createBookList(createBookCriteria().add(Restrictions.ilike("authorByAuthorId.fio", author.getFio(), MatchMode.ANYWHERE)));
        return books;
    }

    @Transactional
    @Override
    public List<Book> getBooks(String bookName) {
        List<Book> books = createBookList(createBookCriteria().add(Restrictions.ilike("b.name", bookName, MatchMode.ANYWHERE)));
        return books;
    }

    @Transactional
    @Override
    public List<Book> getBooks(Genre genre) {
        List<Book> books = createBookList(createBookCriteria().add(Restrictions.eq("genreByGenreId.id", genre.getId())));
        return books;
    }

    @Transactional
    @Override
    public List<Book> getBooks(Character letter) {
        List<Book> books = createBookList(createBookCriteria().add(Restrictions.ilike("b.name", letter.toString(), MatchMode.START)));
        return books;

    }

    @Transactional
    @Override
    public Object getFieldValue(Long id, String fieldName) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);
        criteria.setProjection(Property.forName(fieldName));
        criteria.add(Restrictions.eq("id", id));
        return criteria.uniqueResult();
    }

    private DetachedCriteria createBookCriteria() {
        DetachedCriteria bookListCriteria = DetachedCriteria.forClass(Book.class, "b");
        createAliases(bookListCriteria);
        return bookListCriteria;
    }


    private void createAliases(DetachedCriteria criteria) {
        criteria.createAlias("b.authorByAuthorId", "authorByAuthorId");
        criteria.createAlias("b.genreByGenreId", "genreByGenreId");
        criteria.createAlias("b.publisherByPublisherId", "publisherByPublisherId");
    }

    private List<Book> createBookList(DetachedCriteria bookListCriteria) {
        Criteria criteria = bookListCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        criteria.addOrder(Order.asc("b.name")).setProjection(bookProjection).setResultTransformer(Transformers.aliasToBean(Book.class));
        return criteria.list();
    }

}
