package com.finance.dashboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.dashboard.dto.response.DashboardSummary;
import com.finance.dashboard.dto.response.RecordResponse;
import com.finance.dashboard.service.interfaces.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService service;

    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public DashboardSummary summary() {
        return service.getSummary();
    }

    @GetMapping("/category-wise")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public Map<String, Double> categoryWise() {
        return service.getCategoryWise();
    }

    @GetMapping("/trends")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public Map<Integer, Double> trends() {
        return service.getMonthlyTrends();
    }

    @GetMapping("/recent")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public List<RecordResponse> recent() {
        return service.getRecent();
    }
}
