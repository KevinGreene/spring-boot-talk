package com.surrealanalysis.jhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.surrealanalysis.jhipster.domain.Skill;
import com.surrealanalysis.jhipster.repository.SkillRepository;
import com.surrealanalysis.jhipster.repository.search.SkillSearchRepository;
import com.surrealanalysis.jhipster.web.rest.util.HeaderUtil;
import com.surrealanalysis.jhipster.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Skill.
 */
@RestController
@RequestMapping("/api")
public class SkillResource {

    private final Logger log = LoggerFactory.getLogger(SkillResource.class);
        
    @Inject
    private SkillRepository skillRepository;
    
    @Inject
    private SkillSearchRepository skillSearchRepository;
    
    /**
     * POST  /skills -> Create a new skill.
     */
    @RequestMapping(value = "/skills",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) throws URISyntaxException {
        log.debug("REST request to save Skill : {}", skill);
        if (skill.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("skill", "idexists", "A new skill cannot already have an ID")).body(null);
        }
        Skill result = skillRepository.save(skill);
        skillSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/skills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("skill", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /skills -> Updates an existing skill.
     */
    @RequestMapping(value = "/skills",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill) throws URISyntaxException {
        log.debug("REST request to update Skill : {}", skill);
        if (skill.getId() == null) {
            return createSkill(skill);
        }
        Skill result = skillRepository.save(skill);
        skillSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("skill", skill.getId().toString()))
            .body(result);
    }

    /**
     * GET  /skills -> get all the skills.
     */
    @RequestMapping(value = "/skills",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Skill>> getAllSkills(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Skills");
        Page<Skill> page = skillRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/skills");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /skills/:id -> get the "id" skill.
     */
    @RequestMapping(value = "/skills/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Skill> getSkill(@PathVariable Long id) {
        log.debug("REST request to get Skill : {}", id);
        Skill skill = skillRepository.findOne(id);
        return Optional.ofNullable(skill)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /skills/:id -> delete the "id" skill.
     */
    @RequestMapping(value = "/skills/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        log.debug("REST request to delete Skill : {}", id);
        skillRepository.delete(id);
        skillSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("skill", id.toString())).build();
    }

    /**
     * SEARCH  /_search/skills/:query -> search for the skill corresponding
     * to the query.
     */
    @RequestMapping(value = "/_search/skills/{query:.+}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Skill> searchSkills(@PathVariable String query) {
        log.debug("REST request to search Skills for query {}", query);
        return StreamSupport
            .stream(skillSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
