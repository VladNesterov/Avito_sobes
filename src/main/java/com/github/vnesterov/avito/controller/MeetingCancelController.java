package com.github.vnesterov.avito.controller;

import com.github.vnesterov.avito.repository.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingCancelController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingCancelController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }


    @RequestMapping(value = "meeting/cancel")
    public String cancel(@RequestParam String meeting) {
        return meetingService.cancelMeetings(meeting);
    }
}
