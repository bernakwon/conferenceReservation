package com.berna.rest.reservation.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



/**
 * @author hrkwon
 * @className ReservationDetail
 * @description 예약상세
 * **/
@Entity
@Table(name="RESERVATION_DETAIL")
@Getter@Setter
public class ReservationDetail implements Serializable{

	private static final long serialVersionUID = 1L;

	/*외래키로 이루어진 복합 PrimaryKey*/
	@EmbeddedId
	private ReservationDetailPrimaryKey reservationDetailPrimaryKey;

	/*시작시간*/
	@Column(name="START_TIME")
	private String startTime;
	
	/*종료시간*/
	@Column(name="END_TIME")
	private String endTime;
	
	/*버전*/
	@Version
	private long version;

	@Builder
	public ReservationDetail(ReservationDetailPrimaryKey reservationDetailPrimaryKey, String startTime,
			String endTime) {
		super();
		this.reservationDetailPrimaryKey = reservationDetailPrimaryKey;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public ReservationDetail() {
		super();
		
	}
	
}
