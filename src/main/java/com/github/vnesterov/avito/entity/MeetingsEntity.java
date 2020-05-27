package com.github.vnesterov.avito.entity;

import com.github.vnesterov.avito.dto.MeetingDto;
import com.github.vnesterov.avito.dto.MembersDto;
import jdk.internal.jline.internal.Nullable;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
public class MeetingsEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @Nullable
    private long id;

    @Column(name = "meeting", nullable = false, unique = true)
    private String meeting;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "membersEntity_id")
    private List<MembersEntity> members;


    public static MeetingDto toDto(MeetingsEntity meetingsEntity) {

        List<MembersEntity> membersEntity = new ArrayList<>();
        membersEntity = meetingsEntity.getMembers();

        List<MembersDto> membersDtoResult = new ArrayList<>();

        for (int i = 0; i < membersEntity.size(); i++) {
            MembersDto membersDto = new MembersDto();
            membersDto.setEmail(membersEntity.get(i).getEmail());
            membersDto.setNamePerson(membersEntity.get(i).getNamePerson());
            membersDto.setId(membersEntity.get(i).getId());
            membersDtoResult.add(membersDto);
        }

        MeetingDto result = new MeetingDto();
        result.setId(meetingsEntity.getId());
        result.setMeeting(meetingsEntity.getMeeting());
        result.setDate(meetingsEntity.getDate());
        result.setMembers(membersDtoResult);
        result.setStatus(meetingsEntity.getStatus());
        return result;
    }

}
