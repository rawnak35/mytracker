package com.finTrack.mytracker.service.impl;

import com.finTrack.mytracker.dto.TransactionDto;
import com.finTrack.mytracker.dto.ValueCountDto;
import com.finTrack.mytracker.entity.Transaction;
import com.finTrack.mytracker.entity.User;
import com.finTrack.mytracker.entity.enums.Category;
import com.finTrack.mytracker.exception.ResourceNotFoundException;
import com.finTrack.mytracker.mapper.TransactionMapper;
import com.finTrack.mytracker.repository.TransactionRepository;
import com.finTrack.mytracker.repository.UserRepository;
import com.finTrack.mytracker.service.TransactionsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {

    private TransactionRepository transactionsRepository;
    private UserRepository userRepository;

    @Override
    public TransactionDto createTrans(TransactionDto transDto) {
        User user = userRepository.findByUsername(transDto.getUsername());
        if(user == null){
            throw new ResourceNotFoundException("User does not exist with given username" + transDto.getUsername());
        }
        Transaction savedTrans = transactionsRepository.save(TransactionMapper.mapToTransaction(transDto, user));
        return TransactionMapper.mapToTransactionDto(savedTrans);
    }

    @Override
    public TransactionDto getById(Long id) {
        Transaction transaction = transactionsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction does not exist with given id: " + id));
        return TransactionMapper.mapToTransactionDto(transaction);
    }

    @Override
    public List<TransactionDto> getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User does not exist with given username: " + username);
        }
        List<Transaction> transaction = transactionsRepository.findByUserId(user.getId());
        return transaction.stream().map(TransactionMapper::mapToTransactionDto).toList();
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        return transactionsRepository.findAll().stream().map(TransactionMapper::mapToTransactionDto).toList();
    }

    @Override
    public TransactionDto updateTransaction(Long id, TransactionDto updatedTrans) {
        Transaction transaction = transactionsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction does not exist with given id: " + id));
        transaction.setAmount(updatedTrans.getAmount());
        transaction.setCategory(updatedTrans.getCategory());
        transaction.setTime(updatedTrans.getTime());
        transaction.setDescription(updatedTrans.getDescription());
        Transaction savedTran = transactionsRepository.save(transaction);
        return TransactionMapper.mapToTransactionDto(savedTran);
    }

    @Override
    public void deleteTransaction(Long id) {
        Transaction transaction = transactionsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction does not exist with given id: " + id));
        transactionsRepository.delete(transaction);
    }

    @Override
    public List<TransactionDto> getByCategory(Category category) {
        List<Transaction> transactions = transactionsRepository.findByCategory(category);
        return transactions.stream().map(TransactionMapper::mapToTransactionDto).toList();
    }

    @Override
    public List<ValueCountDto> getCategoriesTotalAmount() {
        return transactionsRepository.groupByCategory();
    }
}
