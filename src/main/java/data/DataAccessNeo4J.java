package data;

import model.Person;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import java.util.ArrayList;
import java.util.List;

public class DataAccessNeo4J implements DataAccessor {
    private static final boolean DEBUG = true;
    private DBConnectorNeo4J dbConnectorNeo4J;
    private String name;

    public DataAccessNeo4J(DBConnectorNeo4J dbConnectorNeo4J) {
        this.dbConnectorNeo4J = dbConnectorNeo4J;
        this.name = "Neo4J";
    }

    public List<Person> getAllPersonsDepthOne(String person) {
        /*- all persons that a person endorses, i.e., endorsements of depth one.
        MATCH ({name:"Sol Linkert"})-[:ENDORSES]->(other)
        RETURN other*/
        List<Person> list = new ArrayList();

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
            //String query = "MATCH ({name:\"Sol Linkert\"})-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
            Session session = driver.session();
            // Run a query matching all nodes
            StatementResult result = session.run(query);

            list = getResults(result);
            session.close();
            //driver.close();

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return list;
    }

    public List<Person> getAllPersonsDepthTwo(String person) {
        /*- endorsements of depth two.
            MATCH ({name:"Sol Linkert"})-[:ENDORSES]->()-[:ENDORSES]->(other_other)
            RETURN other_other*/
        List<Person> list = new ArrayList();

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->(other_other) RETURN other_other.name as name, other_other.job as job, other_other.birthday as birthday;";
            Session session = driver.session();

            // Run a query matching all nodes
            StatementResult result = session.run(query);

            list = getResults(result);
            session.close();
            //driver.close();

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return list;
    }


    public List<Person> getAllPersonsDepthThree(String person) {
        /*- endorsements of depth three.
        MATCH ({name:"Sol Linkert"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other_other_other)
        RETURN other_other_other*/
        List<Person> list = new ArrayList();

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
            Session session = driver.session();

            // Run a query matching all nodes
            StatementResult result = session.run(query);

            list = getResults(result);
            session.close();
            //driver.close();

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return list;

    }

    public List<Person> getAllPersonsDepthFour(String person) {
        /*- endorsements of depth four.
        MATCH ({name:"Sol Linkert"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other_other_other_other)
        RETURN other_other_other_other*/
        //String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
        List<Person> list = new ArrayList();

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            //String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
            String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";

            Session session = driver.session();

            // Run a query matching all nodes
            StatementResult result = session.run(query);

            list = getResults(result);
            session.close();
            //driver.close();

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return list;
    }


    public List<Person> getAllPersonsDepthFive(String person) {
        /* - endorsements of depth five.
        MATCH ({name:"Sol Linkert"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other_other_other_other_other)
        RETURN other_other_other_other_other*/
        //String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";
        List<Person> list = new ArrayList();

        try {
            Driver driver = dbConnectorNeo4J.getDriver();
            String query = "MATCH ({name:\""+person+"\"})-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->()-[:ENDORSES]->(other) RETURN other.name as name, other.job as job, other.birthday as birthday;";

            Session session = driver.session();

            // Run a query matching all nodes
            StatementResult result = session.run(query);

            list = getResults(result);
            session.close();
            //driver.close();

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return list;
    }

    public String getName() {
        return this.name;
    }

    private List<Person> getResults(StatementResult result) {

        List<Person> list = new ArrayList();
        while (result.hasNext()) {
            Record record = result.next();
            Person p = new Person();
            // p.setId(record.get("id").asString());
            p.setJob(record.get("job").asString());
            p.setBirthday(record.get("birthday").asString());
            p.setName(record.get("name").asString());
            list.add(p);

        }
        return list;
    }

    public void close() {
        Driver driver = dbConnectorNeo4J.getDriver();
        driver.close();

    }


}