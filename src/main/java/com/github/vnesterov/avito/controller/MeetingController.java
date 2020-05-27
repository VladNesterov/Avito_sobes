package com.github.vnesterov.avito.controller;

import com.github.vnesterov.avito.dto.MeetingDto;
import com.github.vnesterov.avito.repository.MeetingService;
import com.github.vnesterov.avito.repository.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class MeetingController {

    private final MeetingService meetingService;
    private final MemberService memberService;

    @Autowired
    public MeetingController(MeetingService meetingService, MemberService memberService) {
        this.meetingService = meetingService;
        this.memberService = memberService;
    }

    @RequestMapping(value = "/meeting/add", method = RequestMethod.POST)
    public void meetingAdd(@RequestParam String meeting, @RequestParam Date date) {
        meetingService.addMeetings(meeting, date);
    }

    @RequestMapping(value = "/meeting/cancel", method = RequestMethod.POST)
    public void cancel(@RequestParam String meeting) {
        meetingService.cancelMeetings(meeting);
    }

    @RequestMapping(value = "/show", method = RequestMethod.POST)
    public List<MeetingDto> show() {
        return meetingService.showMeetings();
    }

    @RequestMapping(value = "/member/add", method = RequestMethod.POST)
    public void create(@RequestParam String name, @RequestParam String email) {
        memberService.add(name, email);
    }

}
