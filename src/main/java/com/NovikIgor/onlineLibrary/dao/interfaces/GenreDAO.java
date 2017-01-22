package com.NovikIgor.onlineLibrary.dao.interfaces;

import com.NovikIgor.onlineLibrary.entities.Genre;

import java.util.List;

/**
 * Created by nolik on 21.01.17.
 */
public interface GenreDAO {

    List<Genre> getGenres();

}
