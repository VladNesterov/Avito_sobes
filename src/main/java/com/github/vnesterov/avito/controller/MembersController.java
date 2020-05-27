package com.github.vnesterov.avito.controller;

import com.github.vnesterov.avito.repository.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MembersController {

    private final MeetingService meetingService;

    @Autowired
    public MembersController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }


    @RequestMapping(value = "/member/add/to/meeting", method = RequestMethod.POST)
    public void create(@RequestParam String meeting, @RequestParam List<String> members) {
         meetingService.addMembersToMeetings(meeting, members);
    }

    @RequestMapping(value = "/member/delete", method = RequestMethod.POST)
    public void deletePerson(String meeting, @RequestParam String name) {
        meetingService.deleteMembersFromMeetings(meeting, name);
    }

}
