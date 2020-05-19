package com.github.vnesterov.avito.entity;

import com.github.vnesterov.avito.dto.MembersDto;
import jdk.internal.jline.internal.Nullable;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class MembersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Nullable
    private long id;

    @Column(name = "person_name", nullable = false)
    private String namePerson;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "meetingsEntity_id")
    private List<MeetingsEntity> meetings;

    public static MembersDto toDto(MembersEntity membersEntity) {
        MembersDto result = new MembersDto();
        result.setId(membersEntity.getId());
        result.setEmail(membersEntity.email);
        result.setNamePerson(membersEntity.getNamePerson());
        return result;
    }

}
