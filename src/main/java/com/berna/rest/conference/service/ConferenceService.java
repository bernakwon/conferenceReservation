package com.berna.rest.conference.service;

import java.util.List;

import com.berna.rest.conference.domain.Conference;

/**
 * @author hrkwon
 * @interfaceName ConferenceService
 */
public interface ConferenceService {

	
	List<Conference> getList();

}
