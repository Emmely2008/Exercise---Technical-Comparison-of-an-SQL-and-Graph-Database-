package data;

import model.Person;

import java.util.List;

public interface DataAccessor {
    public List<Person> getAllPersonsDepthOne(int node);
    public List<Person> getAllPersonsDepthTwo(int node);
    public List<Person> getAllPersonsDepthThree(int node);
    public List<Person> getAllPersonsDepthFour(int node);
    public List<Person> getAllPersonsDepthFive(int node);
    public String getName();
}
