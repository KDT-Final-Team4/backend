package com.example.salarying.Admin.Terms.service;

import com.example.salarying.Admin.Terms.dto.TermsDTO;
import com.example.salarying.Admin.Terms.entity.Terms;
import com.example.salarying.Admin.Terms.exception.TermsException;
import com.example.salarying.Admin.Terms.exception.TermsExceptionType;
import com.example.salarying.Admin.Terms.repository.TermsRepository;
import com.example.salarying.Admin.User.entity.Admin;
import com.example.salarying.Admin.User.repository.AdminRepository;
import com.example.salarying.Corporation.User.exception.UserException;
import com.example.salarying.Corporation.User.exception.UserExceptionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TermsServiceImpl implements TermsService{

    private final TermsRepository termsRepository;
    private final AdminRepository adminRepository;

    /**
     * 약관 타입 별 버전 비교 후 등록하는 함수
     * @param Id : 사용자 확인을 위한 id 값
     * @param request : 약관 등록 dto
     * @return : 약관 등록 성공 여부
     */
    @Override
    @Transactional
    public String insertTerm(Long Id, TermsDTO.AddTermRequest request) {

        if(!termsRepository.existsTermsByTypeAndVersion(request.getType(), request.getVersion())){

            Admin admin = adminRepository.findAdminById(Id)
                    .orElseThrow(() -> new UserException(UserExceptionType.NOT_LOGGED_IN));

            Terms newTerms = request.toEntity(admin);
            termsRepository.save(newTerms);

            return "약관 등록 완료";
        }
        else{
            throw new TermsException(TermsExceptionType.ALREADY_EXIST);
        }
    }
}
