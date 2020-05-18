package com.github.vnesterov.avito.controller;

import com.github.vnesterov.avito.repository.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PeopleDeleteController {

    private final MemberService memberService;

    @Autowired
    public PeopleDeleteController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/deletePerson", method = RequestMethod.DELETE)
    public void deletePerson(String meeting, List<String> name) {
        memberService.delete(meeting, name);
    }


}
