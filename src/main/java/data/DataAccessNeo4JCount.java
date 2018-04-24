package data;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

public class DataAccessNeo4JCount implements DataAccessorCount {
    private static final boolean DEBUG = true;
    private DBConnectorNeo4J dbConnectorNeo4J;
    private String name;

    public DataAccessNeo4JCount(DBConnectorNeo4J dbConnectorNeo4J) {
        this.dbConnectorNeo4J = dbConnectorNeo4J;
        this.name = "Neo4JCount";
    }

    public int getAllPersonsDepthOne(String person) {
        /*- all persons that a person endorses, i.e., endorsements of depth one.
        MATCH ({name:"Sol Linkert"})-[:ENDORSES]->(other)
        RETURN other*/
        int count = 0;

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            String query = "MATCH ({name:\"" + person + "\"})-[:ENDORSES]->(other) RETURN count(other);";
            //String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
            //String query = "MATCH ({name:\"Sol Linkert\"})-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
            Session session = driver.session();
            // Run a query matching all nodes
            StatementResult result = session.run(query);

            count = getResults(result);
            session.close();
            //driver.close();

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return count;
    }

    public int getAllPersonsDepthTwo(String person) {
        /*- endorsements of depth two.
            MATCH ({name:"Sol Linkert"})-[:ENDORSES]->()-[:ENDORSES]->(other_other)
            RETURN other_other*/
        int count = 0;

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            // String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->(other_other) RETURN other_other.name as name, other_other.job as job, other_other.birthday as birthday;";
            String query = "MATCH ({name:\"" + person + "\"})-[:ENDORSES]->()-[:ENDORSES]->(other_other) RETURN count(other_other);";
            Session session = driver.session();

            // Run a query matching all nodes
            StatementResult result = session.run(query);

            count = getResults(result);
            session.close();
            //driver.close();

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return count;
    }


    public int getAllPersonsDepthThree(String person) {
        /*- endorsements of depth three.
        MATCH ({name:"Sol Linkert"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other_other_other)
        RETURN other_other_other*/
        int count = 0;

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            //String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
            String query = "MATCH ({name:\"" + person + "\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN count(other);";
            Session session = driver.session();

            // Run a query matching all nodes
            StatementResult result = session.run(query);

            count = getResults(result);
            session.close();
            //driver.close();

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return count;

    }

    public int getAllPersonsDepthFour(String person) {
        /*- endorsements of depth four.
        MATCH ({name:"Sol Linkert"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other_other_other_other)
        RETURN other_other_other_other*/
        //String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
        int count = 0;

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            //String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
            String query = "MATCH ({name:\"" + person + "\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN count(other);";

            Session session = driver.session();

            // Run a query matching all nodes
            StatementResult result = session.run(query);

            count = getResults(result);
            session.close();
            //driver.close();

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return count;
    }


    public int getAllPersonsDepthFive(String person) {
        /* - endorsements of depth five.
        MATCH ({name:"Sol Linkert"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other_other_other_other_other)
        RETURN other_other_other_other_other*/
        //String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
        int count = 0;

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            //String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
            String query = "MATCH ({name:\"" + person + "\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN count(other);";

            Session session = driver.session();

            // Run a query matching all nodes
            StatementResult result = session.run(query);

            count = getResults(result);
            session.close();
            //driver.close();

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return count;
    }

    public String getName() {
        return this.name;
    }

    private int getResults(StatementResult result) {

        int i = 0;
        while (result.hasNext()) {
            Record record = result.next();

           // = record.get("count").asInt();


        }
        return i;
    }

    public void close() {
        Driver driver = dbConnectorNeo4J.getDriver();
        driver.close();

    }

}
