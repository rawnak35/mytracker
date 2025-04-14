package com.finTrack.mytracker.controller;

import com.finTrack.mytracker.dto.TransactionDto;
import com.finTrack.mytracker.service.TransactionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {

    private TransactionsService transactionsService;

    @PostMapping("/create")
    public ResponseEntity<TransactionDto> create(@RequestBody TransactionDto transactionDto) {
        TransactionDto savedTrans = transactionsService.createTrans(transactionDto);
        return new ResponseEntity<>(savedTrans, HttpStatus.CREATED);
    }

}
