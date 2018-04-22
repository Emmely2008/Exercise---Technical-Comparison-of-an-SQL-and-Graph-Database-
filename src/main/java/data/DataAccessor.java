package data;

import model.Person;

import java.util.List;

public interface DataAccessor {
    public List<Person> getAllPersonsDepthOne(String nodeAsName);
    public List<Person> getAllPersonsDepthTwo(String nodeAsName);
    public List<Person> getAllPersonsDepthThree(String nodeAsName);
    public List<Person> getAllPersonsDepthFour(String nodeAsName);
    public List<Person> getAllPersonsDepthFive(String nodeAsName);
    public String getName();
}
