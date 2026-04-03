package com.finance.dashboard.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RecordResponse {
    private Long id;
    private Double amount;
    private String type;
    private String category;
    private LocalDate date;
    private String notes;
}