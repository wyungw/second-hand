create database sechh;
/***创建表***/
create table 房东信息表
(
fno varchar(9) primary key,
fpassword varchar(20) not null,
paypass char(4) not null,
fname nvarchar(5) not null,
fsex nchar(1) not null check(fsex like'[男女]'),
fbir date,
fphone nchar(11) not null,
fmoney int default 0
);

create table 租客信息表
(
zno varchar(9) primary key,
zpassword varchar(20) not null,
zname nvarchar(5) not null,x
zsex nchar(1) check(zsex like'[男女]'),
zbir date,
zphone nchar(11) not null
);

create table 房屋信息表
(
hno varchar(9) primary key,
fno varchar(9) not null,
statuss nvarchar(3) check(statuss in('已租出','待租')) default '待租',
varity nchar(2) not null check(varity in('一居','两居','三居')) default'一居',
adress nvarchar(20) not null,
hsquare int not null,
price  int not null,
photo varchar(255),
foreign key(fno) references 房东信息表(fno)
on delete cascade
on update cascade
);

create table 交易表
(
cno varchar(9) primary key,
zno varchar(9) not null,
hno varchar(9) not null,
ctype nchar(2) not null check(ctype in('租出','退租')),
cdate date not null default getdate(),
foreign key(hno) references 房屋信息表(hno)
on update cascade,
foreign key(zno) references 租客信息表(zno)
on update cascade
);
create table 缴费流水表
(
jno varchar(9) primary key,
hno varchar(9) not null,
zno varchar(9) not null,
jprice  int not null,
jdate date not null default getdate(),
foreign key(hno) references 房屋信息表(hno)
on update cascade,
foreign key(zno) references 租客信息表(zno)
on update cascade
);
/********创建触发器*/
create trigger c1
on 交易表
for insert
as
begin
  declare @ctype nchar(2),@hno varchar(9),@statuss nvarchar(3),@lastd date,@differ int=32
  set @ctype=( select ctype 
             from inserted)
  set @hno=( select hno 
             from inserted)
  set @statuss=(select statuss
                from 房屋信息表
				where 房屋信息表.hno=@hno)
  set @lastd = (select top 1 jdate
                 from 缴费流水表
			     where 缴费流水表.hno=@hno
				 order by 缴费流水表.jdate desc)
  set @differ=DATEDIFF(day,@lastd,GETDATE())
  if (@ctype='租出'and @statuss='待租')
    begin
      update 房屋信息表
	  set statuss='已租出'
	  where 房屋信息表.hno=@hno
	end
  if(@ctype='租出'and @statuss!='待租')
    begin
	  print'该房屋不允许租出'
	  rollback
	end
  if (@ctype='退租'and @statuss='已租出'and @differ<31)
    begin
      update 房屋信息表
	  set statuss='待租'
	  where 房屋信息表.hno=@hno
	end
  if(@ctype='退租'and @statuss='已租出'and @differ>31)
    begin
	  print'该房屋目前不允许退租'
	  rollback
	end
end

--缴费流水表插入一条记录且缴费金额等于房屋表中房屋编号的房屋租金，否则触发器自动改。
create trigger c2
on 缴费流水表
for insert
as
begin
  declare @jprice int,@price int,@hno varchar(9),@jno varchar(9)
  set @hno=(select hno from inserted)
  set @jprice=(select jprice from inserted)
  set @jno=(select jno from inserted)
  set @price =(select price from 房屋信息表 where 房屋信息表.hno=@hno)
  if(@jprice!=@price)
  begin
     update 缴费流水表
	 set jprice=@price
	 where jno=@jno
     print'缴费金额更改为'+cast(@price as varchar(9))
  end
end

--缴费流水表插入一条记录时，对应房屋的房东账户余额发生改变
create trigger c3
on 缴费流水表
for insert
as
begin 
  declare @hno varchar(9),@fno varchar(9),@jprice int
  set @hno=(select hno from inserted)
  set @fno=(select fno from 房屋信息表 where hno=@hno)
  set @jprice=(select price from 房屋信息表 where hno=@hno)
  update 房东信息表
  set fmoney=fmoney+@jprice
  where fno=@fno
end

/*函数*/
--传入房屋编号和租客编号，查询该租客在该房屋是否欠费
create function ifarr(@hno as varchar(9),@zno as varchar(9))
returns nchar(1)
begin
  declare @lasted date,@differ int =32,@fina nchar(1)='是'
  set @lasted=(select top 1 jdate 
               from 缴费流水表 
			   where 缴费流水表.hno=@hno and 缴费流水表.zno=@zno
               order by 缴费流水表.jdate desc)
  set @differ=DATEDIFF(day,@lasted,GETDATE())
  if(@differ<31)
    set @fina='否'
  else
    set @fina='是'
  return @fina
end

--传入房屋编号，查询该房屋是否有照片
create function ifphoto(@hno as varchar(9))
returns nchar(1)
begin
  declare @photo varchar(255)='',@fina nchar(1)='无'
  set @photo=(select photo from 房屋信息表 where hno =@hno)
  if(@photo!='')
     set @fina='有'
  return @fina
