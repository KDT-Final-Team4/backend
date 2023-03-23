package com.example.salarying.Admin.Community.dto;

import com.example.salarying.Admin.Community.entity.Notice;
import com.example.salarying.Admin.User.entity.Admin;
import lombok.*;

import java.util.Date;

public class NoticeDTO {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticeRequest {
        private String title;
        private String content;

        public Notice toEntity(Admin admin) {
            return Notice.builder()
                    .admin(admin)
                    .title(title)
                    .content(content)
                    .postDate(new Date())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticeResponse {
        private String adminEmail;
        private String title;
        private String content;
        private Date postDate;

        public NoticeResponse(Notice notice) {
            this.adminEmail = notice.getAdmin().getAdminEmail();
            this.title = notice.getTitle();
            this.content = notice.getContent();
            this.postDate = notice.getPostDate();
        }
    }
}
