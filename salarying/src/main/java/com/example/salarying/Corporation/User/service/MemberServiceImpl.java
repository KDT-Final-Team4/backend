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
