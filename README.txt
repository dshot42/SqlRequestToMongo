convert classical SQL request as String to Mongo criteria Request


Complexity : 
simple request : Select * from where … (AND / OR / = / != / < / >)
isolation like : select * from model where toto = 1 OR ( titi != 2 AND tata = 3 ) 

request in  : select * from model where toto in (1,2,3)

complexe request  with joins (inner , outer, left) doesn't supported ! 
