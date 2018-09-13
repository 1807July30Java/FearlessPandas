INSERT INTO APP_USER VALUES (200, null , 'JeremyDStraus@gmail.com', 'Jeremy', 'Straus', 3433489, 'JeremyDStraus@Gmail.com', 'Jstraus', null);

INSERT INTO USER_ADDRESS (USER_ID, ADDRESS_ID) VALUES (200, 22);

INSERT INTO ADDRESS (ADDRESS_ID, APARTMENT, CITY, STATE, STREET, ZIP) VALUES (22, '11', 'Queens', 'ny', '123 street', 11404);

INSERT INTO BOOK (BOOK_ID, AUTHOR, DESCRIPTION, PUBLISHER, TITLE, CONDITION_ID, ISBN) VALUES (5, 'Fyodor Dostoyevsky', null, 'penguin classics', 'The Brothers Karamazov', 5, 0);
INSERT INTO BOOK (BOOK_ID, AUTHOR, DESCRIPTION, PUBLISHER, TITLE, CONDITION_ID, ISBN) VALUES (6, 'Fyodor Dostoyevsky', null, 'penguin classics', 'The Brothers Karamazov', 6, 0);
INSERT INTO BOOK (BOOK_ID, AUTHOR, DESCRIPTION, PUBLISHER, TITLE, CONDITION_ID, ISBN) VALUES (4, 'Fyodor Dostoyevsky', null, 'penguin classics', 'The Brothers Karamazov', 4, 0);

INSERT INTO AUCTION (AUCTIONID, BUY_IT_NOW, CREATE_DATE, END_DATE, MINIMUM_PRICE, BOOK_ID, USER_ID, ISCLOSED) VALUES (191, 45, TO_DATE('2018-09-12 17:31:02', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-12 17:31:02', 'YYYY-MM-DD HH24:MI:SS'), 20, 4, 200, null);
INSERT INTO AUCTION (AUCTIONID, BUY_IT_NOW, CREATE_DATE, END_DATE, MINIMUM_PRICE, BOOK_ID, USER_ID, ISCLOSED) VALUES (183, 45, TO_DATE('2018-09-12 11:23:02', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-12 11:23:02', 'YYYY-MM-DD HH24:MI:SS'), 20, 5, 200, null);
INSERT INTO AUCTION (AUCTIONID, BUY_IT_NOW, CREATE_DATE, END_DATE, MINIMUM_PRICE, BOOK_ID, USER_ID, ISCLOSED) VALUES (184, 45, TO_DATE('2018-09-12 11:23:02', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-12 11:23:02', 'YYYY-MM-DD HH24:MI:SS'), 20, 6, 200, null);

