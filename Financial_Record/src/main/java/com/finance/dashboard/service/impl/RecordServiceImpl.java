package com.finance.dashboard.service.impl;

import com.finance.dashboard.dto.request.RecordRequest;
import com.finance.dashboard.dto.response.RecordResponse;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.enums.RecordType;
import com.finance.dashboard.exception.ResourceNotFoundException;
import com.finance.dashboard.repository.RecordRepository;
import com.finance.dashboard.repository.UserRepository;
import com.finance.dashboard.service.interfaces.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository repo;
    private final UserRepository userRepository;

    @Override
    public RecordResponse create(RecordRequest req) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        FinancialRecord r = FinancialRecord.builder()
                .amount(req.getAmount())
                .type(RecordType.valueOf(req.getType()))
                .category(req.getCategory())
                .date(req.getDate())
                .notes(req.getNotes())
                .createdBy(user)
                .build();

        return mapToResponse(repo.save(r));
    }

    @Override
    public List<RecordResponse> getAll() {
        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RecordResponse update(Long id, RecordRequest req) {
        FinancialRecord r = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        r.setAmount(req.getAmount());
        r.setType(RecordType.valueOf(req.getType()));
        r.setCategory(req.getCategory());
        r.setDate(req.getDate());
        r.setNotes(req.getNotes());
        r.setCreatedBy(user);

        return mapToResponse(repo.save(r));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<RecordResponse> filter(String type, String category,
                                       LocalDate startDate, LocalDate endDate) {

        RecordType recordType = type != null ? RecordType.valueOf(type) : null;

        return repo.filterRecords(recordType, category, startDate, endDate)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RecordResponse mapToResponse(FinancialRecord r) {
        return RecordResponse.builder()
                .id(r.getId())
                .amount(r.getAmount())
                .type(r.getType().name())
                .category(r.getCategory())
                .date(r.getDate())
                .notes(r.getNotes())
                .build();
    }
}