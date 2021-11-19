package com.project.challenge.domain.challenge;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ChallengeDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class addChallenge {

        @NotEmpty
        @Length(max=50)
        private String title;

        @NotEmpty
        @Length(max = 3000)
        private String content;

        @NotNull
        private String category;

        @NotBlank
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date startDate;

        @NotBlank
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date endDate;

        @Builder
        public addChallenge(String title, String content, String category, Date startDate, Date endDate) {
            this.title = title;
            this.content = content;
            this.category = category;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Challenge toEntity() {
            return Challenge.builder()
                    .challengeTitle(title)
                    .challengeContent(content)
                    .category(category)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();
        }

    }
}
