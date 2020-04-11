--USERS
INSERT INTO USER (USERNAME, PASSWORD, ENABLED) VALUES ('user','password',TRUE); 
INSERT INTO USER (USERNAME, PASSWORD, ENABLED) VALUES ('admin','password',TRUE);
INSERT INTO USER (USERNAME, PASSWORD, ENABLED) VALUES ('test1@outlook.com','12345',TRUE);
INSERT INTO USER (USERNAME, PASSWORD, ENABLED) VALUES ('test2@outlook.com','12345',TRUE);

--AUTHORITIES
INSERT INTO AUTHORITIES (USERNAME,AUTHORITY) VALUES ('user','ROLE_USER');
INSERT INTO AUTHORITIES (USERNAME,AUTHORITY) VALUES ('admin','ROLE_ADMIN');
INSERT INTO AUTHORITIES (USERNAME,AUTHORITY) VALUES ('test1@outlook.com','ROLE_USER');
INSERT INTO AUTHORITIES (USERNAME,AUTHORITY) VALUES ('test2@outlook.com','ROLE_USER');

--BLOG POST
INSERT INTO BLOG_POST (CONTENT,DRAFT,PUBLISHDATE,TITLE,USER_ID) 
VALUES ('Test Content Spring MVC',false,NOW(),'test',1);
