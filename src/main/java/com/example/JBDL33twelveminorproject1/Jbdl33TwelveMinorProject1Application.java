package com.example.JBDL33twelveminorproject1;

import com.example.JBDL33twelveminorproject1.models.Admin;
import com.example.JBDL33twelveminorproject1.models.Author;
import com.example.JBDL33twelveminorproject1.models.MyUser;
import com.example.JBDL33twelveminorproject1.repositories.AdminRepository;
import com.example.JBDL33twelveminorproject1.repositories.AuthorRepository;
import com.example.JBDL33twelveminorproject1.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

//implements CommandLineRunner interface will ask you to create a void function
//then we can create a function that will run just after the main class has run
@SpringBootApplication
public class Jbdl33TwelveMinorProject1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Jbdl33TwelveMinorProject1Application.class, args);
	}

//	@Autowired
//	AuthorRepository authorRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MyUserRepository myUserRepository;

	@Autowired
	AdminRepository adminRepository;

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("In run function of main class");
//		List<Author> authorList = authorRepository
//				.findByAgeGreaterThanEqualAndCountryAndNameStartingWith(30, "India", "P");

//		authorList.forEach(x -> System.out.println(x)); //works with out without ToSring function in Author
		// //authorList.forEach(System.out::println); //fails because book list in author is empty if booklist in author is being loaded as lazy (has to be eager)
//		authorList.stream() //works
//				.map(author -> author.getBookList())
//				.forEach(books -> System.out.println(books.size()));


		//---------------IMPORTANT BELOW----------------------------------------------//
		//create first Admin user (before this first admin user we cannot create other admin users)
		//create linked users first

//		MyUser myUser = MyUser.builder()
//				.username("raj")
//				.password(passwordEncoder.encode("raj123"))
//				.authority("adm")
//				.build();
//
//		myUser = myUserRepository.save(myUser);
//
//		Admin admin = Admin.builder()
//				.age(40)
//				.name("Raj Shukla")
//				.myUser(myUser)
//				.build();
//
//		adminRepository.save(admin);

	}
}
