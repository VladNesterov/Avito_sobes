package com.github.vnesterov.avito.service;

import com.github.vnesterov.avito.repository.MeetingRepository;
import com.github.vnesterov.avito.repository.MemberRepository;
import com.github.vnesterov.avito.repository.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberActionService implements MemberService {

    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberActionService(MeetingRepository meetingRepository, MemberRepository memberRepository) {
        this.meetingRepository = meetingRepository;
        this.memberRepository = memberRepository;
    }


    @Override
    public void add(String name, String email) {

    }
}
