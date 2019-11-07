package com.berna.domain.reservation.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;

/**
 * @author hrkwon
 * @className ReservationDetail
 * @description 예약
 * **/
@Entity
@Getter
public class Reservation implements Serializable{


	private static final long serialVersionUID = 1L;

	/*ID*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*예약명*/
	@Column(name = "RESERVATION_NAME")
	private String reservationName;

	/*예약자명*/
	@Column(name = "REGIST_NAME")
	private String registedName;

	/*저장일시*/
	@CreationTimestamp
	@Column(name = "REGIST_DT")
	private LocalDateTime registDt;

	/*반복횟수*/
	@Column(name = "REPETITIONS_OF_NUM")
	private int repetitionOfNum = 1;
	
	/*버전*/
	@Version
	private long version;

	@Builder
	public Reservation(Long id, String reservationName, String registedName, LocalDateTime registDt,
			int repetitionOfNum) {
		super();
		this.id = id;
		this.reservationName = reservationName;
		this.registedName = registedName;
		this.registDt = registDt;
		this.repetitionOfNum = repetitionOfNum;
	}

	public Reservation() {
		super();
	}
	
	
}
