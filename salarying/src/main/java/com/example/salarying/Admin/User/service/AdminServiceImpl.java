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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

    /**
     * 사용자 정보를 수정하기 전 사용자 확인을 위해 비밀번호를 재확인하는 함수
     * @param Id : 사용자 ID
     * @param request : 비밀번호
     * @return : 비밀번호 일치 여부
     */
    @Override
    public String checkPassword(Long Id, AdminDTO.CheckRequest request) {
        Admin admin = adminRepository.findAdminById(Id)
                .orElseThrow(()-> new UserException(UserExceptionType.NOT_LOGGED_IN));

        if(passwordEncoder.matches(request.getPassword(), admin.getAdminPassword())){
            return "비밀번호가 일치합니다.";
        }
        else {
            throw  new UserException(UserExceptionType.UNMATCHED_PASSWORD);
        }
    }

}
