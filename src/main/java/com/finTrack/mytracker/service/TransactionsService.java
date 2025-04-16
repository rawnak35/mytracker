package com.finTrack.mytracker.service;


import com.finTrack.mytracker.dto.TransactionDto;

import java.util.List;

public interface TransactionsService {
    TransactionDto createTrans(TransactionDto transDto);
    TransactionDto getById(Long id);
    List<TransactionDto> getByUsername(String username);
    List<TransactionDto> getAllTransactions();
    TransactionDto updateTransaction(Long id, TransactionDto updatedTrans);
    void deleteTransaction(Long id);

}
