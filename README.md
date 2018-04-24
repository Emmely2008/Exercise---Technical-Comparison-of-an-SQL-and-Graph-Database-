

# Exercise---Technical-Comparison-of-an-SQL-and-Graph-Database
*Emmely Lundberg cph-el69*



### Present the execution time of each query each of the 20 random nodes/persons per database.

|   |  Neo4  | PostGresSQL |
|---|---|---|
|getAllPersonsDepthOne:	|{0.02,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.06}	|{0.06,0.06,0.06,0.06,0.06,0.06,0.06,0.06,0.06,0.06,0.06,0.07,0.07,0.08,0.08,0.09,0.1,0.1,0.16,0.22}	|
|getAllPersonsDepthTwo:	|{0.02,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.05}	|{0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.06,0.06,0.06,0.06,0.06,0.07,0.07,0.07,0.07,0.07,0.1,0.12,0.16}	|
|getAllPersonsDepthThree:	|{0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.04}	|{0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.06,0.06,0.06,0.06,0.06,0.12,0.12,0.14,0.35,0.45}	|
|getAllPersonsDepthFour:	|{0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.04}	|{0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.06,0.06,0.06,0.14,0.65,0.92,2.25,2.37}	|
|getAllPersonsDepthFive:	|{0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.03,0.04}	|{0.05,0.05,0.05,0.05,0.05,0.05,0.05,0.06,0.06,0.06,0.06,0.06,0.06,0.06,0.08,0.19,12.31,16.03,35.11,40.45}	|


### Present the average and the median runtime of each of the queries per database.

|   | Neo4  Average  | Neo4  Median | PostGresSQL Average |   PostGresSQL Median |
|---|---|---|---|---|
|getAllPersonsDepthOne:	|0.03	|0.03	||0.08	|0.06	|
|getAllPersonsDepthTwo:	|0.03	|0.03	||0.07	|0.06	|
|getAllPersonsDepthThree:	|0.03	|0.03	||0.1	|0.05	|
|getAllPersonsDepthFour:	|0.03	|0.03	||0.36	|0.05	|
|getAllPersonsDepthFive:	|0.03	|0.03	||5.25	|0.06	|


### Give an explanation of the differences in your time measurements.

