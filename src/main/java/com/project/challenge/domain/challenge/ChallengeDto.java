package com.project.challenge.domain.challenge;

import com.project.challenge.domain.user.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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

        @Setter
        private String writer;

        @NotEmpty
        private String category;

        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @Temporal(TemporalType.DATE)
        private LocalDate startDate;

        @NotNull
        private Integer period;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @Temporal(TemporalType.DATE)
        private LocalDate endDate;

        private String thumbnailFilePath;

        private String originalFilename;

        @Builder
        public addChallenge(String title, String content, String writer, String category, LocalDate startDate, Integer period, LocalDate endDate, String thumbnailFilePath, String originalFilename) {
            this.title = title;
            this.content = content;
            this.writer = writer;
            this.category = category;
            this.startDate = startDate;
            this.period = period;
            this.endDate = endDate;
            this.thumbnailFilePath = thumbnailFilePath;
            this.originalFilename = originalFilename;
        }

        public Challenge toEntity() {
            return Challenge.builder()
                    .challengeTitle(title)
                    .challengeContent(content)
                    .category(category)
                    .challengeWriter(writer)
                    .startDate(startDate)
                    .endDate(toDateTimeFormat())
                    .thumbnailFilePath(thumbnailFilePath)
                    .originalFilename(originalFilename)
                    .build();
        }

        public void setImage(String thumbnailFilePath, String originalFilename) {
            this.thumbnailFilePath = thumbnailFilePath;
            this.originalFilename = originalFilename;
        }

        public LocalDate toDateTimeFormat() {
            endDate = LocalDate.now().plusWeeks(period);
            return endDate;
        }

    }
}
