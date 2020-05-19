package com.github.vnesterov.avito.controller;

import com.github.vnesterov.avito.repository.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAddToDataBase {
    private final MemberService memberService;

    @Autowired
    public MemberAddToDataBase(MemberService memberService) {
        this.memberService = memberService;
    }


    @RequestMapping(value = "/member/add/to/database", method = RequestMethod.POST)
    public String create(@RequestParam String name, @RequestParam String email) {
        return memberService.add(name, email);
    }
}
