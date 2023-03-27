package com.example.salarying.Admin.Terms.dto;

import com.example.salarying.Admin.Terms.entity.Terms;
import com.example.salarying.Admin.User.entity.Admin;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

public class TermsDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "약관 등록 DTO", description = "새로운 약관 생성 시 필요한 데이터")
    public static class AddTermRequest{

        @Schema(name = "type", example = "서비스 이용 약관")
        private String type;
        @Schema(name = "title", example = "서비스 이용 약관 임시 버전")
        private String title;
        @Schema(name = "version", example = "1.1")
        private String version;
        @Schema(name = "content")
        private String content;

        public Terms toEntity(Admin admin){
            return Terms.builder()
                    .admin(admin)
                    .status("비공개")
                    .type(this.type)
                    .version(this.version)
                    .agreementTitle(this.title)
                    .agreementContent(this.content)
                    .date(new Date())
                    .build();
        }
    }
}
