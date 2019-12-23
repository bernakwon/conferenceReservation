package com.berna.domain.reservation.dao;

import com.berna.domain.reservation.domain.ReservatedDate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @author hrkwon
 * @interfaceName ReservationFindDao
 *
 */
@Service
@RequiredArgsConstructor
public class ReservationFindDao {


    private final ReservatedDateRepository reservatedDateRepository;


    /**
     * @param LocalDate
     * @return ReservatedDate
     * @author hrkwon
     * @Description 예약일 기준으로 예약목록을 조회한다.
     */
    @Transactional
    public ReservatedDate getReservationList(LocalDate reservationDate) {

        return reservatedDateRepository.findByReservationDate(reservationDate);
    }

}
