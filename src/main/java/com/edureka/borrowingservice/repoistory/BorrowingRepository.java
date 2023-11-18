package com.edureka.borrowingservice.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edureka.borrowingservice.entity.Borrowing;

public interface BorrowingRepository extends JpaRepository <Borrowing, Long> {
    
}
