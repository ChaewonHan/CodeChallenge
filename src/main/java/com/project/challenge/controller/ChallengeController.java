package com.project.challenge.controller;

import com.project.challenge.common.annotaion.CurrentUser;
import com.project.challenge.common.annotaion.LoginCheck;
import com.project.challenge.service.challenge.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.project.challenge.domain.challenge.ChallengeDto.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChallengeController {

    private final ChallengeService challengeService;

    @LoginCheck
    @GetMapping("/challenges/new")
    public String addChallengeForm(@ModelAttribute("challenge") addChallenge addChallenge) {
        return "challenges/addChallengeForm";
    }

    @LoginCheck
    @PostMapping("/challenges")
    public String saveChallenge(@RequestPart(required = false) MultipartFile file, @Valid @ModelAttribute("challenge") addChallenge addChallenge,
                                BindingResult result, @CurrentUser String email) {
        if (result.hasErrors()) {
            log.info("errors={}", result);
            return "challenges/addChallengeForm";
        }
        challengeService.saveChallenge(addChallenge, email, file);
        return "redirect:/";
    }

    @GetMapping("/challenges")
    public String getChallenges(@RequestParam(required = false) String category, Pageable pageable, Model model) {
        Page<challengeList> challengeList;
        if (category != null) {
            challengeList = challengeService.getChallengeListCategory(pageable, category);
        } else {
            challengeList = challengeService.getChallengeList(pageable);
        }
        model.addAttribute("challengeList", challengeList);

        return "challenges/challengeList";
    }

}
