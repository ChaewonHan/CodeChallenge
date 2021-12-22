package com.project.challenge.domain.challenge;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

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

        @NotEmpty
        private String category;

        @NotEmpty
        private String username;

        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @Temporal(TemporalType.DATE)
        private Date startDate;

        @NotNull
        private Integer period;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @Temporal(TemporalType.DATE)
        private Date endDate;

        @Setter
        private String thumbnailFilePath;

        @Builder
        public addChallenge(String title, String content, String category, String username, Date startDate, Integer period, Date endDate, String thumbnailFilePath) {
            this.title = title;
            this.content = content;
            this.category = category;
            this.username = username;
            this.startDate = startDate;
            this.period = period;
            this.endDate = endDate;
            this.thumbnailFilePath = thumbnailFilePath;
        }


        public Challenge toEntity() {
            return Challenge.builder()
                    .challengeTitle(title)
                    .challengeContent(content)
                    .category(category)

                    .startDate(startDate)
                    .endDate(toDateTimeFormat())
                    .thumbnailFilePath(thumbnailFilePath)
                    .build();
        }

        public Date toDateTimeFormat() {
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            int day = period * 7;
            cal.add(Calendar.DATE, day);
            endDate = cal.getTime();

            return endDate;
        }

    }
}
