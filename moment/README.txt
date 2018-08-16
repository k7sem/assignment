This project intends to be a microservice provider which provide function such as config user, upload image. For interview assignment Moment.


Using Spring boot 1.5 framework, MyBatis and Thymeleaf template. Compiled and developed by IntelliJ IDEA.

[[Configuration]]

[application.yml]
database:              -  configuration for connect to local MySQL 
Location: C:/img  -  This is using for configure local path of uploaded images.
  

[create_table.sql]
	this one is usring for creating table in MySQL db. two tables used, 'user' and 'url'

 
[[Example]]
http:127.0.0.1:8080/userconf
(configure user, add or delte, must add user before uploading image)

http:127.0.0.1:8080/uploading
(Uploading image to server, must specify user id/name)

http:127.0.0.1:8080/getusers
(This is using for Android client to obtain all user data, JSON format)

http:127.0.0.1:8080/geturls
(This is using for Android client to obtain all image urls by specified user id, JSON format)
(Android client should use urls to request images separately)
