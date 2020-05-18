package com.github.vnesterov.avito.controller;

import com.github.vnesterov.avito.dto.MeetingDto;
import com.github.vnesterov.avito.repository.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeetingShowController {

    private final MeetingService meetingService;

    @Autowired
    public MeetingShowController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public List<MeetingDto> show() {
        return meetingService.showMeetings();
    }

}
