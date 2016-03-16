package com.surrealanalysis.template.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetupMemberRepository extends JpaRepository<MeetupMember, Long> {

    List<MeetupMember> findByFirstNameStartsWithIgnoreCase(String firstName);

}
