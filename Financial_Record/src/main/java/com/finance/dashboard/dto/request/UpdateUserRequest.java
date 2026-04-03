package com.finance.dashboard.dto.request;

import com.finance.dashboard.enums.Role;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private Role role;
    private Boolean isActive;
}
