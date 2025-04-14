package com.finTrack.mytracker.service.impl;

import com.finTrack.mytracker.dto.TransactionDto;
import com.finTrack.mytracker.entity.Transaction;
import com.finTrack.mytracker.entity.User;
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
       User user =  userRepository.findById(transDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User does not exist with given username" + transDto.getUserId()));
       Transaction savedTrans = transactionsRepository.save(TransactionMapper.mapToTransaction(transDto, user));
       return  TransactionMapper.mapToTransactionDto(savedTrans);
    }

    @Override
    public TransactionDto getById(Long id) {
        return null;
    }

    @Override
    public List<TransactionDto> getByUsername(String username) {
        return List.of();
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        return List.of();
    }

    @Override
    public TransactionDto updateTransaction(Long id, TransactionDto updatedTrans) {
        return null;
    }

    @Override
    public void deleteTrans(Long id) {

    }
}
