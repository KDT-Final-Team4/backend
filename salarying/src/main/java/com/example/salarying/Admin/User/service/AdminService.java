package com.example.salarying.Admin.User.service;

import com.example.salarying.Admin.User.dto.AdminDTO;
import com.example.salarying.Admin.User.entity.Admin;

import java.util.List;

public interface AdminService {

    AdminDTO.LoginResponse login(AdminDTO.LoginRequest request);

    String checkPassword(Long Id, AdminDTO.CheckRequest request);

    String changePassword(Long Id, AdminDTO.ChangeRequest request);

    List<AdminDTO.ListResponse> manageList(Long Id);

    Admin findAdminById(Long Id);

}
