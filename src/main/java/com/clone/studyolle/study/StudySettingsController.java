package com.clone.studyolle.study;

import com.clone.studyolle.account.Account;
import com.clone.studyolle.account.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study/{path}/settings")
@RequiredArgsConstructor
public class StudySettingsController {

    private final StudyService studyService;

//    @GetMapping("/description")
//    public String viewStudySetting(@CurrentAccount Account account, @PathVariable String path, Model model) {
//        Study study = studyService.getStudyToUpdate(account, path);

//    }
}
