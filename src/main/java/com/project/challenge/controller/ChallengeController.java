package com.project.challenge.controller;

import com.project.challenge.domain.challenge.ChallengeDto;
import com.project.challenge.service.challenge.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChallengeController {

    private final ChallengeService challengeService;

    @GetMapping("/challenges/add")
    public String addChallengeForm(@ModelAttribute("challenge") ChallengeDto.addChallenge addChallenge) {
        return "challenges/addChallengeForm";
    }

    @PostMapping("/challenges")
    public String saveChallenge(@Valid @ModelAttribute("challenge") ChallengeDto.addChallenge addChallenge, BindingResult result) {
        if (result.hasErrors()) {
            log.info("errors={}", result);
            return "challenges/addChallengeForm";
        }

        challengeService.saveChallenge(addChallenge);
        return "redirect:/";
    }
}
