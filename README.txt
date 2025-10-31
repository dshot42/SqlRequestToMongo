convert classical SQL request as String to Mongo criteria Request

execute mongo restore :
mongorestore

download mongo-tools ->
then : > cd C:\Program Files\MongoDB\Tools\100\bin
 > mongorestore C:\Users\come_\Desktop\bu\IdeaProjects\testSpringMongoGeneric\mongo_database_bu\data


Complexity : 
simple request : Select * from where … (AND / OR / = / != / < / >)
isolation like : select * from model where toto = 1 OR ( titi != 2 AND tata = 3 ) 

request in  : select * from model where toto in (1,2,3)

complexe request  with joins (inner , outer, left) doesn't supported ! 
