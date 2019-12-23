package com.berna.domain.reservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.berna.domain.reservation.domain.Reservation;

/**
 * @author hrkwon
 * @interfaceName ReservationRepository
 * 
 */
public interface ReservationRepository extends JpaRepository<Reservation,Long>{

}
