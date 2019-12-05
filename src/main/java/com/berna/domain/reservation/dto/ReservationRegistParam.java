package com.berna.domain.reservation.dto;

import java.time.LocalDate;

import java.time.LocalTime;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRegistParam {


private Long conferenceId;
   private LocalDate reservationDate;
   private String registedName;
   private String startTime;
   private String endTime;
   private int repetitionOfNum;
   private String reservationName;
   private Long reservationId;

 
   
}
