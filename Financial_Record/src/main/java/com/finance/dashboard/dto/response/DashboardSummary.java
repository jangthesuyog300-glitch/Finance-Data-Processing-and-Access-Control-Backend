package com.finance.dashboard.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardSummary {
    private Double totalIncome;
    private Double totalExpense;
    private Double netBalance;
}

