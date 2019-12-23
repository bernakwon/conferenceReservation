package com.berna.domain.reservation.service;

import java.time.LocalDate;
import com.berna.domain.reservation.dto.ReservationRegistParam;
import com.berna.domain.reservation.domain.ReservatedDate;

/**
 * @author hrkwon
 * @interfaceName ReservationService
 * 
 */
public interface ReservationService {

	Long create(ReservationRegistParam ReservationRegistParam);

}
