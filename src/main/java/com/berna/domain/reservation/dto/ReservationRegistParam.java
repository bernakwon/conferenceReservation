package com.berna.domain.reservation.dto;

import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.berna.domain.reservation.domain.ReservatedHash;
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
     * @return Boolean
     * @author hrkwon
     * @Description 분단위가 정시나 30분 단위인지 체크
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

    public List<String> getReservatedIndex() {
        //시간은 00시부터 24시까지 00분 30분 -> 48개
        //0부터 시작하므로 +1
        LocalTime start = LocalTime.parse(this.startTime);
        int startTimeHash = (start.getHour() + start.getMinute() / 60) * 2 + 1;
        LocalTime end = LocalTime.parse(this.endTime);
        int endTimeHash = (end.getHour() + end.getMinute() / 60) * 2 + 1;

        String dateString = this.reservationDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        LinkedList<String> resultList = new LinkedList<>();
        for(int i = startTimeHash; i< endTimeHash; i++) {
            resultList.add(this.conferenceId + dateString + i);
        }
        return resultList;
    }
    public List<ReservatedHash> getReservatedHash() {
        //시간은 00시부터 24시까지 00분 30분 -> 48개
        //0부터 시작하므로 +1
        LocalTime start = LocalTime.parse(this.startTime);
        int startTimeHash = (start.getHour() + start.getMinute() / 60) * 2 + 1;
        LocalTime end = LocalTime.parse(this.endTime);
        int endTimeHash = (end.getHour() + end.getMinute() / 60) * 2 + 1;

        String dateString = this.reservationDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<ReservatedHash> reservatedHashes = new ArrayList<>();
        for(int i = startTimeHash; i< endTimeHash; i++) {
            reservatedHashes.add(
                    ReservatedHash.builder()
                            .reservatedHash(this.conferenceId + dateString + i)
                            .build()
            );
        }
        return reservatedHashes;
    }

    /**
     * @param LocalDate,int
     * @return List<LocalDate>
     * @author hrkwon
     * @Description 반복체크시 예약일 리스트를 생성한다.
     */
    public List<LocalDate> culculateDates() {

        return IntStream.range(0, this.repetitionOfNum).mapToObj(loopCnt -> this.reservationDate.plusWeeks(loopCnt)).collect(Collectors.toList());
    }


}
