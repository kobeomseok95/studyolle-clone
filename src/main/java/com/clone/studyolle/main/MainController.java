package com.clone.studyolle.main;

import com.clone.studyolle.account.Account;
import com.clone.studyolle.account.AccountRepository;
import com.clone.studyolle.account.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final AccountRepository accountRepository;

    @GetMapping("/")
    public String home(@CurrentAccount Account account, Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
