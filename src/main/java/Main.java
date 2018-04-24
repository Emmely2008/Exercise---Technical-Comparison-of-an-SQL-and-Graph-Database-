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
/*        int neo = new DataAccessNeo4J(new DBConnectorNeo4J()).getAllPersonsDepthOne("Jeanie Mountcastle").size();
        int pos = 0;
        try {
            pos = new DataAccessPostGreSQL(new DBConnectorPostGres()).getAllPersonsDepthOne("Jeanie Mountcastle").size();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //System.out.println(neo);
        // System.out.println(pos);

        String[] methodsToTest = {"getAllPersonsDepthOne", "getAllPersonsDepthTwo", "getAllPersonsDepthThree", "getAllPersonsDepthFour", "getAllPersonsDepthFive"};
        //String[] methodsToTest = {"getAllPersonsDepthOne","getAllPersonsDepthTwo"};
        //String[] methodsToTest = {"getAllPersonsDepthOne"};
        //todo 20 people here - random
        int limit = 20;
        int[] twentyRandomNodes = new int[limit];
        Random generator = new Random();
        for (int i = 0; i < limit; i++) {

            int index = generator.nextInt(5000000);
            twentyRandomNodes[i] = index;

        }
        //Stubbing
/*        Benchmark bmStub = new Benchmark(new DataAccessStub(), new Stopwatch());
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

        //resultsPostGres
        //HashMap[] hm = { resultsNeo4J };
        HashMap[] hm = {resultsPostGres, resultsNeo4J };
        // HashMap[] hm = { resultsNeo4J};
        bmPostGres.printHashMapsData(hm, methodsToTest);
        bmPostGres.printHashMapsDataIndividualTimes(hm, methodsToTest);

    }


}
