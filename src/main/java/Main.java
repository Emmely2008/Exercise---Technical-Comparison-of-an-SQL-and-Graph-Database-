import data.DBConnectorNeo4J;
import data.DBConnectorPostGres;
import data.DataAccessNeo4JCount;
import data.DataAccessPostGreSQLCount;
import junit.helper.Stopwatch;
import logic.BenchmarkCount;

import java.util.HashMap;
import java.util.Random;

public class Main {


    public static void main(String[] args) {


        String[] methodsToTest = {"getAllPersonsDepthOne", "getAllPersonsDepthTwo", "getAllPersonsDepthThree", "getAllPersonsDepthFour", "getAllPersonsDepthFive"};

        //20 random people
        int limit = 20;
        int[] twentyRandomNodes = new int[limit];
        Random generator = new Random();
        for (int i = 0; i < limit; i++) {

            int index = generator.nextInt(5000000);
            twentyRandomNodes[i] = index;

        }
        //Stubbing
        /*Benchmark bmStub = new Benchmark(new DataAccessStub(), new Stopwatch());
        HashMap resultsStub = bmStub .getBenchmarkResults(twentyRandomNodes,methodsToTest);*/

        //Neo4J
        DataAccessNeo4JCount daoneo4J = new DataAccessNeo4JCount(new DBConnectorNeo4J());
        BenchmarkCount bmNeo4J = new BenchmarkCount(daoneo4J, new Stopwatch());
        HashMap resultsNeo4J = bmNeo4J.getBenchmarkResults(twentyRandomNodes, methodsToTest);
        daoneo4J.close();

        //PostGres
        BenchmarkCount bmPostGres = null;
        try {
            bmPostGres = new BenchmarkCount(new DataAccessPostGreSQLCount(new DBConnectorPostGres()), new Stopwatch());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap resultsPostGres = bmPostGres.getBenchmarkResults(twentyRandomNodes, methodsToTest);

        HashMap[] hashMaps = {resultsPostGres, resultsNeo4J };

        // print result
        bmPostGres.printHashMapsData(hashMaps, methodsToTest);
        bmPostGres.printHashMapsDataIndividualTimes(hashMaps, methodsToTest);

    }


}
