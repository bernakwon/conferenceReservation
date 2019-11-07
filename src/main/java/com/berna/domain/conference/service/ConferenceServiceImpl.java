package com.berna.domain.conference.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berna.domain.conference.domain.Conference;
import com.berna.domain.conference.repository.ConferenceRepository;

/**
 * @author hrkwon
 * @className ConferenceServiceImpl
 * 
 */
@Service("conferenceService")
public class ConferenceServiceImpl implements ConferenceService{

	@Autowired
	ConferenceRepository conferenceRepository;
	
	/**
	 * @author hrkwon
	 * @return List<Conference>
	 * @Description 회의실 전체 리스트 조회
	 * 
	 */
	@Override
	public List<Conference> getList() {
		
		return conferenceRepository.findAll();
	}

}
