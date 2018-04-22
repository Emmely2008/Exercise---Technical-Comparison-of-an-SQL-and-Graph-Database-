package data;

import model.Person;

import java.util.ArrayList;
import java.util.List;

public class DataAccessStub implements DataAccessor  {
    private String name;
    public DataAccessStub(){
        this.name = "Stub";
    }
    public List<Person> getAllPersonsDepthOne(String nodeAsName) {

        return simulate();
    }

    public List<Person> getAllPersonsDepthTwo(String nodeAsName) {
        return simulate();
    }

    public List<Person> getAllPersonsDepthThree(String nodeAsName) {
        return simulate();
    }

    public List<Person> getAllPersonsDepthFour(String nodeAsName) {
        return simulate();
    }

    public List<Person> getAllPersonsDepthFive(String nodeAsName) {
        return simulate();
    }

    public String getName() {
        return name;
    }

    private List<Person> simulate(){

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Person> list = new ArrayList();
        int limit = 6;
        for (int i = 0; i < limit; i++) {
            Person p = new Person();
            // p.setId(record.get("id").asString());
            p.setJob("job");
            p.setBirthday("birthday");
            p.setName("name");
            list.add(p);
        }

        return list;
    }
}
