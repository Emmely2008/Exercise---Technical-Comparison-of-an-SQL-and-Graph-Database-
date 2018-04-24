import data.*;
import junit.helper.Stopwatch;
import logic.BenchmarkCount;

import java.util.HashMap;

public class Main {


    public static void main(String[] args){
        int neo = new DataAccessNeo4J(new DBConnectorNeo4J()).getAllPersonsDepthOne("Jeanie Mountcastle").size();
        int pos = 0;
        try {
            pos = new DataAccessPostGreSQL(new DBConnectorPostGres()).getAllPersonsDepthOne("Jeanie Mountcastle").size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(neo);
        System.out.println(pos);

        String[] methodsToTest = {"getAllPersonsDepthOne","getAllPersonsDepthTwo","getAllPersonsDepthThree", "getAllPersonsDepthFour"};
        //String[] methodsToTest = {"getAllPersonsDepthOne","getAllPersonsDepthTwo"};
        //String[] methodsToTest = {"getAllPersonsDepthOne"};
        //todo 20 people here - random

        String[] twentyRandomNodes = {
                "Jeanie Mountcastle","Kindra Ryser","Royce Fadely","Nevada Albarran","Gayla Brase",
                "Wilhelmina Beltram","Ena Walin", "Antonette Barthen","Blanche Kuchenbecker","Bibi Sieren",
                "Karri Goertzen","Doretta Freytas", "Mayra Vitantonio","Casey Phetphongsy","Coletta Mateus",
                "Loriann Hnot","Denyse Aukes", "Chong Stolte","Corene Eska","Shirly Orpin"};
        //Stubbing
/*        Benchmark bmStub = new Benchmark(new DataAccessStub(), new Stopwatch());
        HashMap resultsStub = bmStub .getBenchmarkResults(twentyRandomNodes,methodsToTest);*/
        //Neo4J
        DataAccessNeo4JCount daoneo4J = new DataAccessNeo4JCount(new DBConnectorNeo4J());
        BenchmarkCount bmNeo4J = new BenchmarkCount(daoneo4J, new Stopwatch());
        HashMap resultsNeo4J = bmNeo4J.getBenchmarkResults(twentyRandomNodes,methodsToTest);

        //PostGres
        BenchmarkCount bmPostGres = null;
        try {
            bmPostGres = new BenchmarkCount(new DataAccessPostGreSQLCount(new DBConnectorPostGres()), new Stopwatch());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap resultsPostGres = bmPostGres.getBenchmarkResults(twentyRandomNodes,methodsToTest);


        HashMap[] hm = { resultsNeo4J, resultsPostGres};
       // HashMap[] hm = { resultsPostGres};
        // HashMap[] hm = { resultsNeo4J};
        bmPostGres.printHashMapsData(hm, methodsToTest);
        //daoneo4J.close();
    }



}
