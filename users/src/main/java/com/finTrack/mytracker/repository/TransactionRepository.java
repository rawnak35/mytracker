package com.finTrack.mytracker.repository;

import com.finTrack.mytracker.dto.ValueCountDto;
import com.finTrack.mytracker.entity.Transaction;
import com.finTrack.mytracker.entity.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long id);

    List<Transaction> findByCategory(Category category);

    @Query("SELECT new com.finTrack.mytracker.dto.ValueCountDto(t.category, SUM(t.amount)) FROM Transaction t GROUP BY t.category")
    List<ValueCountDto> groupByCategory();
}
