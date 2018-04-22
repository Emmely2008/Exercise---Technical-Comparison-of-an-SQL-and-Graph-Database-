package data;

import model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

class DataAccessPostGreSQLTest {

    @Test
    void getAllPersonsDepthOne() throws Exception{
        DataAccessPostGreSQL da = new DataAccessPostGreSQL(new DBConnectorPostGres());
        List<Person> list = da.getAllPersonsDepthOne("Sol Linkert");
        assertThat(list.size(), equalTo(8));

    }

    /*@Test
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