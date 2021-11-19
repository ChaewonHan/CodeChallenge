package com.project.challenge.controller;

import com.project.challenge.domain.challenge.ChallengeDto;
import com.project.challenge.service.challenge.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChallengeController {

    private ChallengeService challengeService;

    @GetMapping("/challenges/add")
    public String addChallengeForm(@ModelAttribute("challenge") ChallengeDto.addChallenge addChallenge, Model model) {
        return "challenges/addChallengeForm";
    }

    @PostMapping("/challenges")
    public String saveChallenge(@ModelAttribute("challenge") ChallengeDto.addChallenge addChallenge, Model model) {
        challengeService.saveChallenge(addChallenge);
        return "challenges/addChallengeForm";
    }
}
