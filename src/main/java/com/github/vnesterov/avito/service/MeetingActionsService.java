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
        List<MeetingDto> result = new ArrayList<>();
        List<MeetingsEntity> meetings = meetingRepository.findAll();
        for (MeetingsEntity meetingsEntity01 : meetings) {
            MeetingDto meetingDto = new MeetingDto();
            meetingDto.setId(meetingsEntity01.getId());
            meetingDto.setDate(meetingsEntity01.getDate());
            meetingDto.setMeeting(meetingsEntity01.getMeeting());
            meetingDto.setStatus(meetingsEntity01.getStatus());
            meetingDto.setMembers(meetingsEntity01.getMembers());
            result.add(meetingDto);
        }
        return result;
    }

    public String cancelMeetings(String meeting) {
        String message = "There isn't the same meeting";
        List<MeetingsEntity> meetings = meetingRepository.findAll();
        for (MeetingsEntity meetingsEntityFromDataBase : meetings) {
            if (meetingsEntityFromDataBase.getMeeting().equalsIgnoreCase(meeting)) {
                MeetingsEntity entity = new MeetingsEntity();
                entity.setId(meetingsEntityFromDataBase.getId());
                entity.setMeeting(meetingsEntityFromDataBase.getMeeting());
                entity.setDate(meetingsEntityFromDataBase.getDate());
                entity.setStatus("Inactive");
                entity.setMembers(null);
                meetingRepository.save(entity);
                message = "Meeting was canceled";
                return message;
            }
        }
        return message;
    }

    public String addMeetings(String meeting, Date date) {
        MeetingsEntity entity = new MeetingsEntity();
        entity.setMeeting(meeting);
        entity.setDate(date);
        entity.setStatus("Active");
        meetingRepository.save(entity);
        return "Meeting was added ";
    }

    @Override
    public void addMembersToMeetings(String meeting, List<String> nameMembers) {
        List<MembersEntity> members = memberRepository.findAll();
        List<MeetingsEntity> meetings = meetingRepository.findAll();

        List<MembersEntity> resultMembers = new ArrayList<>();

        MeetingsEntity meetingsEntity = new MeetingsEntity();
        for (MeetingsEntity membersQuery : meetings) {
            if (membersQuery.getMeeting().equalsIgnoreCase(meeting)) {
                meetingsEntity = membersQuery;
                break;
            }
        }

        resultMembers = meetingsEntity.getMembers();
        for (MembersEntity membersQuery : members) {
            for (int i = 0; i < nameMembers.size(); i++) {
                if (membersQuery.getNamePerson().equalsIgnoreCase(nameMembers.get(i))) {
                    resultMembers.add(membersQuery);
                }
            }
        }
        meetingsEntity.setMembers(resultMembers);
        meetingRepository.save(meetingsEntity);
    }
}
