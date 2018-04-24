package Integrationtest;

import data.DBConnectorNeo4J;
import data.DBConnectorPostGres;
import data.DataAccessNeo4J;
import data.DataAccessPostGreSQL;
import junit.helper.Stopwatch;
import logic.Benchmark;
import logic.MeasurementData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;



public class BenchmarkIntegrationTest {


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getBenchmarkResultsNeo4JDataMeasurementsRealTime() {
        Stopwatch time = new Stopwatch();

        Benchmark bm = new Benchmark(new DataAccessNeo4J(new DBConnectorNeo4J()), time);
        String[] methodsToTest = {"getAllPersonsDepthOne"};
        int[] twentyRandomNodes = {453508};

        HashMap resultsNeo4J = bm.getBenchmarkResults(twentyRandomNodes,methodsToTest);

        for (int i = 0; i < methodsToTest.length; i++) {
            MeasurementData msd = (MeasurementData) resultsNeo4J.get(methodsToTest[i]);
            msd.getData();
            assertTrue(msd.getAverage() > 0);
            assertTrue(msd.getMedian() > 0);

        }
        HashMap[] hm = {resultsNeo4J};
        bm.printHashMapsData(hm, methodsToTest);

    }
    @Test
    void getBenchmarkResultsPostGresDataMeasurementsRealTime() throws Exception {
        Stopwatch time = new Stopwatch();

        Benchmark bm = new Benchmark(new DataAccessPostGreSQL(new DBConnectorPostGres()), time);
        String[] methodsToTest = {"getAllPersonsDepthOne"};
        int[] twentyRandomNodes = {453508};

        HashMap resultsPotGres = bm.getBenchmarkResults(twentyRandomNodes,methodsToTest);

        for (int i = 0; i < methodsToTest.length; i++) {
            MeasurementData msd = (MeasurementData) resultsPotGres.get(methodsToTest[i]);
            msd.getData();
            assertTrue(msd.getAverage() > 0);
            assertTrue(msd.getMedian() > 0);

        }
        HashMap[] hm = {resultsPotGres};
        bm.printHashMapsData(hm, methodsToTest);

    }


}
