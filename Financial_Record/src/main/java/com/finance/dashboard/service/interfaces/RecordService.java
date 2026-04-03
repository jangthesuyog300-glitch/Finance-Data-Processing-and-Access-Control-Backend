package com.finance.dashboard.service.interfaces;

import com.finance.dashboard.dto.request.RecordRequest;
import com.finance.dashboard.dto.response.RecordResponse;

import java.time.LocalDate;
import java.util.List;

public interface RecordService {

    RecordResponse create(RecordRequest request);

    List<RecordResponse> getAll();

    RecordResponse update(Long id, RecordRequest request);

    void delete(Long id);

    List<RecordResponse> filter(
            String type,
            String category,
            LocalDate startDate,
            LocalDate endDate
    );
}