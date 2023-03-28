package com.example.salarying.Corporation.User.service;

import com.example.salarying.Corporation.User.dto.MemberDTO;
import com.example.salarying.Corporation.User.entity.Member;
import com.example.salarying.Corporation.User.exception.UserException;
import com.example.salarying.Corporation.User.exception.UserExceptionType;
import com.example.salarying.Corporation.User.repository.MemberRepository;
import com.example.salarying.global.jwt.auth.AuthToken;
import com.example.salarying.global.jwt.auth.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenProvider authTokenProvider;

    /**
     * 사용자 정보를 확인하여 비밀번호 암호화 수행 후 DB에 저장
     * @param request : 회사 정보를 가진 DTO
     * @return : 저장된 사용자 정보 혹은 존재하는 email이 있으면 해당 예외타입 반환
     */
    @Override
    @Transactional
    public Member signUp(MemberDTO.SignUpRequest request) {

        if(checkFormat(request) && memberRepository.findByEmail(request.getEmail()).isEmpty()){
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
        } else if (request.getCompanyName()==null) {
            throw new UserException(UserExceptionType.NOT_EXIST_NAME);
        } else if (!request.getCompanyPhoneNumber().matches("\\d{3}-?\\d{4}-?\\d{4}")) {
            throw new UserException(UserExceptionType.NOT_NUMBER_FORMAT);
        } else{
            return true;
        }
    }


    /**
     * 로그인 DTO를 받아 멤버 테이블에 존재하면 access token 발급하는 함수
     * @param request : 사용자 이메일, 비밀번호
     * @return 로그인 완료 시 access token 반환
     */
    @Override
    @Transactional
    public MemberDTO.LoginResponse login(MemberDTO.LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_EXIST_ACCOUNT));
        if(passwordEncoder.matches(request.getPassword(), member.getPassword())){
            member.updateLoginDate();
            AuthToken authToken = authTokenProvider.issueAccessToken(member);
            memberRepository.save(member);
            MemberDTO.LoginResponse response = new MemberDTO.LoginResponse(authToken.getToken(), member);
            return response;
        }else throw new UserException(UserExceptionType.UNMATCHED_PASSWORD);
    }

    /**
     * 비밀번호 재확인 기능
     * @param Id : 사용자 ID
     * @param request : 비밀번호
     * @return : 비밀번호 일치 여부
     */
    @Override
    public String checkPassword(Long Id, MemberDTO.CheckRequest request) {
        Member member = memberRepository.findById(Id)
                .orElseThrow(()-> new UserException(UserExceptionType.NOT_LOGGED_IN));

        if(passwordEncoder.matches(request.getPassword(), member.getPassword())){
            return "비밀번호가 일치합니다.";
        }
        else {
            throw  new UserException(UserExceptionType.UNMATCHED_PASSWORD);
        }
    }

    /**
     * 기업회원 비밀번호 변경 함수
     * @param Id : 사용자 ID
     * @param request : 새로운 비밀번호
     * @return : 비밀번호 변경 성공 여부
     */
    @Override
    public String changePassword(Long Id, MemberDTO.ChangeRequest request) {
        Member member = memberRepository.findById(Id)
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_LOGGED_IN));

        if(passwordEncoder.matches(request.getPassword(),member.getPassword())){
            throw new UserException(UserExceptionType.ALREADY_USED);
        }

        if(request.getPassword() != null && !request.getPassword().equals("")){
            member.updatePassword(passwordEncoder, request.getPassword());
            memberRepository.save(member);
            return "비밀번호 변경 성공";
        }
        else{
            throw new UserException(UserExceptionType.EMPTY_PASSWORD);
        }
    }
}
