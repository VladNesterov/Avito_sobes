package com.github.vnesterov.avito.entity;

import com.github.vnesterov.avito.dto.MeetingDto;
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

    @Column(name = "meeting", nullable = false)
    private String meeting;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="membersnEtity_id")
    private List<MembersEntity> members;


    public static MeetingDto toDto(MeetingsEntity meetingsEntity) {
        MeetingDto result = new MeetingDto();
        result.setId(meetingsEntity.getId());
        result.setMeeting(meetingsEntity.getMeeting());
        result.setDate(meetingsEntity.getDate());
        return result;
    }

}
