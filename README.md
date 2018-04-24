

# Exercise---Technical-Comparison-of-an-SQL-and-Graph-Database
*Emmely Lundberg cph-el69*



#### Present the execution time of each query each of the 20 random nodes/persons per database.



#### Present the average and the median runtime of each of the queries per database.

|   |  Neo4 Average |  Neo4 Median | PostGresSQL Average  |  PostGresSQL Median |
|---|---|---|---|---|
|getAllPersonsDepthOne:| 0.55  | 0.54  |  0.55 | 	0.56  |
|getAllPersonsDepthTwo:|  0.62 | 0.60  |  1.80 |  1.91 |
|getAllPersonsDepthThree:|  0.6 | 0.59  |  3.19 |  3.4 |



#### Give an explanation of the differences in your time measurements.

For every query the nodes grows appropriately logarithmically by a base n. If one node as exactly ten endorsements the it would grow logarithmically with base 10 example 10, 100, 1000.
From the results i have collected I can estimate that the Neo4J might have a appropriately linear+logarithmic running time. It's almost linear even though nodes N grows logarithmically.
From the results i have collected I can estimate that the Relational database might have a logarithmic running time. Between first and second and second and third the times doubles when nodes grows quadratically as example 10 -100. 

#### Conclude which database is better suited for this kind of queries and explain why.
For queries up to level three the Neo4J has the best performance.

In my test the Neo4J had no performance at all for level four and five.

The relational databases performed slightly slower but robust and reliable.




#### 1. Setup an SQL and a Neo4j database respectively.
#### 2. Import the data from the social network 
(endorsement graph https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/raw/master/data/archive_graph.tar.gz) into a Neo4j database and into an SQL database respectively.






#### Set up a virtual machine



Spin up a 8G droplet on Digital Ocean
ssh into the machine 167.99.249.26
```
ssh root@167.99.249.26
```

167.99.249.26:7474 - Neo4J
167.99.249.26:8888 - Neo4J


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



#### Import the PostGresSQL

```
sudo docker run -p 5432:5432 -v /root/import:/home/import --name data -d jegp/soft2018-data 
sudo docker exec -it data bash 
psql -d appdev -U appdev

CREATE TABLE person(id BIGINT, name VARCHAR, job VARCHAR, birthday VARCHAR);
CREATE TABLE endorsement(source_node_id_node_id_node_id BIGINT, target_node_id BIGINT);
\copy person FROM '/home/import/social_network_nodes.csv' DELIMITER ',' CSV HEADER;
\copy endorsement FROM '/home/import/social_network_endorsement.csv' DELIMITER ',' CSV HEADER;
```
#### Create Index
``` 
CREATE UNIQUE INDEX index_id ON person (id);
CREATE INDEX index_name ON person (name);
CREATE INDEX index_source ON endorsement (name);

\q
exit
sudo docker run -p 8888:8888 -v /vagrant/jupyter:/home/jovyan --name jupyter --link data -it jegp/soft2018-jupyter 
```

#### Import Neo4J Database

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

#### Create Index in Neo4J client
```
CREATE INDEX ON :Person(name)
```



#### Queries

##### SQL

```
SELECT * FROM chinook.person a IN  (select * FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name='"+person+"') b ON a.id=b.target_node_id;
SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"'));
SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"')));
SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert'))));
SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert')))));
```
##### Cypher

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

*Even though index I could not run this level in Neo4J took too much time.*

- endorsements of depth four.
MATCH ({name:"Jeanie Mountcastle"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other)
RETURN count(other)

- endorsements of depth five.
MATCH ({name:"Jeanie Mountcastle"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other)
RETURN count(other)

















getAllPersonsDepthOne:	0.055400000000000005(Neo4J)	0.055(Neo4J)	0.022400000000000007(PostGreSQL)	0.022(PostGreSQL)	
getAllPersonsDepthTwo:	0.05515000000000002(Neo4J)	0.055(Neo4J)	0.023500000000000004(PostGreSQL)	0.022(PostGreSQL)	
getAllPersonsDepthThree:	0.06425000000000002(Neo4J)	0.0555(Neo4J)	0.027350000000000006(PostGreSQL)	0.024(PostGreSQL)	
getAllPersonsDepthFour:	0.08015(Neo4J)	0.077(Neo4J)	0.029300000000000003(PostGreSQL)	0.026(PostGreSQL)	
getAllPersonsDepthFive:	0.15650000000000003(Neo4J)	0.14650000000000002(Neo4J)	0.025750000000000006(PostGreSQL)	0.025500000000000002(PostGreSQL)	