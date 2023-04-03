package com.example.salarying.Corporation.Recruiting.dto;

import com.example.salarying.Corporation.Recruiting.entity.Progress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProgressDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "전형 응답 DTO",description = "해당하는 전형 출력")
    public static class ProgressResponse {

        @Schema(description = "채용공고 id")
        private Long id;
        @Schema(description = "서류전형", allowableValues = {"true","false"})
        private Boolean hasDocument;
        @Schema(description = "1차전형", allowableValues = {"true","false"})
        private Boolean hasFirst;
        @Schema(description = "2차전형", allowableValues = {"true","false"})
        private Boolean hasSecond;
        @Schema(description = "최종전형", allowableValues = {"true","false"})
        private Boolean hasFinal;


        public ProgressResponse(Progress progress) {
            this.id = progress.getRecruiting().getId();
            this.hasDocument = progress.getHasDocument();
            this.hasFirst = progress.getHasFirstRound();
            this.hasSecond = progress.getHasSecondRound();
            this.hasFinal = progress.getHasFinalRound();
        }
    }
}