end
--传入房屋编号和租客编号，查询该租客是否在租该房
create function ifhav2(@hno as varchar(9),@zno as varchar(9))
returns nchar(1)
begin
  declare @statuss nvarchar(3),@fina nchar(1) ='否',@zzno varchar(9)='0'
  set @statuss=(select statuss from 房屋信息表 where hno =@hno) 
  if(@statuss='已租出')
  begin 
      set @zzno=(select top 1 zno from 交易表 where hno =@hno order by cdate desc)
  end 
  if(@zno=@zzno)
    set @fina='是'
   return @fina
end
    
--传入房屋编号，查询该房屋是否在租
create function ifhav(@hno as varchar(9))
returns nchar(1)
begin
  declare @statuss nvarchar(3),@fina nchar(1)='是'
  set @statuss=(select statuss from 房屋信息表 where hno =@hno)
  if(@statuss='待租')
     set @fina='否'
  return @fina
end
/*创建视图*/
--1.收租总和视图（房屋编号、收租总和）
create view 收租总和(房屋编号,收租总和)
as
select 缴费流水表.hno,sum(jprice)
from 缴费流水表
group by hno
--2.房屋视图（房屋编号、房屋状态、租客姓名、房屋户型、地址、面积、租金）
create view 房屋视图1(房屋编号,房屋状态,租客姓名,房屋户型,地址,面积,租金)
as 
select 房屋信息表.hno,statuss,zname,varity,adress,hsquare,price
from 房屋信息表,交易表,租客信息表
where 房屋信息表.hno=交易表.hno and 交易表.zno=租客信息表.zno
--3.房屋租客视图（房屋编号、租客姓名、租客性别、租客年龄、租客电话、是否欠费。。最新的缴费日期与当前日期相差小于31天）
create view 房屋租客(房屋编号,租客姓名,租客性别,租客年龄,租客电话,是否欠费)
as
select hno,zname,zsex,DATEDIFF(year,zbir,GETDATE()),zphone,dbo.ifarr(hno,交易表.zno)
from 交易表,租客信息表
where 交易表.zno=租客信息表.zno

--4.缴费流水视图（订单编号、租客姓名、房屋编号、缴费价格、缴费日期、是否在租、房东编号）
create view 缴费流水(订单编号,租客姓名,房屋编号,缴费价格,缴费日期,是否在租,房东编号)
as
select jno,zname,缴费流水表.hno,jprice,jdate,dbo.ifhav2(缴费流水表.hno,缴费流水表.zno),fno
from 缴费流水表,租客信息表,房屋信息表
where 缴费流水表.zno=租客信息表.zno and 缴费流水表.hno=房屋信息表.hno
--为租客建立的视图：
--1.可租房屋视图（房屋编号、房东姓名、房东性别、房东年龄、房东电话、房屋户型、地址、面积、租金）
create view 可租房屋(房屋编号,房东姓名,房东年龄,房东电话,房屋户型,地址,面积,租金,照片)
as
select hno,fname,DATEDIFF(year,fbir,getdate()),fphone,varity,adress,hsquare,price,dbo.ifphoto(房屋信息表.hno)
from 房东信息表 ,房屋信息表
where 房东信息表.fno=房屋信息表.fno and 房屋信息表.statuss='待租'
--3.缴费流水视图（订单编号、房屋编号、缴费价格、缴费日期）
create view 缴费流水2(订单编号,房屋编号,房东姓名,缴费价格,缴费日期,是否在租,租客)
as
select jno,房屋信息表.hno,fname,jprice,jdate,dbo.ifhav(房屋信息表.hno),zno
from 缴费流水表,房屋信息表,房东信息表
where 缴费流水表.hno=房屋信息表.hno and 房屋信息表.fno=房东信息表.fno
--4.已租房屋视图（房屋编号、房东姓名、房东性别、房东电话、月租、是否欠费、房屋租客）
create view 已租房屋(房屋编号,房东姓名,房东性别,房东电话,月租,是否欠费,租客)
as
select 交易表.hno,fname,fsex,fphone,房屋信息表.price,dbo.ifarr(交易表.hno,交易表.zno),交易表.zno
from 交易表,房东信息表,房屋信息表
where 交易表.hno=房屋信息表.hno and 房屋信息表.fno=房东信息表.fno and 房屋信息表.statuss='已租出'

--已租房屋视图（房屋编号，房屋类型，租金，面积）
create view 已租房屋2(房屋编号,房屋类型,租金,面积)
as 
select 房屋信息表.hno,varity,price,hsquare
from 房屋信息表
where statuss='已租出'


/*存储过程*/
--传入房屋户型，查询该户型已租出和未租出的房屋数量。
create proc p1 @varity nchar(2)
as
declare @a int=0,@b int=0
set @a=(select count(hno) from 房屋信息表 where varity=@varity and statuss='已租出')
set @b=(select count(hno) from 房屋信息表 where varity=@varity and statuss='待租')
print '已租出房间数   '+'待租出房间数'   
print '      '+cast(@a as char(3))+'       '+cast(@b as char(3))

exec p1 '一居'

--传入租客姓名，查询租客租房数量
create proc p2 @zname nvarchar(5)
as 
declare @sum int =0
set @sum=(select count(hno)
          from 交易表,租客信息表
		  where 租客信息表.zno=交易表.zno and 交易表.ctype='租出'and zname=@zname)
print '租房数量为'
print '    '+cast(@sum as nchar(3))

exec p2 '谢俊'
