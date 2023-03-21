package com.example.salarying.Corporation.User.service;

import com.example.salarying.Corporation.User.dto.MemberDTO;
import com.example.salarying.Corporation.User.entity.Member;
import com.example.salarying.Corporation.User.exception.UserException;
import com.example.salarying.Corporation.User.exception.UserExceptionType;
import com.example.salarying.Corporation.User.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 사용자 정보를 확인하여 비밀번호 암호화 수행 후 DB에 저장
     * @param request : 회사 정보를 가진 DTO
     * @return : 저장된 사용자 정보 혹은 존재하는 email이 있으면 해당 예외타입 반환
     */
    @Override
    public Member signUp(MemberDTO.SignUpRequest request) {

        if(checkFormat(request) && memberRepository.findByUserEmail(request.getEmail()).isEmpty()){
            Member newMember = request.toEntity();
            newMember.encodePassword(passwordEncoder);
            memberRepository.save(newMember);
            return newMember;
        }
        else{
            throw new UserException(UserExceptionType.ALREADY_EXIST);
        }
    }

    /**
     * 회원가입시 입력된 사용자 정보의 형식이 올바른지 확인하는 함수
     * @param request : 입력된 사용자 정보
     * @return 모든 값이 지정된 형식을
     */
    public Boolean checkFormat(MemberDTO.SignUpRequest request){
        if(!request.getEmail().matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
            throw new UserException(UserExceptionType.NOT_EMAIL_FORMAT);
        } else if (!request.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) { //최소 8글자, 글자 1개, 숫자 1개, 특수문자 1개
            throw new UserException(UserExceptionType.NOT_PASSWORD_FORMAT);
        } else if (request.getCompanyNm()==null) {
            throw new UserException(UserExceptionType.NOT_EXIST_NAME);
        } else if (!request.getCompanyTel().matches("\\d{3}-?\\d{4}-?\\d{4}")) {
            throw new UserException(UserExceptionType.NOT_NUMBER_FORMAT);
        } else{
            return true;
        }
    }


    @Override
    public Member findByUserByEmail(String email) {
        Optional<Member> member = memberRepository.findByUserEmail(email);
        if (!member.isEmpty()) {
            return member.get();
        } else {
            throw new UserException(UserExceptionType.ALREADY_EXIST);
        }
    }
}
