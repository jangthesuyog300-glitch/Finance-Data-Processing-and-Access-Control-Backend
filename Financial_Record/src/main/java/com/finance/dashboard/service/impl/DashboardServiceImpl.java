package com.finance.dashboard.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.finance.dashboard.dto.response.DashboardSummary;
import com.finance.dashboard.dto.response.RecordResponse;
import com.finance.dashboard.enums.RecordType;
import com.finance.dashboard.repository.RecordRepository;
import com.finance.dashboard.service.interfaces.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final RecordRepository repo;

    @Override
    public DashboardSummary getSummary() {

        Double income = repo.sumByType(RecordType.INCOME);
        Double expense = repo.sumByType(RecordType.EXPENSE);

        income = income != null ? income : 0;
        expense = expense != null ? expense : 0;

        return DashboardSummary.builder()
                .totalIncome(income)
                .totalExpense(expense)
                .netBalance(income - expense)
                .build();
    }

    @Override
    public Map<String, Double> getCategoryWise() {

        return repo.categoryTotals()
                .stream()
                .collect(Collectors.toMap(
                        r -> (String) r[0],
                        r -> (Double) r[1]
                ));
    }

    @Override
    public Map<Integer, Double> getMonthlyTrends() {

        return repo.monthlyTotals()
                .stream()
                .collect(Collectors.toMap(
                        r -> (Integer) r[0],
                        r -> (Double) r[1]
                ));
    }

    @Override
    public List<RecordResponse> getRecent() {

        return repo.findTop5ByOrderByDateDesc()
                .stream()
                .map(r -> RecordResponse.builder()
                        .id(r.getId())
                        .amount(r.getAmount())
                        .type(r.getType().name())
                        .category(r.getCategory())
                        .date(r.getDate())
                        .notes(r.getNotes())
                        .build())
                .collect(Collectors.toList());
    }
}

