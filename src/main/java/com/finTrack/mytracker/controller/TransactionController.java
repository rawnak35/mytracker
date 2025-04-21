package com.finTrack.mytracker.controller;

import com.finTrack.mytracker.dto.TransactionDto;
import com.finTrack.mytracker.dto.ValueCountDto;
import com.finTrack.mytracker.service.TransactionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("{id}")
    public ResponseEntity<TransactionDto> getById(@PathVariable("id") Long id) {
        TransactionDto tran = transactionsService.getById(id);
        return ResponseEntity.ok(tran);
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<List<TransactionDto>> getByUsername(@PathVariable("username") String username) {
        List<TransactionDto> tran = transactionsService.getByUsername(username);
        return ResponseEntity.ok(tran);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TransactionDto>> getAllUsers() {
        List<TransactionDto> transactions = transactionsService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable("id") Long id, @RequestBody TransactionDto updatedTransaction) {
        TransactionDto transactionDto = transactionsService.updateTransaction(id, updatedTransaction);
        return ResponseEntity.ok(transactionDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") Long id) {
        transactionsService.deleteTransaction(id);
        return ResponseEntity.ok("Transaction was deleted successfully");
    }

    @GetMapping("/categoriesAmount")
    public  ResponseEntity<List<ValueCountDto>> getCategoriesAmount(){
        List<ValueCountDto> res = transactionsService.getCategoriesTotalAmount();
        return ResponseEntity.ok(res);
    }


}
