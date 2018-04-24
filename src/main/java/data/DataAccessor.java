package data;

import model.Person;

import java.util.List;

public interface DataAccessor {
    public List<Person> getAllPersonsDepthOne(String node);
    public List<Person> getAllPersonsDepthTwo(String node);
    public List<Person> getAllPersonsDepthThree(String node);
    public List<Person> getAllPersonsDepthFour(String node);
    public List<Person> getAllPersonsDepthFive(String node);
    public String getName();
}
