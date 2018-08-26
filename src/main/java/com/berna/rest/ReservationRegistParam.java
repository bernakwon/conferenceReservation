package com.berna.rest;

import java.time.LocalDate;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

@Getter
public class ReservationRegistParam {
	@Builder
   public ReservationRegistParam(Long conferenceId, LocalDate reservationDate, String registedName, String startTime,
			String endTime, int repetitionOfNum, String reservationName, Long reservationId) {
		super();
		this.conferenceId = conferenceId;
		this.reservationDate = reservationDate;
		this.registedName = registedName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.repetitionOfNum = repetitionOfNum;
		this.reservationName = reservationName;
		this.reservationId = reservationId;
	}
public ReservationRegistParam() {
		// TODO Auto-generated constructor stub
	}

private Long conferenceId;
   private LocalDate reservationDate;
   private String registedName;
   private String startTime;
   private String endTime;
   private int repetitionOfNum;
   private String reservationName;
   private Long reservationId;

 
   
}
