package com.github.vnesterov.avito.service;

import com.github.vnesterov.avito.entity.MeetingsEntity;
import com.github.vnesterov.avito.entity.MembersEntity;
import com.github.vnesterov.avito.repository.MeetingRepository;
import com.github.vnesterov.avito.repository.MemberRepository;
import com.github.vnesterov.avito.repository.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberActionService implements MemberService {

    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberActionService(MeetingRepository meetingRepository, MemberRepository memberRepository) {
        this.meetingRepository = meetingRepository;
        this.memberRepository = memberRepository;
    }

//    @Override
//    public void add(String meeting, List<String> namesMembers) {
//        List<Meetings> meetings = meetingRepository.findAll();
//
//        for (Meetings meetingsFromDataBase : meetings) {
//            if (meetingsFromDataBase.getMeeting().equals(meeting)) {
//                Meetings entity = new Meetings();
//                entity.setId(meetingsFromDataBase.getId());
//                entity.setMeeting(meetingsFromDataBase.getMeeting());
//                entity.setDate(meetingsFromDataBase.getDate());
//                entity.setStatus("Active");
//                entity.setMembers(meetingsFromDataBase.getMembers());
//                entity.setMembers(searchMembersForAdd(namesMembers));
//                meetingRepository.save(entity);
//            }
//        }
//    }
//
//    public List<Members> searchMembersForAdd(List<String> names) {
//        List<Members> people = peopleRepository.findAll();
//        List<Members> result = new ArrayList<>();
//
//        for (Members meetings01 : people) {
//
//            for (int i = 0; i < names.size(); i++) {
//                if (meetings01.getNamePerson().equalsIgnoreCase(names.get(i))) {
//                    result.add(meetings01);
//                    break;
//                }
//            }
//        }
//        return result;
//
//    }


    public MembersEntity searchMembersForAddasd(String names) {
        List<MembersEntity> people = memberRepository.findAll();
        List<MembersEntity> result = new ArrayList<>();

        for (MembersEntity meetings01 : people) {
            meetings01.getNamePerson().equalsIgnoreCase(names);
            return meetings01;
        }
        return null;
    }


    @Override
    public void add(String meeting, String name) {
        List<MeetingsEntity> meetings = meetingRepository.findAll();
        List<MembersEntity> members = new ArrayList<>();

        for (MeetingsEntity meetingsEntityFromDataBase : meetings) {
            if (meetingsEntityFromDataBase.getMeeting().equals(meeting)) {
                MeetingsEntity entity = new MeetingsEntity();
                entity.setId(meetingsEntityFromDataBase.getId());
                entity.setMeeting(meetingsEntityFromDataBase.getMeeting());
                entity.setDate(meetingsEntityFromDataBase.getDate());
                entity.setStatus("Active");
                meetingRepository.save(entity);
            }
        }

    }

    @Override
    public void delete(String meeting, List<String> name) {


    }

}
