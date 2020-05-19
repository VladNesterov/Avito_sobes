package com.github.vnesterov.avito.dto;


import jdk.internal.jline.internal.Nullable;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
public class MeetingDto {

    @Nullable
    private long id;
    private String meeting;
    private Date date;
    private String status;

    private List<MembersDto> members;
}
