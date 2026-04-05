package com.finance.dashboard.util;

import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.enums.RecordType;
import com.finance.dashboard.enums.Role;
import com.finance.dashboard.repository.RecordRepository;
import com.finance.dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RecordRepository recordRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (recordRepository.count() == 0) {
            log.info("Seeding sample data...");

            // 1. Ensure a default user exists
            User user = userRepository.findByEmail("test@gmail.com")
                    .orElseGet(() -> {
                        User newUser = User.builder()
                                .name("Admin User")
                                .email("test@gmail.com")
                                .password(passwordEncoder.encode("password123"))
                                .role(Role.ROLE_ADMIN)
                                .isActive(true)
                                .build();
                        return userRepository.save(newUser);
                    });


            // 2. Create 10 sample records
            List<FinancialRecord> records = Arrays.asList(
                    createRecord(50000.0, RecordType.INCOME, "Salary", LocalDate.now().minusMonths(1), "Monthly Salary", user),
                    createRecord(15000.0, RecordType.EXPENSE, "Rent", LocalDate.now().minusDays(25), "Monthly Rent", user),
                    createRecord(2000.0, RecordType.EXPENSE, "Grocery", LocalDate.now().minusDays(20), "Weekly Groceries", user),
                    createRecord(1200.0, RecordType.EXPENSE, "Utilities", LocalDate.now().minusDays(18), "Electricity and Water", user),
                    createRecord(5000.0, RecordType.INCOME, "Freelance", LocalDate.now().minusDays(15), "Web Development Project", user),
                    createRecord(800.0, RecordType.EXPENSE, "Transport", LocalDate.now().minusDays(12), "Ours/Bus Fare", user),
                    createRecord(3000.0, RecordType.EXPENSE, "Entertainment", LocalDate.now().minusDays(10), "Netflix and Games", user),
                    createRecord(1500.0, RecordType.EXPENSE, "Food", LocalDate.now().minusDays(8), "Dinner Out", user),
                    createRecord(25000.0, RecordType.INCOME, "Bonus", LocalDate.now().minusDays(5), "Performance Bonus", user),
                    createRecord(500.0, RecordType.EXPENSE, "Others", LocalDate.now().minusDays(1), "Stationery", user)
            );

            recordRepository.saveAll(records);
            log.info("Successfully seeded 10 sample records.");
        } else {
            log.info("Database already contains data, skipping seeding.");
        }
    }

    private FinancialRecord createRecord(Double amount, RecordType type, String category, LocalDate date, String notes, User user) {
        return FinancialRecord.builder()
                .amount(amount)
                .type(type)
                .category(category)
                .date(date)
                .notes(notes)
                .createdBy(user)
                .build();
    }
}
