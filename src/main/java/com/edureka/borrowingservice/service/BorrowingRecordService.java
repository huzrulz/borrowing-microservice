package com.edureka.borrowingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import com.edureka.borrowingservice.entity.Borrowing;
import com.edureka.borrowingservice.repoistory.BorrowingRepository;

public class BorrowingRecordService {

    @Value("${book.service.url}")
    String bookServiceURL;

    @Autowired
    BorrowingRepository borrowingRecordRepository;

    public Borrowing borrowBook(Book book, String user) throws RuntimeException {
        Book availableBook = null;
        try {
            Object restTemplate;
            ResponseEntity<String> response = restTemplate.getForEntity(bookServiceURL + "/" + book.getId(), String.class);
            availableBook = UtilityMapper.responseToModel(response.getBody());
        } catch (Exception e) {
            throw new Exception("Book Service not up, Something happens! try again");
        }

        if (availableBook.getStatus().equals(BookStatus.AVAILABLE)) {
            Borrowing borrowingRecord = new Borrowing();
            borrowingRecord.setBorrowingDate(LocalDate.now());
            borrowingRecord.setReturnDate(LocalDate.now().minusWeeks(2));
            borrowingRecord.setUser(user);
            borrowingRecord.setBookId(availableBook.getId());
            borrowingRecordRepository.save(borrowingRecord);
            availableBook.setStatus(BookStatus.BORROWED);
            borrowingRecord.setBook(availableBook);
            messageSender.send(UtilityMapper.getJsonString(availableBook));
            return borrowingRecord;
        } else {
            throw new Exception("Book Not available");
        }
    }
}
