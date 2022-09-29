package com.example.JBDL33twelveminorproject1.services;

import com.example.JBDL33twelveminorproject1.exceptions.TxnServiceException;
import com.example.JBDL33twelveminorproject1.models.Book;
import com.example.JBDL33twelveminorproject1.models.Student;
import com.example.JBDL33twelveminorproject1.models.Transaction;
import com.example.JBDL33twelveminorproject1.models.TransactionType;
import com.example.JBDL33twelveminorproject1.repositories.TransactionRepository;
import com.example.JBDL33twelveminorproject1.request.BookFilterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {

    private static Logger logger = LoggerFactory.getLogger(TxnService.class);

    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    TransactionRepository transactionRepository;

    @Value("${book.return.due_date}")//get "constant" value from property class
    int number_of_days;


//    @Transactional //NEEDS TO BE PROPERLY ADDED so only one transaction at the time (in case 2 students want to issue the same book)
    public String issueTxn(int studentId, int bookId) throws TxnServiceException, InterruptedException {
        /**
         * 1. Student is a valid entity
         * 2. Book is present and is available
         * 3. Create a transaction --> saving in the txn table
         * 4. Make the book unavailable
         */

        logger.info("Issue request came: studentId - {}, bookId - {}", studentId, bookId);

        //below commented code is only for showing an example
//        Transaction dummyTxn = Transaction.builder()
//                .externalTxnId(UUID.randomUUID().toString())
//                .transactionType(TransactionType.DUMMY)
//                .build();
//
//        transactionRepository.save(dummyTxn);

//        Thread.sleep(10000); //10 seconds sleep

//        logger.info("After sleeping in Issue request came: studentId - {}, bookId - {}", studentId, bookId);

        Student student = studentService.findStudentByStudentID(studentId);

        if(student == null){
            throw new TxnServiceException("Student not present in the library");
        }

        //student is present in the DB (valid entity) at this point
        //book list has only one element
        //get the first and only element in the list
        List<Book> books = bookService.find(BookFilterType.BOOK_ID, String.valueOf(bookId));
        if(books == null || books.size() != 1 || books.get(0).getStudent() != null){
            //book or is not present in the library (no valid entity) or someone else already issued this book
            throw new TxnServiceException("Book not present in the library");
        }

        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .payment(books.get(0).getCost())
                .book(books.get(0))
                .student(student)
                .build();

        transactionRepository.save(transaction);

        //assign student to book
        books.get(0).setStudent(student);
        bookService.createOrUpdate(books.get(0));

        return transaction.getExternalTxnId();
    }

    public String returnTxn(int studentId, int bookId) throws TxnServiceException {
        /**
         * 1. Student is a valid entity
         * 2. Book is issued to this particular student
         * 3. Calculate the fine
         * 4. Create a transaction --> saving in the txn table
         * 5. Make the book available
         */

        Student student = studentService.findStudentByStudentID(studentId);

        if(student == null){
            throw new TxnServiceException("Student not present in the library");
        }

        //student is present in the DB (valid entity) at this point
        //book list has only one element
        //get the first and only element in the list
        List<Book> books = bookService.find(BookFilterType.BOOK_ID, String.valueOf(bookId));
        if(books == null || books.size() != 1){
            //book or is not present in the library (no valid entity)
            throw new TxnServiceException("Book not present in the library");
        }

        if(books.get(0).getStudent().getId() != studentId){
            //books is not issued by the student
            throw new TxnServiceException("Book is not issued to this student");
        }

        //Finding the original issue Txn
        //select * from transaction where my_book_id = ? and student_id =? and type = 'ISSUE'
        //ORDER BY transaction_date DESC

        Transaction issueTxn = transactionRepository
                .findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(books.get(0), student, TransactionType.ISSUE);

        Transaction transaction = Transaction.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.RETURN)
                .book(books.get(0))
                .student(student)
                .payment(calculateFine(issueTxn))
                .build();

        transactionRepository.save(transaction);

        //make book available
        books.get(0).setStudent(null);
        bookService.createOrUpdate(books.get(0));

        return transaction.getExternalTxnId();
    }

    private double calculateFine(Transaction issueTxn){
        long issueTime = issueTxn.getTransactionDate().getTime();
        long returnTime = System.currentTimeMillis();

        long diff = returnTime - issueTime;

        long daysPassed = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(daysPassed >= number_of_days) {
            //applying a simple fine of 1 unit per delayed days of return of the book
            return (daysPassed - number_of_days) * 1.0;
        }

        return 0.0;

    }
}
