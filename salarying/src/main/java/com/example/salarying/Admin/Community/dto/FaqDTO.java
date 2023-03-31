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

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FAQListResponse {
        private String question;
        private String answer;
        private String category;

        public FAQListResponse(FAQ faq) {
            this.question = faq.getQuestion();
            this.answer = faq.getAnswer();
            this.category = faq.getCategory();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FaqStatusRequest {
        private Long id;
        private Boolean status;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailResponse {
        private String question;
        private String answer;
        private Date postDate;
        private String adminName;

        public DetailResponse(FAQ faq) {
            this.question = faq.getQuestion();
            this.answer = faq.getAnswer();
            this.postDate = faq.getPostDate();
            this.adminName = faq.getAdmin().getAdminName();
        }
    }
}
