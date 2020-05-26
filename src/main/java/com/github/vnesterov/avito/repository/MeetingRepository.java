package com.github.vnesterov.avito.repository;

import com.github.vnesterov.avito.entity.MeetingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface MeetingRepository extends JpaRepository<MeetingsEntity, Long> {

    @Query("SELECT me FROM MeetingsEntity me WHERE me.meeting = :meeting")
    MeetingsEntity findByMeeting(String meeting);

    @Modifying
    @Query(" UPDATE MeetingsEntity me SET me.status ='Inactive' WHERE me.meeting = :meeting")
    void setInactive(String meeting);
}



