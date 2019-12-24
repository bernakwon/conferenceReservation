package com.berna.domain.reservation.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author hrkwon
 * @className ReservatedHash
 * @description 예약된 Hash 값 저장 테이블
 * **/
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name="RESERVATED_HASH")
public class ReservatedHash {

    @Id
    @Column(name="RESERVATED_HASH")
    /*예약 Hash key*/
    String reservatedHash;

    /*등록일시*/
    @Column(name="REGIST_DT")
    @CreationTimestamp
    LocalDateTime registDt;

    /*버전*/
    String version;
}
