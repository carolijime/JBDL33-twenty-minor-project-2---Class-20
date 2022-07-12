package com.example.JBDL33twelveminorproject1.request;

import com.example.JBDL33twelveminorproject1.models.Author;
import com.example.JBDL33twelveminorproject1.models.Book;
import com.example.JBDL33twelveminorproject1.models.Genre;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCreateRequest {

    @NotBlank //does not accept blanks nor nulls
    private String name;

    @NotNull
    private Author author;

    @Positive
    private int cost;

    @NotNull
    private Genre genre;

    public Book to(){
        //convert BookCreateRequest to a book object
        return Book.builder()
                .cost(this.cost)
                .genre(this.genre)
                .name(this.name)
                .author(this.author)
                .build();
    }

}
