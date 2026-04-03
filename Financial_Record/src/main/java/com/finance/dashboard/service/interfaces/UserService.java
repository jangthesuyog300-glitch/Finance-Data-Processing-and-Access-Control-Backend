package com.finance.dashboard.service.interfaces;

import com.finance.dashboard.dto.request.UpdateUserRequest;
import com.finance.dashboard.dto.request.UserRequest;
import com.finance.dashboard.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    UserResponse register(UserRequest req);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UpdateUserRequest req);
}
