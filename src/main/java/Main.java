import data.DBConnectorNeo4J;
import data.DBConnectorPostGres;
import data.DataAccessNeo4J;
import data.DataAccessPostGreSQL;
import junit.helper.Stopwatch;
import logic.Benchmark;

import java.util.HashMap;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //20 random people
        int limit = 1;
        String[] twentyRandomNodes = new String[limit];
        //String[] methodsToTest = {"getAllPersonsDepthOne", "getAllPersonsDepthTwo", "getAllPersonsDepthThree", "getAllPersonsDepthFour", "getAllPersonsDepthFive"};
        String[] methodsToTest = {"getAllPersonsDepthOne"};

        //PostGres
        Benchmark bmPostGres = null;

        try {
            DataAccessPostGreSQL dapSQL = new DataAccessPostGreSQL(new DBConnectorPostGres());
            bmPostGres = new Benchmark(dapSQL, new Stopwatch());


            Random generator = new Random();
            for (int i = 0; i < limit; i++) {

                int index = generator.nextInt(5000000);
                twentyRandomNodes[i] = dapSQL.getAPersonName(index);
                //getAPersonName

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        //Stubbing
        /*Benchmark bmStub = new Benchmark(new DataAccessStub(), new Stopwatch());
        HashMap resultsStub = bmStub .getBenchmarkResults(twentyRandomNodes,methodsToTest);*/

        //Neo4J
        DataAccessNeo4J daoneo4J = new DataAccessNeo4J(new DBConnectorNeo4J());
        Benchmark bmNeo4J = new Benchmark(daoneo4J, new Stopwatch());
        HashMap resultsNeo4J = bmNeo4J.getBenchmarkResults(twentyRandomNodes, methodsToTest);
        daoneo4J.close();


        HashMap resultsPostGres = bmPostGres.getBenchmarkResults(twentyRandomNodes, methodsToTest);

        HashMap[] hashMaps = {resultsPostGres, resultsNeo4J};

        // print result
        bmPostGres.printHashMapsData(hashMaps, methodsToTest);
        bmPostGres.printHashMapsDataIndividualTimes(hashMaps, methodsToTest);


    }
}
