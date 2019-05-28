create database sechh;
/***������***/
create table ������Ϣ��
(
fno varchar(9) primary key,
fpassword varchar(20) not null,
paypass char(4) not null,
fname nvarchar(5) not null,
fsex nchar(1) not null check(fsex like'[��Ů]'),
fbir date,
fphone nchar(11) not null,
fmoney int default 0
);

create table �����Ϣ��
(
zno varchar(9) primary key,
zpassword varchar(20) not null,
zname nvarchar(5) not null,x
zsex nchar(1) check(zsex like'[��Ů]'),
zbir date,
zphone nchar(11) not null
);

create table ������Ϣ��
(
hno varchar(9) primary key,
fno varchar(9) not null,
statuss nvarchar(3) check(statuss in('�����','����')) default '����',
varity nchar(2) not null check(varity in('һ��','����','����')) default'һ��',
adress nvarchar(20) not null,
hsquare int not null,
price  int not null,
photo varchar(255),
foreign key(fno) references ������Ϣ��(fno)
on delete cascade
on update cascade
);

create table ���ױ�
(
cno varchar(9) primary key,
zno varchar(9) not null,
hno varchar(9) not null,
ctype nchar(2) not null check(ctype in('���','����')),
cdate date not null default getdate(),
foreign key(hno) references ������Ϣ��(hno)
on update cascade,
foreign key(zno) references �����Ϣ��(zno)
on update cascade
);
create table �ɷ���ˮ��
(
jno varchar(9) primary key,
hno varchar(9) not null,
zno varchar(9) not null,
jprice  int not null,
jdate date not null default getdate(),
foreign key(hno) references ������Ϣ��(hno)
on update cascade,
foreign key(zno) references �����Ϣ��(zno)
on update cascade
);
/********����������*/
create trigger c1
on ���ױ�
for insert
as
begin
  declare @ctype nchar(2),@hno varchar(9),@statuss nvarchar(3),@lastd date,@differ int=32
  set @ctype=( select ctype 
             from inserted)
  set @hno=( select hno 
             from inserted)
  set @statuss=(select statuss
                from ������Ϣ��
				where ������Ϣ��.hno=@hno)
  set @lastd = (select top 1 jdate
                 from �ɷ���ˮ��
			     where �ɷ���ˮ��.hno=@hno
				 order by �ɷ���ˮ��.jdate desc)
  set @differ=DATEDIFF(day,@lastd,GETDATE())
  if (@ctype='���'and @statuss='����')
    begin
      update ������Ϣ��
	  set statuss='�����'
	  where ������Ϣ��.hno=@hno
	end
  if(@ctype='���'and @statuss!='����')
    begin
	  print'�÷��ݲ��������'
	  rollback
	end
  if (@ctype='����'and @statuss='�����'and @differ<31)
    begin
      update ������Ϣ��
	  set statuss='����'
	  where ������Ϣ��.hno=@hno
	end
  if(@ctype='����'and @statuss='�����'and @differ>31)
    begin
	  print'�÷���Ŀǰ����������'
	  rollback
	end
end

--�ɷ���ˮ�����һ����¼�ҽɷѽ����ڷ��ݱ��з��ݱ�ŵķ�����𣬷��򴥷����Զ��ġ�
create trigger c2
on �ɷ���ˮ��
for insert
as
begin
  declare @jprice int,@price int,@hno varchar(9),@jno varchar(9)
  set @hno=(select hno from inserted)
  set @jprice=(select jprice from inserted)
  set @jno=(select jno from inserted)
  set @price =(select price from ������Ϣ�� where ������Ϣ��.hno=@hno)
  if(@jprice!=@price)
  begin
     update �ɷ���ˮ��
	 set jprice=@price
	 where jno=@jno
     print'�ɷѽ�����Ϊ'+cast(@price as varchar(9))
  end
end

--�ɷ���ˮ�����һ����¼ʱ����Ӧ���ݵķ����˻������ı�
create trigger c3
on �ɷ���ˮ��
for insert
as
begin 
  declare @hno varchar(9),@fno varchar(9),@jprice int
  set @hno=(select hno from inserted)
  set @fno=(select fno from ������Ϣ�� where hno=@hno)
  set @jprice=(select price from ������Ϣ�� where hno=@hno)
  update ������Ϣ��
  set fmoney=fmoney+@jprice
  where fno=@fno
end

/*����*/
--���뷿�ݱ�ź���ͱ�ţ���ѯ������ڸ÷����Ƿ�Ƿ��
create function ifarr(@hno as varchar(9),@zno as varchar(9))
returns nchar(1)
begin
  declare @lasted date,@differ int =32,@fina nchar(1)='��'
  set @lasted=(select top 1 jdate 
               from �ɷ���ˮ�� 
			   where �ɷ���ˮ��.hno=@hno and �ɷ���ˮ��.zno=@zno
               order by �ɷ���ˮ��.jdate desc)
  set @differ=DATEDIFF(day,@lasted,GETDATE())
  if(@differ<31)
    set @fina='��'
  else
    set @fina='��'
  return @fina
end

--���뷿�ݱ�ţ���ѯ�÷����Ƿ�����Ƭ
create function ifphoto(@hno as varchar(9))
returns nchar(1)
begin
  declare @photo varchar(255)='',@fina nchar(1)='��'
  set @photo=(select photo from ������Ϣ�� where hno =@hno)
  if(@photo!='')
     set @fina='��'
  return @fina
end
--���뷿�ݱ�ź���ͱ�ţ���ѯ������Ƿ�����÷�
create function ifhav2(@hno as varchar(9),@zno as varchar(9))
returns nchar(1)
begin
  declare @statuss nvarchar(3),@fina nchar(1) ='��',@zzno varchar(9)='0'
  set @statuss=(select statuss from ������Ϣ�� where hno =@hno) 
  if(@statuss='�����')
  begin 
      set @zzno=(select top 1 zno from ���ױ� where hno =@hno order by cdate desc)
  end 
  if(@zno=@zzno)
    set @fina='��'
   return @fina
