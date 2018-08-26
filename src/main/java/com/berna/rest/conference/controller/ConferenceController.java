package com.berna.rest.conference.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berna.rest.conference.domain.Conference;
import com.berna.rest.conference.service.ConferenceService;

/**
 * @author hrkwon
 * @className ConferenceController
 * @description 회의실 조회
 */
@RestController
public class ConferenceController {
	
	@Autowired
	ConferenceService conferenceService;
	

	/**
	 * @author hrkwon
	 * @method Get
	 * @return List<Conference>
	 * @Description 회의실 전체목록을 조회한다.
	 */
	@GetMapping("/conference")
	public List<Conference> getConferenceList(){
		return conferenceService.getList();
	}
	
}
