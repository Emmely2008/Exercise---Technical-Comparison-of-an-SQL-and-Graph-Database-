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
        List<Person> list = da.getAllPersonsDepthOne("Jeanie Mountcastle");
        assertThat(list.size(), equalTo(39));

    }

    @Test
    void getAllPersonsDepthTwo() throws Exception{
        DataAccessPostGreSQL da = new DataAccessPostGreSQL(new DBConnectorPostGres());
        List<Person> list = da.getAllPersonsDepthTwo("Jeanie Mountcastle");
        assertThat(list.size(), equalTo(874));
    }

    @Test
    void getAllPersonsDepthThree() throws Exception{
        DataAccessPostGreSQL da = new DataAccessPostGreSQL(new DBConnectorPostGres());
        List<Person> list = da.getAllPersonsDepthThree("Jeanie Mountcastle");
        assertThat(list.size(), equalTo(18505));
    }

    @Test
    void getAllPersonsDepthFour() throws Exception{
        DataAccessPostGreSQL da = new DataAccessPostGreSQL(new DBConnectorPostGres());
        List<Person> list = da.getAllPersonsDepthFour("Jeanie Mountcastle");
        assertThat(list.size(), equalTo(278991));
    }

    @Test
    void getAllPersonsDepthFive() throws Exception{
        DataAccessPostGreSQL da = new DataAccessPostGreSQL(new DBConnectorPostGres());
        List<Person> list = da.getAllPersonsDepthFive("Jeanie Mountcastle");
        assertThat(list.size(), equalTo(499997
        ));
    }
}