drop table member;
-- 테이블 삭제
---- Board 테이블 용 ----
create table board(
bno number(5) primary key,
btitle nvarchar2(30) not null,
bcontent nvarchar2(1000) not null,
bwriter nvarchar2(10) not null,
bdate date not null
);

drop sequence board_seq; -- 기본 자동생성번호 삭제

create sequence board_seq increment by 1 start with 1 nocycle nocache ;

insert into BOARD (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '비오내요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
insert into BOARD (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '안녕하세요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
insert into BOARD (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '감사합니다.~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
insert into BOARD (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '수고하셨내요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
insert into BOARD (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '화이팅하세요~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);
insert into BOARD (bno, btitle, bcontent, bwriter, bdate)
	values (board_seq.nextval, '방갑습니다.~', '비오는데 등교하시는냐고 고생 하셨습니다.', '김기원', sysdate);

select * from board;

---- member 테이블 용 ----

create table member(
mno number(5) primary key,
mid nvarchar2(10) not null,
mpw nvarchar2(10) not null,
mmoney number(16) not null,
mdate date not null
);

-- 더미데이터
insert into member(mno, mid, mpw, mmoney, mdate)
values (board_seq.nextval,'김기원', '1234', '50000', sysdate);
insert into member(mno, mid, mpw, mmoney, mdate)
values (board_seq.nextval,'안희준', '1234', '50000', sysdate);
insert into member(mno, mid, mpw, mmoney, mdate)
values (board_seq.nextval,'조건재', '1234', '50000', sysdate);
insert into member(mno, mid, mpw, mmoney, mdate)
values (board_seq.nextval,'용상엽', '1234', '50000', sysdate);
insert into member(mno, mid, mpw, mmoney, mdate)
values (board_seq.nextval,'양승환', '1234', '50000', sysdate);
select * from MEMBER;