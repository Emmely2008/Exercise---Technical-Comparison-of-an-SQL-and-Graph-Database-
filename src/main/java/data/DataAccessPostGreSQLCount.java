package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccessPostGreSQLCount implements DataAccessorCount {
    private static final boolean DEBUG = true;
    private String name;
    private DBConnectorPostGres dbConnectorPostGres;

    public DataAccessPostGreSQLCount(DBConnectorPostGres dbConnectorPostGres) {
        this.name = "PostGreSQLCount";
        this.dbConnectorPostGres = dbConnectorPostGres;
    }

    public int getAllPersonsDepthOne(int node) {
        int count = 0;

        try {
            Connection connection = this.dbConnectorPostGres.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT count(*) FROM chinook.person a JOIN  (select * FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name='" + node + "') b ON a.id=b.target_node_id;";
            ResultSet res = stmt.executeQuery(query);
            count = this.getResults(res);
        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return count;
    }

    public int getAllPersonsDepthTwo(int node) {
        /*
        Second
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"'));
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert'));        List<Person> list = new ArrayList();
        */
        int count = 0;
        try {
            Connection connection = this.dbConnectorPostGres.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT count(*) FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '" + node + "'));";
            ResultSet res = stmt.executeQuery(query);

            count = this.getResults(res);

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return count;
    }

    public int getAllPersonsDepthThree(int node) {
        /*Third
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"')));
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert')));
        */

        int count = 0;
        try {
            Connection connection = this.dbConnectorPostGres.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT count(*) FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '" + node + "')));";
            ResultSet res = stmt.executeQuery(query);

            count = this.getResults(res);

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return count;
    }

    public int getAllPersonsDepthFour(int node) {
        /*Fourt
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"'))));
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert'))));
        */

        int count = 0;
        try {
            Connection connection = this.dbConnectorPostGres.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT count(*) FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '" + node + "'))));";
            ResultSet res = stmt.executeQuery(query);

            count = this.getResults(res);

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return count;
    }

    public int getAllPersonsDepthFive(int node) {
        /* Fifth
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '"+person+"')))));
        %sql SELECT * FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = 'Sol Linkert')))));
        */

        int count = 0;
        try {
            Connection connection = this.dbConnectorPostGres.getConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT count(*) FROM chinook.person WHERE id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement WHERE source_node_id IN (SELECT target_node_id FROM chinook.endorsement e JOIN chinook.person p ON e.source_node_id = p.id WHERE p.name = '" + node + "')))));";
            ResultSet res = stmt.executeQuery(query);

            count = this.getResults(res);

        } catch (Exception e) {
            if (DEBUG) e.printStackTrace();
        }
        return count;
    }

    private int getResults(ResultSet res) throws SQLException {

        int count = 0;
        while (res.next()) {

            //p.setId(res.getString("id"));
            count = res.getInt("count");

        }
        return count;
    }

    public String getName() {
        return this.name;
    }
}
