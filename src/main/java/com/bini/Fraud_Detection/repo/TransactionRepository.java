package com.bini.Fraud_Detection.repo;

import com.bini.Fraud_Detection.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByPhoneNumberAndTimestampAfter(String phoneNumber, LocalDateTime after);
}
