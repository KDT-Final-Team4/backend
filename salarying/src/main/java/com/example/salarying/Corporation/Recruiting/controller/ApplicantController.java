package com.example.salarying.Corporation.Recruiting.controller;


import com.example.salarying.Corporation.Recruiting.dto.ApplicantDTO;
import com.example.salarying.Corporation.Recruiting.service.ApplicantService;
import com.example.salarying.global.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ApplicantController {


    private final ApplicantService applicantService;

    @Operation(summary = "지원자 리스트 출력")
    @GetMapping("/applicants")
    public ResponseDTO<?> showApplicantList(@RequestParam("recruiting_id") Long id){
        List<ApplicantDTO.ApplicantResponse> applicantResponseList = applicantService.findApplicantByRecruitingId(id);
        return new ResponseDTO<>().ok(applicantResponseList,"정상출력 데이터");
    }
}
