package com.example.salarying.Admin.User.service;

import com.example.salarying.Admin.User.dto.AdminDTO;

public interface AdminService {

    String login(AdminDTO.LoginRequest request);

    String checkPassword(Long Id, AdminDTO.CheckRequest request);

}
