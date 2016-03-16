package com.surrealanalysis.jhipster.repository;

import com.surrealanalysis.jhipster.domain.Skill;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Skill entity.
 */
public interface SkillRepository extends JpaRepository<Skill,Long> {

}
