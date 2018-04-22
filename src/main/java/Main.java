import data.*;
import junit.helper.Stopwatch;
import logic.Benchmark;

import java.util.HashMap;

public class Main {


    public static void main(String[] args){
        int neo = new DataAccessNeo4J(new DBConnectorNeo4J()).getAllPersonsDepthOne("Sol Linkert").size();
        int pos = 0;
        try {
            pos = new DataAccessPostGreSQL(new DBConnectorPostGres()).getAllPersonsDepthOne("Sol Linkert").size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(neo);
        System.out.println(pos);

        String[] methodsToTest = {"getAllPersonsDepthOne","getAllPersonsDepthTwo","getAllPersonsDepthThree","getAllPersonsDepthFour","getAllPersonsDepthFive"};
        //todo 20 people here - random
        String[] twentyRandomNodes = {
                "Dino Kalt","Shirl Wilcock","Dulcie Miyares","Gianna Alan","Julienne Confair",
                "Jackie Murano","Twanna Marze", "Shanti Hannagan","Margarete Balbi","Owen Quinlin",
                "Bo Woyahn","Andrew Zahradnik", "Annemarie Bartgis","Antony Ermert","Fred Grueninger",
                "Elly Glosser","Elenor Heeralall", "Elfrieda Witherington","Elenore Stableford","Eduardo Sprau"};
        //Stubbing
        Benchmark bmStub = new Benchmark(new DataAccessStub(), new Stopwatch());
        HashMap resultsStub = bmStub .getBenchmarkResults(twentyRandomNodes,methodsToTest);
        //Neo4J
        DataAccessNeo4J daoneo4J = new DataAccessNeo4J(new DBConnectorNeo4J());
        Benchmark bmNeo4J = new Benchmark(daoneo4J, new Stopwatch());
        HashMap resultsNeo4J = bmNeo4J.getBenchmarkResults(twentyRandomNodes,methodsToTest);

        //Neo4J
        Benchmark bmPostGres = null;
        try {
            bmPostGres = new Benchmark(new DataAccessPostGreSQL(new DBConnectorPostGres()), new Stopwatch());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap resultsPostGres = bmPostGres.getBenchmarkResults(twentyRandomNodes,methodsToTest);


        HashMap[] hm = { resultsNeo4J, resultsPostGres};
        bmStub .printHashMapsData(hm, methodsToTest);
        daoneo4J.close();
    }



}
