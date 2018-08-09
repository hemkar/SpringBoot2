create table trade
(
tradeId varchar (255) not null,
stockName varchar (255),
brokerCode varchar (255),
brokerName varchar (255),
quantity integer ,
tradeDate date,
settlementDate date ,
buySellIndicator varchar(1)
);

insert into trade 
values ('234','IBM','MS','Morgan Stanley',300,TO_DATE('05/15/2018','MM/DD/YYYY'),TO_DATE('05/12/2018','MM/DD/YYYY'),'B');
insert into trade 
values ('257','HCL','GS','Goldman Sachs',40,TO_DATE('06/21/2018','MM/DD/YYYY'),TO_DATE('06/18/2018','MM/DD/YYYY'),'B');
insert into trade 
values ('326','Pepsi','MS','Morgan Stanley',50,TO_DATE('06/23/2018','MM/DD/YYYY'),TO_DATE('06/18/2018','MM/DD/YYYY'),'B');