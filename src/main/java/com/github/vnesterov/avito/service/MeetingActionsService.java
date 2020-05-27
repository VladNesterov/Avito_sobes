package com.github.vnesterov.avito.service;

import com.github.vnesterov.avito.dto.MeetingDto;
import com.github.vnesterov.avito.entity.MeetingsEntity;
import com.github.vnesterov.avito.entity.MembersEntity;
import com.github.vnesterov.avito.repository.MeetingRepository;
import com.github.vnesterov.avito.repository.MeetingService;
import com.github.vnesterov.avito.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MeetingActionsService implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public MeetingActionsService(MeetingRepository meetingRepository, MemberRepository memberRepository) {
        this.meetingRepository = meetingRepository;
        this.memberRepository = memberRepository;
    }

    public List<MeetingDto> showMeetings() {
        List<MeetingsEntity> meetings = meetingRepository.findAll();
        List<MeetingDto> meetingDto = new ArrayList<>();

        for (MeetingsEntity meetingsEntityFromDataBase : meetings) {
            meetingDto.add(MeetingsEntity.toDto(meetingsEntityFromDataBase));
        }
        return meetingDto;
    }


    public void cancelMeetings(String meetingName) {
        MeetingsEntity meeting = meetingRepository.findByMeeting(meetingName);
        if (meeting == null) {
            return;
        }
        meeting.setStatus("Inactive");
        meeting.setMembers(Collections.emptyList());
        meetingRepository.save(meeting);
    }

    public void addMeetings(String meetingName, Date date) {

        MeetingsEntity entity = new MeetingsEntity();
        entity.setMeeting(meetingName);
        entity.setDate(date);
        entity.setStatus("Active");
        meetingRepository.save(entity);
    }

    @Override
    public void addMembersToMeetings(String meetingName, List<String> nameMembers) {
        MeetingsEntity meeting = meetingRepository.findByMeeting(meetingName);
        if (meeting == null) {
            throw new NullPointerException("This meeting does not exist " + meetingName);
        }
        List<MembersEntity> resultMembers = new ArrayList<>();
        List<String> membersDoesNotExistInDataBase = new ArrayList<>();

        for (String nameMember : nameMembers) {
            MembersEntity member = memberRepository.findByMember(nameMember);
            if (member == null) {
                membersDoesNotExistInDataBase.add(nameMember);
            } else resultMembers.add(member);
        }
        for (MembersEntity resultMember : resultMembers) {
            meeting.getMembers().add(resultMember);
        }
        meetingRepository.save(meeting);
        if (membersDoesNotExistInDataBase.size() > 0) {
            throw new RuntimeException("This members does not exist in database: " + membersDoesNotExistInDataBase);
        }
    }

    @Override
    public void deleteMembersFromMeetings(String meetingName, String nameMember) {
        MeetingsEntity meeting = meetingRepository.findByMeeting(meetingName);
        if (meeting == null) {
            throw new RuntimeException("This meeting does not exist " + meetingName);
        }
        if (meeting.getMembers().size() == 0) {
            throw new RuntimeException("This meeting does not contain members");
        }
        meeting.getMembers().removeIf(member -> member.getNamePerson().equalsIgnoreCase(nameMember));

        meetingRepository.save(meeting);
    }

}

