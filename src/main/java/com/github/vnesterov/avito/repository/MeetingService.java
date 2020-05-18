package com.github.vnesterov.avito.repository;

import com.github.vnesterov.avito.dto.MeetingDto;

import java.util.Date;
import java.util.List;

public interface MeetingService {
    List<MeetingDto> showMeetings();

    String cancelMeetings(String meeting);

    String addMeetings(String meeting, Date date);

    void addMembersToMeetings(String meeting,List<String> nameMembers);
}
