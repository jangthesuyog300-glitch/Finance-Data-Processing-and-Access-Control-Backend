package com.finance.dashboard.service.interfaces;

import java.util.List;
import java.util.Map;

import com.finance.dashboard.dto.response.DashboardSummary;
import com.finance.dashboard.dto.response.RecordResponse;

public interface DashboardService {

    DashboardSummary getSummary();

    Map<String, Double> getCategoryWise();

    Map<Integer, Double> getMonthlyTrends();

    List<RecordResponse> getRecent();
}
