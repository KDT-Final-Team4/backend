package com.example.salarying.Admin.User.service;

import com.example.salarying.Admin.User.dto.AdminDTO;

public interface AdminService {

    AdminDTO.LoginResponse login(AdminDTO.LoginRequest request);

    String checkPassword(Long Id, AdminDTO.CheckRequest request);

    String changePassword(Long Id, AdminDTO.ChangeRequest request);

}
