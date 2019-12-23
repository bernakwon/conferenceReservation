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

   /**
    * @author hrkwon
    * @return Boolean
    * @Description 분단위가 정시나 30분 단위인지 체크
    *
    */
   public Boolean isthirtyMinuteCheck() {
      Boolean thirtyMinuteCheck = false;
      int compareStartTimeMinute = LocalTime.parse(this.startTime).getMinute();
      int compareEndTimeMinute = LocalTime.parse(this.endTime).getMinute();
      if ((compareStartTimeMinute == 0 || compareStartTimeMinute == 30)
              && (compareEndTimeMinute == 0 || compareEndTimeMinute == 30)) {
         thirtyMinuteCheck = true;
      }
      return thirtyMinuteCheck;
   }


}
