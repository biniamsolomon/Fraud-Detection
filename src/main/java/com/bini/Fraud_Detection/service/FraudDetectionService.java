package com.bini.Fraud_Detection.service;


import com.bini.Fraud_Detection.entity.Transaction;
import com.bini.Fraud_Detection.repo.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class FraudDetectionService {

    private final TransactionRepository repository;

    private static final List<String> BLACKLISTED_NUMBERS = Arrays.asList("+251911000000", "+251922000000");
    private static final double MAX_ALLOWED_AMOUNT = 10000.0;
    private static final int MAX_TRANSACTIONS_PER_MIN = 5;

    public FraudDetectionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Transaction analyze(Transaction tx) {
        boolean isFraud = false;

        if (BLACKLISTED_NUMBERS.contains(tx.getPhoneNumber())) {
            isFraud = true;
        }

        if (tx.getAmount() > MAX_ALLOWED_AMOUNT) {
            isFraud = true;
        }

        LocalDateTime oneMinuteAgo = LocalDateTime.now().minusMinutes(1);
        List<Transaction> recent = repository.findByPhoneNumberAndTimestampAfter(tx.getPhoneNumber(), oneMinuteAgo);

        if (recent.size() >= MAX_TRANSACTIONS_PER_MIN) {
            isFraud = true;
        }

        tx.setFraudulent(isFraud);
        return repository.save(tx);
    }
}
