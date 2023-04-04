package com.example.salarying.Admin.User.service;

import com.example.salarying.Admin.User.dto.AdminDTO;
import com.example.salarying.Admin.User.entity.Admin;
import com.example.salarying.Admin.User.repository.AdminRepository;
import com.example.salarying.Corporation.User.exception.UserException;
import com.example.salarying.Corporation.User.exception.UserExceptionType;
import com.example.salarying.Corporation.User.repository.MemberRepository;
import com.example.salarying.global.jwt.auth.AuthToken;
import com.example.salarying.global.jwt.auth.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenProvider authTokenProvider;
    private final MemberRepository memberRepository;


    /**
     * 관리자 로그인 기능구현
     * @param request : 관리자 email, password
     * @return : accces token
     * passwordEncoder를 통해 비밀번호 확인 후 해당 멤버의 마지막 로그인 시간 update
     */
    @Override
    @Transactional
    public AdminDTO.LoginResponse login(AdminDTO.LoginRequest request) {
        Admin admin = adminRepository.findByAdminEmail(request.getEmail())
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_EXIST_ACCOUNT));

        if(passwordEncoder.matches(request.getPassword(),admin.getAdminPassword())){
            admin.updateLoginDate();
            AuthToken authToken = authTokenProvider.issueAdminAccessToken(admin);
            adminRepository.save(admin);
            AdminDTO.LoginResponse response = new AdminDTO.LoginResponse(authToken.getToken(), admin);
            return response;
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
        Admin admin = findAdminById(Id);

        if(passwordEncoder.matches(request.getPassword(), admin.getAdminPassword())){
            return "비밀번호가 일치합니다.";
        }
        else {
            throw  new UserException(UserExceptionType.UNMATCHED_PASSWORD);
        }
    }

    /**
     * 사용자 비밀번호 변경 함수
     * @param Id : 사용자 ID
     * @param request : 새로운 비밀번호
     * @return : 변경 성공 여부
     */
    @Override
    @Transactional
    public String changePassword(Long Id, AdminDTO.ChangeRequest request) {

        Admin admin = findAdminById(Id);

        if(passwordEncoder.matches(request.getPassword(),admin.getAdminPassword())){
            throw new UserException(UserExceptionType.ALREADY_USED);
        }

        if(request.getPassword() != null && !request.getPassword().equals("")){
            admin.updatePassword(passwordEncoder, request.getPassword());
            adminRepository.save(admin);
            return "비밀번호 변경 성공";
        }
        else{
            throw new UserException(UserExceptionType.EMPTY_PASSWORD);
        }
    }

    /**
     * 담당기업 리스트 출력 함수
     * 관리자의 경우 담당 기업의 리스트, 슈퍼관리자의 경우 모든 기업의 리스트를 출력
     * @param Id : 사용자 정보
     * @return : 담당 기업 리스트
     */
    @Override
    public List<AdminDTO.ListResponse> manageList(Long Id) {

        Admin admin = findAdminById(Id);

        List<AdminDTO.ListResponse> listResponses = new ArrayList<>();
        if(admin.getRole().equals("SUPERADMIN")){
            listResponses = memberRepository.findAll()
                    .stream()
                    .map(entity -> new AdminDTO.ListResponse(entity))
                    .collect(Collectors.toList());
        }
        else {
            listResponses = memberRepository.findByAdmin_Id(Id)
                    .stream()
                    .map(entity -> new AdminDTO.ListResponse(entity))
                    .collect(Collectors.toList());
        }

        return listResponses;
    }

    public Admin findAdminById(Long Id){

        Optional<Admin> admin = adminRepository.findAdminById(Id);
        if(admin.isPresent())
            return admin.get();
        else throw new UserException(UserExceptionType.NOT_LOGGED_IN);

    }

}
