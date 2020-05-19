package com.github.vnesterov.avito.controller;

import com.github.vnesterov.avito.entity.MeetingsEntity;
import com.github.vnesterov.avito.entity.MembersEntity;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MeetingActionsServiceTest {

    @Test
    public void cancel() {

        MembersEntity membersEntityVova = new MembersEntity();
        membersEntityVova.setEmail("vova@mail.ru");
        membersEntityVova.setId(1);
        membersEntityVova.setNamePerson("vova");

        MembersEntity membersEntityVlad = new MembersEntity();
        membersEntityVlad.setEmail("vlad@mail.ru");
        membersEntityVlad.setId(2);
        membersEntityVlad.setNamePerson("vlad");

        MembersEntity membersEntityVika = new MembersEntity();
        membersEntityVika.setEmail("vika@mail.ru");
        membersEntityVika.setId(2);
        membersEntityVika.setNamePerson("vika");


        List<MembersEntity> membersEntities = new ArrayList<>();
        membersEntities.add(membersEntityVika);
        membersEntities.add(membersEntityVova);

        MeetingsEntity meetingsEntity01 = new MeetingsEntity();
        meetingsEntity01.setId(1);
        meetingsEntity01.setMeeting("meeting01");
        meetingsEntity01.setStatus("Active");
        meetingsEntity01.setMembers(membersEntities);

        MeetingsEntity meetingsEntity02 = new MeetingsEntity();
        meetingsEntity02.setId(1);
        meetingsEntity02.setMeeting("meeting02");
        meetingsEntity02.setStatus("Active");
        meetingsEntity02.setMembers(membersEntities);

        List<MeetingsEntity> meeting01 = new ArrayList<>();
        meeting01.add(meetingsEntity01);
        meeting01.add(meetingsEntity02);

        MeetingsEntity entityResultFirstTest = new MeetingsEntity();
        String nameMeetingToCancel = "meeting01";

        for (MeetingsEntity meetingsEntityFromDataBase01 : meeting01) {
            if (meetingsEntityFromDataBase01.getMeeting().equalsIgnoreCase(nameMeetingToCancel)) {

                entityResultFirstTest.setId(meetingsEntityFromDataBase01.getId());
                entityResultFirstTest.setMeeting(meetingsEntityFromDataBase01.getMeeting());
                entityResultFirstTest.setDate(meetingsEntityFromDataBase01.getDate());
                entityResultFirstTest.setStatus("Inactive");
                entityResultFirstTest.setMembers(null);
            }
        }

        assertNull(entityResultFirstTest.getMembers());
        assertEquals("Inactive", entityResultFirstTest.getStatus());
        assertEquals("meeting01", entityResultFirstTest.getMeeting());


        MeetingsEntity entityResultSecondTest = new MeetingsEntity();
        String meetingToCancel02 = "meeting02";

        for (MeetingsEntity meetingsEntityFromDataBase : meeting01) {
            if (meetingsEntityFromDataBase.getMeeting().equalsIgnoreCase(meetingToCancel02)) {
                entityResultSecondTest.setId(meetingsEntityFromDataBase.getId());
                entityResultSecondTest.setMeeting(meetingsEntityFromDataBase.getMeeting());
                entityResultSecondTest.setDate(meetingsEntityFromDataBase.getDate());
                entityResultSecondTest.setStatus("Inactive");
                entityResultSecondTest.setMembers(null);
            }
        }
        assertNull(entityResultSecondTest.getMembers());
        assertEquals("Inactive", entityResultSecondTest.getStatus());
        assertEquals("meeting02", entityResultSecondTest.getMeeting());

    }

}
