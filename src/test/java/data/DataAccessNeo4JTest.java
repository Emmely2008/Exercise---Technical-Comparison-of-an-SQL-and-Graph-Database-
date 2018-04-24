package data;

import model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

class DataAccessNeo4JTest {


    @Test
    void getAllPersonsDepthOne() {
        DataAccessNeo4J da = new DataAccessNeo4J(new DBConnectorNeo4J());
        List<Person> list = da.getAllPersonsDepthOne("Jeanie Mountcastle");
        assertThat(list.size(), equalTo(39));
    }
    @Test
    void getAllPersonsDepthTwo() {
        DataAccessNeo4J da = new DataAccessNeo4J(new DBConnectorNeo4J());
        List<Person> list = da.getAllPersonsDepthTwo("Jeanie Mountcastle");
        assertThat(list.size(), equalTo(913));
    }

    @Test
    void getAllPersonsDepthThree() {
        DataAccessNeo4J da = new DataAccessNeo4J(new DBConnectorNeo4J());
        List<Person> list = da.getAllPersonsDepthThree("Jeanie Mountcastle");
        assertThat(list.size(), equalTo(19374 ));
    }

    @Test
    void getAllPersonsDepthFour() {
        DataAccessNeo4J da = new DataAccessNeo4J(new DBConnectorNeo4J());
        List<Person> list = da.getAllPersonsDepthFour("Jeanie Mountcastle");
        assertThat(list.size(), equalTo(287264  ));
    }

    @Test
    void getAllPersonsDepthFive() {
        DataAccessNeo4J da = new DataAccessNeo4J(new DBConnectorNeo4J());
        List<Person> list = da.getAllPersonsDepthFive("Jeanie Mountcastle");
        assertThat(list.size(), equalTo(499998));
    }
}