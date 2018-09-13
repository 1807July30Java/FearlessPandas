create table ADDRESS
(
  ADDRESS_ID NUMBER(10) not null
    primary key,
  APARTMENT  VARCHAR2(255 char),
  CITY       VARCHAR2(255 char),
  STATE      VARCHAR2(255 char),
  STREET     VARCHAR2(255 char),
  ZIP        NUMBER(10)
);

create table APP_USER
(
  USER_ID   NUMBER(10) not null
    primary key,
  BIRTHDATE RAW(255),
  EMAIL     VARCHAR2(255 char),
  F_NAME    VARCHAR2(255 char),
  L_NAME    VARCHAR2(255 char),
  PASSWORD  NUMBER(10),
  PAY_EMAIL VARCHAR2(255 char),
  USERNAME  VARCHAR2(255 char),
  USER_ROLE VARCHAR2(255 char)
);

create table USER_ADDRESS
(
  USER_ID    NUMBER(10) not null
    constraint FK_RR7B0TC5KHP6FEVG5VF9CTN5
    references APP_USER,
  ADDRESS_ID NUMBER(10) not null
    constraint FK_6V1DWL45NT34PCE4P648I1GTX
    references ADDRESS,
  primary key (USER_ID, ADDRESS_ID)
);

create table BOOK_CONDITION
(
  CONDITION_ID NUMBER(10) not null
    primary key,
  NAME         VARCHAR2(255 char)
);

create table BOOK
(
  BOOK_ID      NUMBER(10) not null
    primary key,
  AUTHOR       VARCHAR2(255 char),
  DESCRIPTION  VARCHAR2(255 char),
  PUBLISHER    VARCHAR2(255 char),
  TITLE        VARCHAR2(255 char),
  CONDITION_ID NUMBER(10)
    constraint FK_5FE9QNUSPVE0QD56LXMW4C0XF
    references BOOK_CONDITION,
  ISBN         NUMBER default 0
);

create table AUCTION
(
  AUCTIONID     NUMBER(10) not null
    primary key,
  BUY_IT_NOW    NUMBER(10),
  CREATE_DATE   DATE,
  END_DATE      DATE,
  MINIMUM_PRICE NUMBER(10),
  BOOK_ID       NUMBER(10)
    constraint FK_EFREAPHEMS9L817CJU90QUX4I
    references BOOK,
  USER_ID       NUMBER(10)
    constraint FK_RKEC9WY9A816GNVLAPQUDMIHJ
    references APP_USER,
  ISCLOSED       NULL
);

create table BOOK_IMAGE
(
  IMAGE_ID   NUMBER(10) not null
    primary key,
  IMAGE_BLOB RAW(255),
  BOOK_ID    NUMBER(10)
    constraint FK_I3O4L4F9LRN9G38W8VAYKEIST
    references BOOK
);

create table GENRE
(
  GENRE_ID NUMBER(10) not null
    primary key,
  NAME     VARCHAR2(255 char)
);

create table BOOK_GENRE
(
  BOOK_ID  NUMBER(10) not null
    constraint FK_4WHH4VHBR2SHLQ670CHUGU3EU
    references BOOK,
  GENRE_ID NUMBER(10) not null
    constraint FK_PG539GQVT881BTXCD16RY5GDB
    references GENRE,
  primary key (BOOK_ID, GENRE_ID)
);

create table REVIEW
(
  REVIEW_ID   NUMBER(10) not null
    primary key,
  DETAIL      VARCHAR2(255 char),
  RATING      NUMBER(10),
  REVIEWEE_ID NUMBER(10)
    constraint FK_B2GCM1MHG21JBP1PIYIQMNAE0
    references APP_USER,
  REVIEWER_ID NUMBER(10)
    constraint FK_FPMWQ7VSLMP90VAR2QMW2LHF4
    references APP_USER
);

create table BID
(
  BID_ID     NUMBER(10) not null
    primary key,
  AMOUNT     NUMBER(10),
  AUCTION_ID NUMBER(10)
    constraint FK_O7BKCI1AUXWFW03MTLFEE6WMR
    references AUCTION,
  USER_ID    NUMBER(10)
    constraint FK_7BAB41PNM6TTT1EHMUI0EFCXH
    references APP_USER
);

