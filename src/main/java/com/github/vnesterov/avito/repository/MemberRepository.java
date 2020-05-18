package com.github.vnesterov.avito.repository;

import com.github.vnesterov.avito.entity.MembersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface MemberRepository extends JpaRepository<MembersEntity, Long> {

}

