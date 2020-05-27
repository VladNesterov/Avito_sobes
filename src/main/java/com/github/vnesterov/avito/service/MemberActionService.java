package com.github.vnesterov.avito.service;

import com.github.vnesterov.avito.entity.MeetingsEntity;
import com.github.vnesterov.avito.entity.MembersEntity;
import com.github.vnesterov.avito.repository.MemberRepository;
import com.github.vnesterov.avito.repository.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class MemberActionService implements MemberService {

    private final MemberRepository memberRepository;
    private static final Pattern pattern = Pattern
            .compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

    @Autowired
    public MemberActionService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void add(String memberName, String email) {
        MembersEntity member = memberRepository.findByMember(memberName);
        if (member != null) {
            throw new RuntimeException("This members already exist");
        }
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new RuntimeException("wrong Email");
        }
        MembersEntity membersEntity = new MembersEntity();
        membersEntity.setEmail(email);
        membersEntity.setNamePerson(memberName);

        memberRepository.save(membersEntity);
    }
}

