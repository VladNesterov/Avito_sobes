package com.github.vnesterov.avito.repository;

import com.github.vnesterov.avito.entity.MeetingsEntity;
import com.github.vnesterov.avito.entity.MembersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface MemberRepository extends JpaRepository<MembersEntity, Long> {

    @Query("SELECT me FROM MeetingsEntity me WHERE me.meeting = :member")
    MembersEntity findByMember(String member);

}

