package com.berna.rest.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berna.rest.conference.domain.Conference;

public interface ConferenceRepository extends JpaRepository<Conference,Long>{

}
