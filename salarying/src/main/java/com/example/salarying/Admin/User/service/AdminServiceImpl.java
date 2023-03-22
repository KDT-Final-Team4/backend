package com.example.salarying.Admin.User.service;

import com.example.salarying.Admin.User.dto.AdminDTO;
import com.example.salarying.Admin.User.entity.Admin;
import com.example.salarying.Admin.User.repository.AdminRepository;
import com.example.salarying.Corporation.User.exception.UserException;
import com.example.salarying.Corporation.User.exception.UserExceptionType;
import com.example.salarying.global.jwt.auth.AuthToken;
import com.example.salarying.global.jwt.auth.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenProvider authTokenProvider;


    @Override
    public String login(AdminDTO.LoginRequest request) {
        Admin admin = adminRepository.findByAdminEmail(request.getEmail())
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_EXIST_ACCOUNT));

        if(passwordEncoder.matches(request.getPassword(),admin.getAdminPassword())){
            AuthToken authToken = authTokenProvider.issueAdminAccessToken(admin);
            return authToken.getToken();
        }else throw new UserException(UserExceptionType.UNMATCHED_PASSWORD);
    }
}
