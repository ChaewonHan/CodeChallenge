package com.project.challenge.domain.challenge;

import com.project.challenge.domain.BaseEntity;
import com.project.challenge.domain.CreateDateEntity;
import com.project.challenge.domain.user.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity(name = "challenges")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class Challenge extends CreateDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeNo;

    @Column(nullable = false)
    private String challengeTitle;

    @Column(nullable = false)
    private String challengeContent;

    @Column(nullable = false)
    private String challengeWriter;

    @Column(nullable = false)
    private String category;

    @Column(insertable = false)
    @Enumerated(EnumType.STRING)
    private ChallengeStatus challengeStatus;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column
    private String thumbnailFilePath;

    @Column
    private String originalFilename;
}
