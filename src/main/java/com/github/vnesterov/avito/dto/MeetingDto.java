package com.github.vnesterov.avito.dto;

import com.github.vnesterov.avito.entity.MeetingsEntity;
import com.github.vnesterov.avito.entity.MembersEntity;
import jdk.internal.jline.internal.Nullable;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MeetingDto {

    @Nullable
    private long id;
    private String meeting;
    private Date date;
    private String status;
    private List<MembersEntity> members;
}
