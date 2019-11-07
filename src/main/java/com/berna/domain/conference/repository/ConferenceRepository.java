package com.berna.domain.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berna.domain.conference.domain.Conference;

public interface ConferenceRepository extends JpaRepository<Conference,Long>{

}
