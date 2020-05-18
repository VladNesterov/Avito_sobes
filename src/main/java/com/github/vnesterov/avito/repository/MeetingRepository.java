package com.github.vnesterov.avito.repository;

import com.github.vnesterov.avito.entity.MeetingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface MeetingRepository extends JpaRepository<MeetingsEntity, Long> {

}