end
    
--���뷿�ݱ�ţ���ѯ�÷����Ƿ�����
create function ifhav(@hno as varchar(9))
returns nchar(1)
begin
  declare @statuss nvarchar(3),@fina nchar(1)='��'
  set @statuss=(select statuss from ������Ϣ�� where hno =@hno)
  if(@statuss='����')
     set @fina='��'
  return @fina
end
/*������ͼ*/
--1.�����ܺ���ͼ�����ݱ�š������ܺͣ�
create view �����ܺ�(���ݱ��,�����ܺ�)
as
select �ɷ���ˮ��.hno,sum(jprice)
from �ɷ���ˮ��
group by hno
--2.������ͼ�����ݱ�š�����״̬��������������ݻ��͡���ַ����������
create view ������ͼ1(���ݱ��,����״̬,�������,���ݻ���,��ַ,���,���)
as 
select ������Ϣ��.hno,statuss,zname,varity,adress,hsquare,price
from ������Ϣ��,���ױ�,�����Ϣ��
where ������Ϣ��.hno=���ױ�.hno and ���ױ�.zno=�����Ϣ��.zno
--3.���������ͼ�����ݱ�š��������������Ա�������䡢��͵绰���Ƿ�Ƿ�ѡ������µĽɷ������뵱ǰ�������С��31�죩
create view �������(���ݱ��,�������,����Ա�,�������,��͵绰,�Ƿ�Ƿ��)
as
select hno,zname,zsex,DATEDIFF(year,zbir,GETDATE()),zphone,dbo.ifarr(hno,���ױ�.zno)
from ���ױ�,�����Ϣ��
where ���ױ�.zno=�����Ϣ��.zno

--4.�ɷ���ˮ��ͼ��������š�������������ݱ�š��ɷѼ۸񡢽ɷ����ڡ��Ƿ����⡢������ţ�
create view �ɷ���ˮ(�������,�������,���ݱ��,�ɷѼ۸�,�ɷ�����,�Ƿ�����,�������)
as
select jno,zname,�ɷ���ˮ��.hno,jprice,jdate,dbo.ifhav2(�ɷ���ˮ��.hno,�ɷ���ˮ��.zno),fno
from �ɷ���ˮ��,�����Ϣ��,������Ϣ��
where �ɷ���ˮ��.zno=�����Ϣ��.zno and �ɷ���ˮ��.hno=������Ϣ��.hno
--Ϊ��ͽ�������ͼ��
--1.���ⷿ����ͼ�����ݱ�š����������������Ա𡢷������䡢�����绰�����ݻ��͡���ַ����������
create view ���ⷿ��(���ݱ��,��������,��������,�����绰,���ݻ���,��ַ,���,���,��Ƭ)
as
select hno,fname,DATEDIFF(year,fbir,getdate()),fphone,varity,adress,hsquare,price,dbo.ifphoto(������Ϣ��.hno)
from ������Ϣ�� ,������Ϣ��
where ������Ϣ��.fno=������Ϣ��.fno and ������Ϣ��.statuss='����'
--3.�ɷ���ˮ��ͼ��������š����ݱ�š��ɷѼ۸񡢽ɷ����ڣ�
create view �ɷ���ˮ2(�������,���ݱ��,��������,�ɷѼ۸�,�ɷ�����,�Ƿ�����,���)
as
select jno,������Ϣ��.hno,fname,jprice,jdate,dbo.ifhav(������Ϣ��.hno),zno
from �ɷ���ˮ��,������Ϣ��,������Ϣ��
where �ɷ���ˮ��.hno=������Ϣ��.hno and ������Ϣ��.fno=������Ϣ��.fno
--4.���ⷿ����ͼ�����ݱ�š����������������Ա𡢷����绰�����⡢�Ƿ�Ƿ�ѡ�������ͣ�
create view ���ⷿ��(���ݱ��,��������,�����Ա�,�����绰,����,�Ƿ�Ƿ��,���)
as
select ���ױ�.hno,fname,fsex,fphone,������Ϣ��.price,dbo.ifarr(���ױ�.hno,���ױ�.zno),���ױ�.zno
from ���ױ�,������Ϣ��,������Ϣ��
where ���ױ�.hno=������Ϣ��.hno and ������Ϣ��.fno=������Ϣ��.fno and ������Ϣ��.statuss='�����'

--���ⷿ����ͼ�����ݱ�ţ��������ͣ���������
create view ���ⷿ��2(���ݱ��,��������,���,���)
as 
select ������Ϣ��.hno,varity,price,hsquare
from ������Ϣ��
where statuss='�����'


/*�洢����*/
--���뷿�ݻ��ͣ���ѯ�û����������δ����ķ���������
create proc p1 @varity nchar(2)
as
declare @a int=0,@b int=0
set @a=(select count(hno) from ������Ϣ�� where varity=@varity and statuss='�����')
set @b=(select count(hno) from ������Ϣ�� where varity=@varity and statuss='����')
print '�����������   '+'�����������'   
print '      '+cast(@a as char(3))+'       '+cast(@b as char(3))

exec p1 'һ��'

--���������������ѯ����ⷿ����
create proc p2 @zname nvarchar(5)
as 
declare @sum int =0
set @sum=(select count(hno)
          from ���ױ�,�����Ϣ��
		  where �����Ϣ��.zno=���ױ�.zno and ���ױ�.ctype='���'and zname=@zname)
print '�ⷿ����Ϊ'
print '    '+cast(@sum as nchar(3))

exec p2 'л��'
