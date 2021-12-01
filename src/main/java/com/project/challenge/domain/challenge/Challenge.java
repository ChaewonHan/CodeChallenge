package com.project.challenge.domain.challenge;

import com.project.challenge.domain.BaseEntity;
import com.project.challenge.domain.CreateDateEntity;
import com.project.challenge.domain.authentication.Authentication;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "challenges")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Challenge extends CreateDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeNo;

    @Column(nullable = false)
    private String challengeTitle;

    @Column(nullable = false)
    private String challengeContent;

    @Column
    private String category;

    @Column(insertable = false)
    @Enumerated(EnumType.STRING)
    private ChallengeStatus challengeStatus;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @OneToMany(mappedBy = "challenge")
    private List<Authentication> authentication;

    @Column
    private String originalFilePath;

    @Column
    private String thumbnailFilePath;

    @Builder
    public Challenge(Long challengeNo, String challengeTitle, String challengeContent, String category, ChallengeStatus challengeStatus, Date startDate, Date endDate, List<Authentication> authentication, String originalFilePath, String thumbnailFilePath) {
        this.challengeNo = challengeNo;
        this.challengeTitle = challengeTitle;
        this.challengeContent = challengeContent;
        this.category = category;
        this.challengeStatus = challengeStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.authentication = authentication;
        this.originalFilePath = originalFilePath;
        this.thumbnailFilePath = thumbnailFilePath;
    }
}
