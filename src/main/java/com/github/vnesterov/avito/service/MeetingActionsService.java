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
        List<MeetingsEntity> meetings = meetingRepository.findAll();
        List<MeetingDto> meetingDto = new ArrayList<>();

        for (MeetingsEntity meetingsEntityFromDataBase : meetings) {
            meetingDto.add(MeetingsEntity.toDto(meetingsEntityFromDataBase));
        }
        return meetingDto;
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
        List<MeetingsEntity> meetings = meetingRepository.findAll();
        List<MeetingDto> meetingDto = new ArrayList<>();

        for (MeetingsEntity meetingsEntityFromDataBase : meetings) {
            if (meetingsEntityFromDataBase.getMeeting().equalsIgnoreCase(meeting)) {
                return "Meeting was not added, because a such meeting already exists";
            }
        }
        MeetingsEntity entity = new MeetingsEntity();
        entity.setMeeting(meeting);
        entity.setDate(date);
        entity.setStatus("Active");
        meetingRepository.save(entity);
        return "Meeting was added ";
    }

    @Override
    public String addMembersToMeetings(String meeting, List<String> nameMembers) {
        List<MembersEntity> members = memberRepository.findAll();
        List<MeetingsEntity> meetings = meetingRepository.findAll();

        List<MembersEntity> resultMembers = new ArrayList<>();

        MeetingsEntity meetingsEntity = getMeetingsEntity(meeting, meetings);

        resultMembers = meetingsEntity.getMembers();
        try {
            for (MembersEntity membersQuery : members) {
                for (int i = 0; i < nameMembers.size(); i++) {
                    if (membersQuery.getNamePerson().equalsIgnoreCase(nameMembers.get(i))) {
                        resultMembers.add(membersQuery);
                    }
                }
            }
        } catch (NullPointerException e) {
            return "You have to create meeting and then add members to it";
        }

        meetingsEntity.setMembers(resultMembers);
        meetingsEntity.setStatus("Active");
        meetingRepository.save(meetingsEntity);
        return "Members was added to meeting";
    }

    @Override
    public void deleteMembersFromMeetings(String meeting, List<String> nameMembers) {
        List<MembersEntity> members = memberRepository.findAll();
        List<MeetingsEntity> meetings = meetingRepository.findAll();

        List<MembersEntity> resultMembers = new ArrayList<>();

        MeetingsEntity meetingsEntity = getMeetingsEntity(meeting, meetings);

        for (MembersEntity membersQuery : members) {
            for (int i = 0; i < nameMembers.size(); i++) {
                if (!membersQuery.getNamePerson().equalsIgnoreCase(nameMembers.get(i))) {
                    resultMembers.add(membersQuery);
                }
            }
        }

        meetingsEntity.setMembers(resultMembers);
        meetingsEntity.setStatus("Active");
        meetingRepository.save(meetingsEntity);
    }

    private MeetingsEntity getMeetingsEntity(String meeting, List<MeetingsEntity> meetings) {
        MeetingsEntity meetingsEntity = new MeetingsEntity();
        for (MeetingsEntity membersQuery : meetings) {
            if (membersQuery.getMeeting().equalsIgnoreCase(meeting)) {
                meetingsEntity = membersQuery;
                break;
            }
        }
        return meetingsEntity;
    }


}

