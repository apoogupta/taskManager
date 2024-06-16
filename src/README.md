##commands for connecting with MYSQL


Root USer password - Divya@28
- mysql -u root -p password - apoorva123
- mysql> create database taskmanager;
- 
to create new user
  CREATE USER 'apoorva'@'localhost' IDENTIFIED BY '123';


To create a new database, run the following commands at the mysql prompt:

- mysql> create database db_example; -- Creates the new database
- mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
- mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database



CREATE USER 'apoorva'@'localhost' IDENTIFIED BY '123';
GRANT ALL PRIVILEGES ON *.* TO 'apoorva'@'localhost' WITH GRANT OPTION;
mysql -u apoorva -p ;

data dir = /usr/local/mysql/data
sudo chown mysql:/usr/local/mysql/data
sudo chmod 755 /usr/local/mysql/data

sudo /usr/local/mysql/support-files/mysql.server start
