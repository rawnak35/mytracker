package com.finTrack.mytracker.mapper;

import com.finTrack.mytracker.dto.TransactionDto;
import com.finTrack.mytracker.entity.Transaction;
import com.finTrack.mytracker.entity.User;

public class TransactionMapper {

    public static TransactionDto mapToTransactionDto(Transaction trans){
        return new TransactionDto(trans.getId(), trans.getUser().getUsername(), trans.getCategory(), trans.getDescription(), trans.getAmount(), trans.getTime());
    }

    public static Transaction mapToTransaction(TransactionDto transDto, User user){
        return new Transaction(transDto.getId(), user, transDto.getCategory(), transDto.getDescription(), transDto.getAmount(), transDto.getTime());
    }
}
