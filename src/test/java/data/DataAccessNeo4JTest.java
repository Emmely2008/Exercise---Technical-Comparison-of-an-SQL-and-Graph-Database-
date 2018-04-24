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
        List<Person> list = da.getAllPersonsDepthOne(2);
        assertThat(list.size(), equalTo(8));
    }
   /* @Test
    void getAllPersonsDepthTwo() {
    }

    @Test
    void getAllPersonsDepthThree() {
    }

    @Test
    void getAllPersonsDepthFour() {
    }

    @Test
    void getAllPersonsDepthFive() {
    }*/
}