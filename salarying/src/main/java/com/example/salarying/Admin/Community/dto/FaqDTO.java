package com.example.salarying.Admin.Community.dto;

import com.example.salarying.Admin.Community.entity.FAQ;
import com.example.salarying.Admin.User.entity.Admin;
import lombok.*;

import java.util.Date;

public class FaqDTO {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class InsertFaqRequest {
        private String question;
        private String answer;
        private String category;

        public FAQ toEntity(Admin admin) {
            return FAQ.builder()
                    .admin(admin)
                    .question(this.question)
                    .answer(this.answer)
                    .category(this.category)
                    .postDate(new Date())
                    .status(true)
                    .build();

        }
    }
}
