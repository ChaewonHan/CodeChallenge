package com.project.challenge.domain.challenge;

import com.project.challenge.common.annotaion.DateCheck;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
        @DateCheck
        private LocalDate startDate;

        @NotNull
        private Integer period;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class challengeList {

        private String title;
        private String writer;
        private String category;
        private LocalDate startDate;
        private LocalDate endDate;
        private String thumbnailFilePath;

        public static challengeList toChallengeDto(Challenge challenge) {
            return challengeList.builder()
                    .title(challenge.getChallengeTitle())
                    .writer(challenge.getChallengeWriter())
                    .category(challenge.getCategory())
                    .startDate(challenge.getStartDate())
                    .endDate(challenge.getEndDate())
                    .thumbnailFilePath(challenge.getThumbnailFilePath())
                    .build();
        }
    }

}
