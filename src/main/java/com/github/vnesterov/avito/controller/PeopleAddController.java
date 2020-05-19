package com.github.vnesterov.avito.controller;

import com.github.vnesterov.avito.repository.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeopleAddController {

    private final MeetingService meetingService;

    @Autowired
    public PeopleAddController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }


    @RequestMapping(value = "/member/add/to/meeting",  method = RequestMethod.POST)
    public void create(@RequestParam String meeting, @RequestParam List<String> members) {
        meetingService.addMembersToMeetings(meeting, members);
    }

}
