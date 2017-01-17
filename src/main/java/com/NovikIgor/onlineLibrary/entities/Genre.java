package com.NovikIgor.onlineLibrary.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by nolik on 15.01.17.
 */
@Entity
public class Genre {
    private long id;
    private String name;
    private Long parent;
    private Collection<Book> booksById;
    private Author authorByParent;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "parent")
    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (id != genre.id) return false;
        if (name != null ? !name.equals(genre.name) : genre.name != null) return false;
        if (parent != null ? !parent.equals(genre.parent) : genre.parent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "genreByGenreId")
    public Collection<Book> getBooksById() {
        return booksById;
    }

    public void setBooksById(Collection<Book> booksById) {
        this.booksById = booksById;
    }

    @ManyToOne
    @JoinColumn(name = "parent", referencedColumnName = "id")
    public Author getAuthorByParent() {
        return authorByParent;
    }

    public void setAuthorByParent(Author authorByParent) {
        this.authorByParent = authorByParent;
    }
}
