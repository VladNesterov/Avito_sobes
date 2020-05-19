package com.github.vnesterov.avito.controller;

import com.github.vnesterov.avito.repository.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MeetingAddController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingAddController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @RequestMapping(value = "/meeting/add", method = RequestMethod.POST)
    public String meetingAdd(@RequestParam String meeting, @RequestParam Date date) {
        return meetingService.addMeetings(meeting, date);
    }


}
