insert into CONFERENCE (ID ,CONFERENCE_NAME ) values (1,'회의실1')
insert into CONFERENCE (ID ,CONFERENCE_NAME ) values (2,'회의실2')
insert into CONFERENCE (ID ,CONFERENCE_NAME ) values (3,'회의실3')
insert into CONFERENCE (ID ,CONFERENCE_NAME ) values (4,'회의실4')
insert into CONFERENCE (ID ,CONFERENCE_NAME ) values (5,'회의실5')

insert into RESERVATION (ID,RESERVATION_NAME,REGIST_NAME,REGIST_DT,REPETITIONS_OF_NUM,VERSION ) values (1,'회의1','RAN',NOW(),1,0)
insert into RESERVATION (ID,RESERVATION_NAME,REGIST_NAME,REGIST_DT,REPETITIONS_OF_NUM,VERSION ) values (2,'회의2','LIM',NOW(),2,0)


insert into RESERVATED_DATE (ID,RESERVATION_DATE,VERSION ) values (1,'2019-12-25',0)
insert into RESERVATED_DATE (ID,RESERVATION_DATE,VERSION ) values (2,'2018-12-27',0)

insert into RESERVATION_DETAIL (CONFERENCE_ID,RESERVATION_ID,RESERVATED_DATE_ID,START_TIME,END_TIME,VERSION ) values (1,1,1,'08:00','14:00',0)
insert into RESERVATION_DETAIL (CONFERENCE_ID,RESERVATION_ID,RESERVATED_DATE_ID,START_TIME,END_TIME,VERSION ) values (1,1,2,'14:00','15:00',0)
insert into RESERVATION_DETAIL (CONFERENCE_ID,RESERVATION_ID,RESERVATED_DATE_ID,START_TIME,END_TIME,VERSION) values (2,2,2,'09:00','11:00',0)


insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122517')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122518')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122519')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122520')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122521')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122522')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122523')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122524')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122525')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122526')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122527')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122528')

insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122529')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('12019122530')

insert into RESERVATED_HASH (RESERVATED_HASH) values ('22019122719')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('22019122720')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('22019122721')
insert into RESERVATED_HASH (RESERVATED_HASH) values ('22019122722')