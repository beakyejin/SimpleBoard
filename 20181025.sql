commit;

select * from MEMBER_TBL_02;

insert into MEMBER_TBL_02 
(custno, custname, phone, address, joindate, grade, city)
values
(100001, '���ູ', '010-1111-2222','���� ���빮�� �ְ�1��', '20151202', 'A', '01');

insert into MEMBER_TBL_02 
(custno, custname, phone, address, joindate, grade, city)
values
(100002, '���ູ', '010-1111-3333','���� ���빮�� �ְ�2��', '20151206', 'B', '01');

insert into MEMBER_TBL_02 
(custno, custname, phone, address, joindate, grade, city)
values
(100003, '�����', '010-1111-4444','�︪�� �︪�� ����1��', '20151001', 'B', '30');

insert into MEMBER_TBL_02 
(custno, custname, phone, address, joindate, grade, city)
values
(100004, '�ֻ��', '010-1111-5555','�︪�� �︪�� ����2��', '20151113', 'A', '30');

insert into MEMBER_TBL_02 
(custno, custname, phone, address, joindate, grade, city)
values
(100005, '����ȭ', '010-1111-6666','���ֵ� ���ֽ� �ܳ�����', '20151225', 'B', '60');

insert into MEMBER_TBL_02 
(custno, custname, phone, address, joindate, grade, city)
values
(100006, '������', '010-1111-7777','���ֵ� ���ֽ� ��������', '20151211', 'C', '60');

commit;

desc money_tbl_02;

select * from MONEY_TBL_02;

insert into MONEY_TBL_02
(custno, saleno, pcost, amount, price, pcode, sdate)
VALUES
(100001, 20160001, 500, 5, 2500, 'A001', '20160101');

insert into MONEY_TBL_02
(custno, saleno, pcost, amount, price, pcode, sdate)
VALUES
(100001, 20160002, 1000, 4, 4000, 'A002', '20160101');

insert into MONEY_TBL_02
(custno, saleno, pcost, amount, price, pcode, sdate)
VALUES
(100001, 20160003, 500, 3, 1500, 'A008', '20160101');

insert into MONEY_TBL_02
(custno, saleno, pcost, amount, price, pcode, sdate)
VALUES
(100002, 20160004, 2000, 1, 2000, 'A004', '20160102');

insert into MONEY_TBL_02
(custno, saleno, pcost, amount, price, pcode, sdate)
VALUES
(100002, 20160005, 500, 1, 500, 'A001', '20160103');

insert into MONEY_TBL_02
(custno, saleno, pcost, amount, price, pcode, sdate)
VALUES
(100003, 20160006, 1500, 2, 3000, 'A003', '20160103');

insert into MONEY_TBL_02
(custno, saleno, pcost, amount, price, pcode, sdate)
VALUES
(100004, 20160007, 500, 2, 1000, 'A001', '20160104');

insert into MONEY_TBL_02
(custno, saleno, pcost, amount, price, pcode, sdate)
VALUES
(100004, 20160008, 300, 1, 300, 'A005', '20160104');

insert into MONEY_TBL_02
(custno, saleno, pcost, amount, price, pcode, sdate)
VALUES
(100004, 20160009, 600, 1, 600, 'A006', '20160104');

insert into MONEY_TBL_02
(custno, saleno, pcost, amount, price, pcode, sdate)
VALUES
(100004, 20160010, 3000, 1, 3000, 'A007', '20160106');

commit;

select * from MEMBER_TBL_02;

select to_char(sysdate, 'yyyymmdd') from dual;

SELECT NVL(MAX(CUSTNO), 100000)+1 from MEMBER_TBL_02;

select * from member_tbl_02;

SELECT 
CUSTNO, CUSTNAME, PHONE, ADDRESS, 
to_char(JOINDATE,'yyyymmdd') as joindate , GRADE, CITY 
 FROM MEMBER_TBL_02 ;

select * from MONEY_TBL_02;
select * from member_tbl_02;

select custno, custname, grade from MEMBER_TBL_02;
select custno, price from money_tbl_02;

select custno, sum(price) 
from money_tbl_02
group by custno;

select 
    a.custno as custno, 
    a.custname as custname, 
    decode(a.grade, 'A', 'VIP' 
				 , 'B', '�Ϲ�'
				 , 'C', '����') as grade, 
    sum(b.price) as price
from money_tbl_02 b
join member_tbl_02 a 
on a.custno = b.custno
group by a.custno, a.custname,  a.grade
order by price desc;

select a.custno, a.custname, a.grade,  b.* 
from member_tbl_02 a
inner join money_tbl_02 b
on a.custno = b.custno;

select 
decode(grade, 'A', 'VIP'
           , 'B', '�Ϲ�'
           , 'C', '����') 
           as grade
from member_tbl_02;

select * from member_tbl_02;
select * from money_tbl_02;

DELETE FROM member_tbl_02 WHERE grade = 'w';
commit;

--�� ��޺� �ְ� �����
select a.grade, nvl(max(b.price),0) as price
from member_tbl_02 a
left join money_tbl_02 b
on a.custno = b.custno
group by a.grade;

--��ǰ�ڵ�(pcode) �� ����� ���
select pcode, avg(price)
from money_tbl_02 
group by pcode
order by pcode;

--ȸ���� �� �����
select max(a.custno) as custno
     , max(a.custname) as custname
     , decode(max(a.grade), 'A', 'VIP' 
				 , 'B', '�Ϲ�'
				 , 'C', '����') as grade
     , nvl(sum(b.price), 0) as price
from member_tbl_02 a
join money_tbl_02 b
on a.custno = b.custno
group by a.custno
order by price desc;

select a.custno as custno
     , a.custname as custname
     , decode(a.grade, 'A', 'VIP' 
				 , 'B', '�Ϲ�'
				 , 'C', '����') as grade
     , nvl(sum(b.price), 0) as price
from member_tbl_02 a
join money_tbl_02 b
on a.custno = b.custno
group by a.custno, a.custname, a.grade
order by price desc;


