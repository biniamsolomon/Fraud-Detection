package com.bini.Fraud_Detection.controller;


import com.bini.Fraud_Detection.entity.Transaction;
import com.bini.Fraud_Detection.service.FraudDetectionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final FraudDetectionService fraudService;

    public TransactionController(FraudDetectionService fraudService) {
        this.fraudService = fraudService;
    }

    @PostMapping
    public Transaction checkTransaction(@RequestBody Transaction transaction) {
        transaction.setTimestamp(java.time.LocalDateTime.now());
        return fraudService.analyze(transaction);
    }
}
