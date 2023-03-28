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

        private Boolean status;

        public Notice toEntity(Admin admin) {
            return Notice.builder()
                    .admin(admin)
                    .title(title)
                    .content(content)
                    .postDate(new Date())
                    .status(status)
                    .build();
        }

    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class NoticeResponse {
        private Long id;
        private String adminEmail;
        private String title;
        private String content;
        private Date postDate;
        private String adminName;
        private Boolean status;

        public NoticeResponse(Notice notice) {
            this.id = notice.getId();
            this.adminEmail = notice.getAdmin().getAdminEmail();
            this.adminName = notice.getAdmin().getAdminName();
            this.title = notice.getTitle();
            this.content = notice.getContent();
            this.postDate = notice.getPostDate();
            this.status = notice.getStatus();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NoticeList {
        private String title;
        private String adminName;
        private String role;
        private Boolean status;

        public NoticeList(Notice notice) {
            this.title = notice.getTitle();
            this.adminName = notice.getAdmin().getAdminName();
            this.role = notice.getAdmin().getRole();
            this.status = notice.getStatus();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteRequest {
        private Long Id;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Update {
        private String title;
        private String content;
        private Boolean status;

        public Notice toEntity() {
            return Notice.builder()
                    .title(title)
                    .content(content)
                    .status(status)
                    .build();
        }
    }
}
