package data;

import model.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataAccessPostGreSQL implements DataAccessor {
    private static final boolean DEBUG = true;
    private String name;
    private DBConnectorPostGres dbConnectorPostGres;

    public DataAccessPostGreSQL(DBConnectorPostGres dbConnectorPostGres) {
        this.name = "PostGreSQL";
        this.dbConnectorPostGres = dbConnectorPostGres;
    }

    public List<Person> getAllPersonsDepthOne(String node) {
        List<Person> list = new ArrayList();

        try {
            Connection connection = this.dbConnectorPostGres.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM chinook.person a JOIN  (select * FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name='" + node + "') b ON a.id=b.target_node_id;";
            //String query = "SELECT count(*) FROM chinook.person a JOIN  (select * FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name='" + node + "') b ON a.id=b.target_node_id;";
            ResultSet res = stmt.executeQuery(query);
            list = this.getResults(res);
        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return list;
    }

    public List<Person> getAllPersonsDepthTwo(String node) {
        /*
        Second
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"'));
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert'));        List<Person> list = new ArrayList();
        */
        List<Person> list = new ArrayList();
        try {
            Connection connection = this.dbConnectorPostGres.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT name, job, birthday FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '" + node + "'));";
            ResultSet res = stmt.executeQuery(query);

           list = this.getResults(res);

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return list;
    }

    public List<Person> getAllPersonsDepthThree(String node) {
        /*Third
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"')));
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert')));
        */

        List<Person> list = new ArrayList();
        try {
            Connection connection = this.dbConnectorPostGres.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+node+"')));";
            ResultSet res = stmt.executeQuery(query);

            //list = this.getResults(res);

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return list;
    }

    public List<Person> getAllPersonsDepthFour(String node) {
        /*Fourt
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"'))));
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert'))));
        */

        List<Person> list = new ArrayList();
        try {
            Connection connection = this.dbConnectorPostGres.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+node+"'))));";
            ResultSet res = stmt.executeQuery(query);

            //list = this.getResults(res);

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return list;
    }

    public List<Person> getAllPersonsDepthFive(String node) {
        /* Fifth
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"')))));
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert')))));
        */

        List<Person> list = new ArrayList();
        try {
            Connection connection = this.dbConnectorPostGres.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+node+"')))));";
            ResultSet res = stmt.executeQuery(query);

            //list = this.getResults(res);

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return list;
    }
    public String getAPersonName(int id) {
        String s = "";

        try {
            Connection connection = this.dbConnectorPostGres.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM person WHERE id ='"+id+"' ";
            ResultSet res = stmt.executeQuery(query);

            while (res.next()) {

                s = res.getString("name");

            }
        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return s;
    }
    private List<Person> getResults(ResultSet res) throws SQLException {

        List<Person> list = new ArrayList();
        while (res.next()) {
            Person p = new Person();
            //p.setId(res.getString("id"));
            p.setJob(res.getString("job"));
            p.setBirthday(res.getString("birthday"));
            p.setName(res.getString("name"));
            System.out.println(p.getName());
            list.add(p);
        }
        return list;

    }

    public String getName() {
        return this.name;
    }
}
