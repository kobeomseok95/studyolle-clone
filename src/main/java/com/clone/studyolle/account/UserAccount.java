package com.clone.studyolle.account;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserAccount extends User {

    private Account account;

    public UserAccount(Account account) {
        super(account.getNickname(), account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account = account;
    }
}
