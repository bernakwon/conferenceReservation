package com.berna.domain.reservation.service;

import com.berna.domain.conference.domain.Conference;
import com.berna.domain.reservation.domain.ReservatedDate;
import com.berna.domain.reservation.domain.Reservation;
import com.berna.domain.reservation.domain.ReservationDetail;
import com.berna.domain.reservation.domain.ReservationDetailPrimaryKey;
import com.berna.domain.reservation.dto.ReservationRegistParam;
import com.berna.domain.reservation.dao.ReservatedDateRepository;
import com.berna.domain.reservation.dao.ReservationDetailRepository;
import com.berna.domain.reservation.dao.ReservationRepository;
import com.berna.global.error.exception.ReservationReduplicationException;
import com.berna.global.error.exception.TimeErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author hrkwon
 * @className ReservationServiceImpl
 */
@Service
@RequiredArgsConstructor
public class ReservationComparedBySQL implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final ReservatedDateRepository reservatedDateRepository;

    private final ReservationDetailRepository reservationDetailRepository;


    /**
     * @param ReservationRegistParam
     * @return Long
     * @author hrkwon
     * @Description 예약을 생성한다.
     */
    @Transactional
    @Override
    public Long create(ReservationRegistParam reservationRegistParam) {
        Long resultId = 0L;
        // 30분/정시, 시간체크
        if (!reservationRegistParam.isthirtyMinuteCheck()) {
            throw new TimeErrorException("The minute unit is 30 minutes.");
        }

        // 중복체크
        if (isDuplicationCheck(reservationRegistParam)) {

            resultId = upsertReservation(reservationRegistParam);

        } else {
            throw new ReservationReduplicationException("It is already booked. Please select another time.");
        }
        return resultId;
    }

    /**
     * @param Reservation , ReservationRegistParam
     * @return Long
     * @author hrkwon
     * @Description 예약저장 보조 method
     */
    private Long upsertReservation(ReservationRegistParam reservationRegistParam) {
        Reservation saveReservation = Reservation.builder().registedName(reservationRegistParam.getRegistedName())
                .repetitionOfNum(reservationRegistParam.getRepetitionOfNum())
                .reservationName(reservationRegistParam.getReservationName()).build();

        Reservation resultReservation = reservationRepository.save(saveReservation);

        saveReservatedDatesAndDetails(resultReservation, reservationRegistParam);

        return resultReservation.getId();
    }

    /**
     * @param Reservation , ReservationRegistParam
     * @return void
     * @author hrkwon
     * @Description 예약일과 예약상세를 수정한다.
     */
    private void saveReservatedDatesAndDetails(Reservation saveReservation,
                                               ReservationRegistParam reservationRegistParam) {

        List<LocalDate> reservatedDates = reservationRegistParam.culculateDates();

        for (LocalDate reservatedDate : reservatedDates) {

            ReservatedDate saveReservatedDate = upsertReservatedDate(reservatedDate);

            ReservationDetailPrimaryKey pk = ReservationDetailPrimaryKey.builder()
                    .conference(Conference.builder().id(reservationRegistParam.getConferenceId()).build()).reservatedDate(saveReservatedDate)
                    .reservation(saveReservation).build();

            ReservationDetail detail =  Optional.ofNullable(reservationDetailRepository.findByReservationDetailPrimaryKey(pk))
                                        .orElse(ReservationDetail.builder()
                                        .endTime(reservationRegistParam.getEndTime())
                                        .startTime(reservationRegistParam.getStartTime())
                                        .reservationDetailPrimaryKey(pk)
                                        .build());
            reservationDetailRepository.save(detail);
        }
    }

    /**
     * @param LocalDate
     * @return ReservatedDate
     * @author hrkwon
     * @Description 예약일이 예약일 관리 테이블에 존재하는지 체크 후 , 없으면 생성한다.
     */
    public ReservatedDate upsertReservatedDate(LocalDate reservatedDate) {

        ReservatedDate resultReservatedDate = reservatedDateRepository.findByReservationDate(reservatedDate);
        if (resultReservatedDate == null) {

            ReservatedDate saveReservatedDate = ReservatedDate.builder().reservationDate(reservatedDate).build();
            reservatedDateRepository.save(saveReservatedDate);
            return saveReservatedDate;
        }
        return resultReservatedDate;
    }

    /**
     * @param ReservationRegistParam
     * @return Boolean
     * @author hrkwon
     * @Description 예약이 중복인지 체크
     */

    private Boolean isDuplicationCheck(ReservationRegistParam reservationRegistParam) {
        Boolean FLAG = true;
        List<LocalDate> reservatedDates = reservationRegistParam.culculateDates();

        int duplicationCheck = reservationDetailRepository
                .countByReservationDetailPrimaryKeyConferenceIdAndReservationDetailPrimaryKeyReservatedDateReservationDateInAndStartTimeLessThanAndEndTimeGreaterThan(
                        reservationRegistParam.getConferenceId(), reservatedDates, reservationRegistParam.getEndTime(),
                        reservationRegistParam.getStartTime());

        if (duplicationCheck > 0) {
            FLAG = false;
        }

        return FLAG;
    }


}
