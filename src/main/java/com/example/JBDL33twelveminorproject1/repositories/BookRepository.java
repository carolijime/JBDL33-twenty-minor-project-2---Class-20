package com.example.JBDL33twelveminorproject1.repositories;

import com.example.JBDL33twelveminorproject1.models.Book;
import com.example.JBDL33twelveminorproject1.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    //select * from book where name = ?1
    List<Book> findByName(String name);

    //select * from book, author where author.name = ?1 and author.id = book.author_id
    List<Book> findByAuthor_Name(String AuthorName);

    //select * from book where genre = ?1
    List<Book> findByGenre(Genre genre);

//    @Query("select b from Book b where b.:filterType = ?2")
//    List<Book> findEqualsGeneric(String filterType, String value);
}
