package com.finance.dashboard.repository;

import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.enums.RecordType;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RecordRepository extends JpaRepository<FinancialRecord, Long> {

    @Query("SELECT SUM(r.amount) FROM FinancialRecord r WHERE r.type = :type")
    Double sumByType(@Param("type") RecordType type);

    @Query("SELECT r.category, SUM(r.amount) FROM FinancialRecord r GROUP BY r.category")
    List<Object[]> categoryTotals();

    @Query("SELECT MONTH(r.date), SUM(r.amount) FROM FinancialRecord r GROUP BY MONTH(r.date)")
    List<Object[]> monthlyTotals();

    List<FinancialRecord> findTop5ByOrderByDateDesc();

    @Query("""
        SELECT r FROM FinancialRecord r
        WHERE (:type IS NULL OR r.type = :type)
        AND (:category IS NULL OR r.category = :category)
        AND (:startDate IS NULL OR r.date >= :startDate)
        AND (:endDate IS NULL OR r.date <= :endDate)
    """)
    List<FinancialRecord> filterRecords(
            @Param("type") RecordType type,
            @Param("category") String category,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}