For every query the number of nodes grows appropriately logarithmically by a base ~n. 
If one node as exactly ten endorsements then the number of nodes would grow logarithmically with base 10 example 10 (first depth), 100 (second depth), 1000 (third depth etc.

[![https://gyazo.com/531e6af4861d0f0d68e66682e5ae17e9](https://i.gyazo.com/531e6af4861d0f0d68e66682e5ae17e9.png)](https://gyazo.com/531e6af4861d0f0d68e66682e5ae17e9)

- From the results I can estimate that the Neo4J might have a appropriately constant running time. The benchmark test shows that the time is constant even when the data grows, 
the number of nodes N grows logarithmically. The time complexity of the database queries would be O(1.


- From the results I can estimate that the Relational database might have a ~quadratic running time. 
Between first and second and second and third the times doubles (very roughly calculated) when nodes grows quadratically as example 10 (first depth), 100 (second depth), 1000 (third depth etc. 

### Conclude which database is better suited for this kind of queries and explain why.

- Neo4J has the best performance for queries like this.
- The relational databases performed slightly slower for operations like this.

I expected the relational database to be much slower. I didn't expect the Neo4J to have almost linear running time when data grows logarithmically.



__________________

## Setting Up the solution

1. Setup an SQL and a Neo4j database respectively.
2. Import the data from the social network 
(endorsement graph https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/raw/master/data/archive_graph.tar.gz) into a Neo4j database and into an SQL database respectively.


### Set Up A Virtual Machine on Digital Ocean

Create a 8G droplet on Digital Ocean
ssh into the machine 167.99.249.26
```
ssh root@167.99.249.26
```

167.99.249.26:7474 - Neo4J

167.99.249.26:8888 - PostGreSQL


Install docker on the virtual machine and prepare the data
```
wget -O - https://bit.ly/docker-install | bash

mkdir import
mkdir plugins

cd import/

wget https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/raw/master/data/archive_graph.tar.gz
tar xf archive_graph.tar.gz
sudo rm archive_graph.tar.gz
```

### Import The Data To Postgressql

```
sudo docker run -p 5432:5432 -v /root/import:/home/import --name data -d jegp/soft2018-data 
sudo docker exec -it data bash 
psql -d appdev -U appdev

CREATE TABLE person(id BIGINT, name VARCHAR, job VARCHAR, birthday VARCHAR);
CREATE TABLE endorsement(source_node_id_node_id_node_id BIGINT, target_node_id BIGINT);
\copy person FROM '/home/import/social_network_nodes.csv' DELIMITER ',' CSV HEADER;
\copy endorsement FROM '/home/import/social_network_endorsement.csv' DELIMITER ',' CSV HEADER;
```
### Create Index Postgressql
``` 
CREATE UNIQUE INDEX index_id ON person (id);
CREATE INDEX index_name ON person (name);
CREATE INDEX index_source ON endorsement (name);

\q
exit
sudo docker run -p 8888:8888 -v /vagrant/jupyter:/home/jovyan --name jupyter --link data -it jegp/soft2018-jupyter 
```

### Import Neo4J Data

```
head social_network_nodes.csv


sed -i -E '1s/.*/:ID,name,job,birthday/' social_network_nodes.csv
head social_network_nodes.csv


sed -i -E '1s/.*/:START_ID,:END_ID/' social_network_edges.csv
head social_network_edges.csv


cd ..

cd plugins
echo "Downloading APOC plugin"
wget https://github.com/neo4j-contrib/neo4j-apoc-procedures/releases/download/3.3.0.1/apoc-3.3.0.1-all.jar
echo "Downloading algorithms plugin"
wget https://github.com/neo4j-contrib/neo4j-graph-algorithms/releases/download/3.3.2.0/graph-algorithms-algo-3.3.2.0.jar



cd ..


docker run \
    -d --name neo4j \
    --rm \
    --publish=7474:7474 \
    --publish=7687:7687 \
    --volume=$(pwd)/import:/import \
    --volume=$(pwd)/plugins:/plugins \
    --env NEO4J_AUTH=neo4j/class \
    --env=NEO4J_dbms_security_procedures_unrestricted=apoc.\\\*,algo.\\\* \
	--env=NEO4J_dbms_memory_pagecache_size=12G \
	--env=NEO4J_dbms_memory_heap_initial__size=12G \
	--env=NEO4J_dbms_memory_heap_max__size=12G \
    neo4j
	
	
	
	

docker exec neo4j sh -c 'neo4j stop'
docker exec neo4j sh -c 'rm -rf data/databases/graph.db'	

docker exec neo4j sh -c 'neo4j-admin import \
    --nodes:Person /import/social_network_nodes.csv \
    --relationships:ENDORSES /import/social_network_edges.csv \
    --ignore-missing-nodes=true \
    --ignore-duplicate-nodes=true \
    --id-type=INTEGER'

docker restart neo4j
```

### Create Index in Neo4J via client
```
CREATE INDEX ON :Person(name)

CREATE INDEX ON :Person(ID)
CALL db.indexes()

```



### Construct queries



Construct queries in SQL and in Cypher, which find

- all persons that a person endorses, i.e., endorsements of depth one.
- all persons that are endorsed by endorsed persons of a person, i.e., endorsements of depth two.
- endorsements of depth three.
- endorsements of depth four.
- endorsements of depth five.

#### SQL

```
 endorsements of depth one.
SELECT * FROM chinook.person a IN  (select * FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name='"+person+"') b ON a.id=b.target_node_id;
- endorsements of depth two.
SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"'));
- endorsements of depth three.
SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"')));
- endorsements of depth four.
SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert'))));
- endorsements of depth five.
SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert')))));
```
#### Cypher

```
- all persons that a person endorses, i.e., endorsements of depth one.
MATCH ({name:"Jeanie Mountcastle"})-[:ENDORSES]->(other)
RETURN count(other)

- endorsements of depth two.
MATCH ({name:"Jeanie Mountcastle"})-[:ENDORSES]->()-[:ENDORSES]->(other)
RETURN count(other)

- endorsements of depth three.
MATCH ({name:"Jeanie Mountcastle"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other)
RETURN count(other)
```


- endorsements of depth four.
MATCH ({name:"Jeanie Mountcastle"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other)
RETURN count(other)

- endorsements of depth five.
MATCH ({name:"Jeanie Mountcastle"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other)
RETURN count(other)






