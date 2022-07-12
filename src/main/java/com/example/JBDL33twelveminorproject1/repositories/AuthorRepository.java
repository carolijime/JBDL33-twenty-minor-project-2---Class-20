package com.example.JBDL33twelveminorproject1.repositories;

import com.example.JBDL33twelveminorproject1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    /*
    there are 2 ways to write/execute our SQL queries
    1. Native query
        @Query(value = "select a from author a where a.email = ?1", nativeQuery = true)

    2. JPQL - Java Persistence Query Language
        @Query("select a from Author a where a.email = ?1")

    Compiler does not check native queries, but it does check the JPQL queries (if error, project will not start)
    JPQL is the recommended way to go

    3. Don't have ot write the query...
     */

    //3. third "no write SQL" method
    Author findByEmail(String email);

    //1. Native Query (native query is by default set to false)
    @Query(value = "select a from author a where a.email = ?1", nativeQuery = true) //parameter number //to write our own queries
    public  Author getAuthorGivenEmailIdNativeQuery(String author_email);

    //2. JPQL method
    @Query("select a from Author a where a.email = ?1")
    public  Author getAuthorGivenEmailIdJPQL(String author_email); //if no records with that email, returns null



    //You need to find all authors in India
    //Native query
    @Query(value = "select a from author a where a.land = ?1", nativeQuery = true)
    public List<Author> getAuthorsByCountry(String country);

    //JPQL query
    @Query(value = "select a from Author a where a.country = ?1")
    public List<Author> getAuthorsByCountryJPQL(String country);

    //3rd method
    //you have to find all the authors above the age of 30, living in India and their name starting with A
    //write the name of the function as the query and pass the needed parameter
    //Function below automatically will "create" the below SQL Query
    //select * from author where age >= ?1 and country = ?2 and name like ?3%;
    List<Author> findByAgeGreaterThanEqualAndCountryAndNameStartingWith
        (int age, String country, String prefix);
}
