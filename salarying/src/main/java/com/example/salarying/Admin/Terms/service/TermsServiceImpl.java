package com.example.salarying.Admin.Terms.service;

import com.example.salarying.Admin.Terms.dto.TermsDTO;
import com.example.salarying.Admin.Terms.entity.Terms;
import com.example.salarying.Admin.Terms.exception.TermsException;
import com.example.salarying.Admin.Terms.exception.TermsExceptionType;
import com.example.salarying.Admin.Terms.repository.TermsRepository;
import com.example.salarying.Admin.User.entity.Admin;
import com.example.salarying.Admin.User.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TermsServiceImpl implements TermsService{

    private final TermsRepository termsRepository;
    private final AdminService adminService;

    /**
     * 약관 타입 별 버전 비교 후 등록하는 함수
     * @param Id : 사용자 확인을 위한 id 값
     * @param request : 약관 등록 dto
     * @return : 약관 등록 성공 여부
     */
    @Override
    @Transactional
    public String insertTerm(Long Id, TermsDTO.AddTermRequest request) {

        String type = findType(request.getType());
        request.setType(type);

        if(!termsRepository.existsTermsByTypeAndVersion(type, request.getVersion())){

            Admin admin = adminService.findAdminById(Id);

            Terms newTerms = request.toEntity(admin);
            termsRepository.save(newTerms);

            return "약관 등록 완료";
        }
        else{
            throw new TermsException(TermsExceptionType.ALREADY_EXIST);
        }
    }

    /**
     * 약관 타입 리스트 생성 함수
     * @param keyword : 약관 타입
     * @return : 해당 약관 타입의 List
     */
    @Override
    public List<TermsDTO.TermsListResponse> termsList(String keyword) {

        String type = findType(keyword);

        List<TermsDTO.TermsListResponse> termsListResponses = termsRepository.findByType(type)
                .stream()
                .map(entity -> new TermsDTO.TermsListResponse(entity))
                .collect(Collectors.toList());

        return termsListResponses;
    }

    /**
     * 약관 공개 여부 상태 변경 함수
     * 해당 약관 공개 설정 요청 시 이미 공개된 해당 타입의 약관이 존재하는 경우 변경 불가
     * @param request : 약관 변경 요청 DTO(Id, status)
     * @return : 변경 완료
     */
    @Override
    @Transactional
    public String changeStatus(TermsDTO.StatusRequest request) {

        Terms terms = findById(request.getId());

        if(request.getStatus().equals(terms.getStatus())) throw new TermsException(TermsExceptionType.CHECK_STATUS);

        if(terms.getStatus().equals("공개")){
            throw  new TermsException(TermsExceptionType.MUST_OPEN);
        }

        if(request.getForce()){
            if(request.getStatus().equals("공개") && termsRepository.existsByTypeAndStatus(terms.getType(), request.getStatus()) && request.getForce())
            {
                Terms old = termsRepository.findByTypeAndStatus(terms.getType(), request.getStatus());
                old.update("비공개");
                terms.update(request.getStatus());
                termsRepository.save(old);
            }
        }
        else{
            if(request.getStatus().equals("공개") || termsRepository.existsByTypeAndStatus(terms.getType(), request.getStatus()) && request.getForce())
            {
                throw new TermsException(TermsExceptionType.CHECK_OTHERS);
            }
        }
        terms.update(request.getStatus());
        termsRepository.save(terms);
        return "변경 완료";

    }

    /**
     * 약관 상세 내용 가져오는 함수
     * @param Id : 약관 Id
     * @return : 약관 상세 내용(작성자, 상태, 제목, 내용, 버전)
     */
    @Override
    public TermsDTO.DetailResponse showDetail(Long Id) {

        Terms terms = findById(Id);

        TermsDTO.DetailResponse response = new TermsDTO.DetailResponse(terms);
        return response;
    }

    /**
     * 약관 수정하는 함수
     * @param adminId : 작성자 정보를 위한 사용자 Id
     * @param request : 약관 변경 요청 DTO
     * @return : 변경완료
     */
    @Override
    @Transactional
    public String updateTerm(Long adminId, TermsDTO.UpdateRequest request) {

        Admin admin = adminService.findAdminById(adminId);

        Terms terms = findById(request.getId());

        terms.modify(admin, request);

        termsRepository.save(terms);

        return "변경 완료";
    }

    /**
     * 약관 삭제하는 함수
     * 약관이 공개인 경우, 약관의 작성자와 삭제하려는 사용자가 다른 경우 삭제 불가
     * @param userId : 사용자 확인을 위한 사용자 Id
     * @param termId : 약관 Id
     * @return : 삭제 완료
     */
    @Override
    public String deleteTerm(Long userId, Long termId) {

        Admin admin = adminService.findAdminById(userId);

        Terms terms = findById(termId);

        if(terms.getStatus().equals("공개")) throw new TermsException(TermsExceptionType.IS_OPENED);

        if(userId == terms.getAdmin().getId() || admin.getRole().equals("SUPERADMIN")){
            termsRepository.delete(terms);
            return "삭제 완료";
        }
        else {
            throw new TermsException(TermsExceptionType.NO_AUTHORITY);
        }
    }

    /**
     * 약관 Id로 약관 찾는 함수
     * @param Id : 약관 Id
     * @return : 해당 Id를 가진 약관
     */
    @Override
    public Terms findById(Long Id) {
        Optional<Terms> terms = termsRepository.findById(Id);
        if(terms.isPresent())
            return terms.get();
        else throw new TermsException(TermsExceptionType.NOT_EXIST);
    }


    /**
     * 약관 타입 찾는 함수
     * @param keyword : 전달된 약관 keyword
     * @return : 약관 타임 풀네임
     */
    public String findType(String keyword){
        String type = "";
        switch(keyword) {
            case "service":
                type = "서비스 이용약관";
                break;
            case "privacy":
                type = "개인정보 처리방침";
                break;
            case "information":
                type = "제3자 정보제공";
                break;
            case "marketing" :
                type = "개인정보 마케팅 이용";
                break;
            default:
                throw new TermsException(TermsExceptionType.INVALID_TYPE);
        }
        return type;
    }
}
