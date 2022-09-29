package com.example.JBDL33twelveminorproject1.services;

import com.example.JBDL33twelveminorproject1.models.Author;
import com.example.JBDL33twelveminorproject1.models.Book;
import com.example.JBDL33twelveminorproject1.models.Genre;
import com.example.JBDL33twelveminorproject1.repositories.AuthorRepository;
import com.example.JBDL33twelveminorproject1.repositories.BookRepository;
import com.example.JBDL33twelveminorproject1.request.BookCreateRequest;
import com.example.JBDL33twelveminorproject1.request.BookFilterType;
import com.example.JBDL33twelveminorproject1.request.BookSearchOperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

//    //wiring custom property
//    @Value("${custom.my_prop}")
//    String sampleText;
//
//    @GetMapping("/sample")
//    public String getSampleText(){
//        return this.sampleText;
//    }

    public void createOrUpdate(BookCreateRequest bookCreateRequest){
        Book book = bookCreateRequest.to();
        createOrUpdate(book);
    }

    public void createOrUpdate(Book book){
        Author author = book.getAuthor();

        //Find if the author with the given email exists in DB or not
        // If exists, then don't save, else save it in the DB first

        // use JPQL query method
//        Author authorFromDB = authorRepository.getAuthorGivenEmailIdJPQL(author.getEmail());
        Author authorFromDB = authorRepository.findByEmail(author.getEmail());

        if(authorFromDB == null){
            //Save author if it does not exist in the DB before saving book (author is a dependency)
            authorFromDB = authorRepository.save(author);
        }


//        author.setId(1); //just for explanation
//        book.setAuthor(author); // Not required

        //set author again with the updated information from the DB, which will have the ID
        book.setAuthor(authorFromDB);

        //save book
        bookRepository.save(book);
    }

    public List<Book> find(BookFilterType bookFilterType, String value){
        switch (bookFilterType) {
            case NAME:
                return bookRepository.findByName(value);
            case AUTHOR_NAME:
                return bookRepository.findByAuthor_Name(value);
            case GENRE:
                return bookRepository.findByGenre(Genre.valueOf(value));
            case BOOK_ID:
                return bookRepository.findAllById(Collections.singletonList(Integer.parseInt(value)));
            default:
                return  new ArrayList<>();
        }
    }

    public List<Book> find2(BookFilterType bookFilterType, String value, BookSearchOperationType bookSearchOperationType){

        //TOD: Modify this function
        switch (bookFilterType) {
            case NAME:
                return bookRepository.findByName(value);
            case AUTHOR_NAME:
                return bookRepository.findByAuthor_Name(value);
            case GENRE:
                return bookRepository.findByGenre(Genre.valueOf(value));
            default:
                return  new ArrayList<>();
        }
    }
    public List<Book> find3(List<BookFilterType> bookFilterType,
                            List<String> value,
                            List<BookSearchOperationType> bookSearchOperationType){
        //first query for type == Equals
        //findEqualsGeneric --> select b from Book b where ?1 = ?1
//        List<Book> books = IntStream.range(0, bookFilterType.size())
//                .filter(i -> bookSearchOperationType.get(i) == BookSearchOperationType.EQUALS)
//                .mapToObj(i -> bookRepository.findEqualsGeneric(bookFilterType.get(i), value.get(i)))
//                .collect(Collectors.toList());

        //TOD: Modify this function
//        switch (bookFilterType) {
//            case NAME:
//                return bookRepository.findByName(value);
//            case AUTHOR_NAME:
//                return bookRepository.findByAuthor_Name(value);
//            case GENRE:
//                return bookRepository.findByGenre(Genre.valueOf(value));
//            default:
//                return  new ArrayList<>();
//        }

        return null;
    }

}
