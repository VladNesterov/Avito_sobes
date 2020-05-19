package com.github.vnesterov.avito.dto;

import com.github.vnesterov.avito.entity.MeetingsEntity;
import com.github.vnesterov.avito.entity.MembersEntity;
import jdk.internal.jline.internal.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class MembersDto {

    @Nullable
    private long id;
    private String namePerson;
    private String email;


}
