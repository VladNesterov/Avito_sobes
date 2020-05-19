package com.github.vnesterov.avito.controller;

import com.github.vnesterov.avito.repository.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PeopleDeleteController {

    private final MeetingService meetingService;

    @Autowired
    public PeopleDeleteController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @RequestMapping(value = "/member/delete", method = RequestMethod.POST)
    public void deletePerson(String meeting, @RequestParam List<String> name) {
        meetingService.deleteMembersFromMeetings(meeting, name);
    }

}
