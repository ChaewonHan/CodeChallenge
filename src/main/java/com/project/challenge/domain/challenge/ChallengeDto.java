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
import java.util.Calendar;
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
        private int period;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date endDate;

        @Builder
        public addChallenge(String title, String content, String category, Date startDate, int period, Date endDate) {
            this.title = title;
            this.content = content;
            this.category = category;
            this.startDate = startDate;
            this.period = period;
        }

        public Challenge toEntity() {
            return Challenge.builder()
                    .challengeTitle(title)
                    .challengeContent(content)
                    .category(category)
                    .startDate(startDate)
                    .endDate(toDateTimeFormat())
                    .build();
        }

        private Date toDateTimeFormat() {
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            int day = period * 7;
            cal.add(Calendar.DATE, day);
            endDate = cal.getTime();

            return endDate;
        }

    }
}
