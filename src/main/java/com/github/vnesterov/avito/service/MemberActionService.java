package com.github.vnesterov.avito.service;

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

    @Autowired
    public MemberActionService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public String add(String name, String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return "wrong Email";
        }
        System.out.println(email + " : " + matcher.matches());
        MembersEntity membersEntity = new MembersEntity();
        membersEntity.setEmail(email);
        membersEntity.setNamePerson(name);
        List<MembersEntity> members = memberRepository.findAll();
        for (MembersEntity membersEntity1 : members) {
            if (membersEntity1.getNamePerson().equalsIgnoreCase(name)) {
                return "This person already exist";
            }

        }
        memberRepository.save(membersEntity);
        return "Person was added to data base";
    }
}

