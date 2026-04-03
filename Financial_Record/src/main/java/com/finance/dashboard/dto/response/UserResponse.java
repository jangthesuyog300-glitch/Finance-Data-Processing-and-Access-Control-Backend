package com.finance.dashboard.dto.response;

import com.finance.dashboard.enums.Role;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private Boolean isActive;
}
