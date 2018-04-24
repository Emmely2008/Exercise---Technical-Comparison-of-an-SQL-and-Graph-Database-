package logic;

import data.DataAccessStub;
import junit.helper.Stopwatch;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BenchmarkTest {

    @Test
    void getBenchmarkResultsLengthOfDataMeasurements() {
        Stopwatch timeMock = mock(Stopwatch.class);

        Benchmark bm = new Benchmark(new DataAccessStub(), timeMock);
        when(timeMock.elapsedTime()).thenReturn(2.0);
        String[] methodsToTest = {"getAllPersonsDepthOne","getAllPersonsDepthTwo","getAllPersonsDepthFour","getAllPersonsDepthFive"};
        String[] twentyRandomNodes = {"Jeanie Mountcastle"};

        HashMap resultsStub = bm.getBenchmarkResults(twentyRandomNodes,methodsToTest);

        for (int i = 0; i < methodsToTest.length; i++) {
            MeasurementData msd = (MeasurementData) resultsStub.get(methodsToTest[i]);
            msd.getData();
            assertThat(msd.getData().size(), equalTo(twentyRandomNodes.length));

        }
        HashMap[] hm = {resultsStub};
        bm.printHashMapsData(hm, methodsToTest);

    }
    @Test
    void getBenchmarkResultsMedian() {
        Stopwatch timeMock = mock(Stopwatch.class);

        Benchmark bm = new Benchmark(new DataAccessStub(), timeMock);
        when(timeMock.elapsedTime()).thenReturn(2.0);
        String[] methodsToTest = {"getAllPersonsDepthOne","getAllPersonsDepthTwo","getAllPersonsDepthFour","getAllPersonsDepthFive"};
        String[] twentyRandomNodes = {"Jeanie Mountcastle"};

        HashMap resultsStub = bm.getBenchmarkResults(twentyRandomNodes,methodsToTest);

        for (int i = 0; i < methodsToTest.length; i++) {
            MeasurementData msd = (MeasurementData) resultsStub.get(methodsToTest[i]);
            msd.getData();
            assertThat(msd.getAverage(), equalTo(2.0));

        }
        HashMap[] hm = {resultsStub};
        bm.printHashMapsData(hm, methodsToTest);

    }
    @Test
    void getBenchmarkResultsAverage() {
        Stopwatch timeMock = mock(Stopwatch.class);

        Benchmark bm = new Benchmark(new DataAccessStub(), timeMock);
        when(timeMock.elapsedTime()).thenReturn(2.0);
        String[] methodsToTest = {"getAllPersonsDepthOne","getAllPersonsDepthTwo","getAllPersonsDepthFour","getAllPersonsDepthFive"};
        String[] twentyRandomNodes = {"Jeanie Mountcastle"};

        HashMap resultsStub = bm.getBenchmarkResults(twentyRandomNodes,methodsToTest);

        for (int i = 0; i < methodsToTest.length; i++) {
            MeasurementData msd = (MeasurementData) resultsStub.get(methodsToTest[i]);
            msd.getData();
            assertThat(msd.getMedian(), equalTo(2.0));

        }
        HashMap[] hm = {resultsStub};
        bm.printHashMapsData(hm, methodsToTest);

    }
}