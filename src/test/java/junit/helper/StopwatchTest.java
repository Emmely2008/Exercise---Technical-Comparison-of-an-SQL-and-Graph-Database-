package junit.helper;

import org.junit.jupiter.api.Test;


class StopwatchTest {

    @Test
    void elapsedTime() throws InterruptedException {
        Stopwatch sw = new Stopwatch();
        Thread.sleep(6000);
        double time = sw.elapsedTime();
        assert(time > 5);
    }
    void elapsedTimeTwo() throws InterruptedException {
        Stopwatch sw = new Stopwatch();
        Thread.sleep(6000);
        double time = sw.elapsedTime();
        assert(time < 12);
    }
    void elapsedTimeReset() throws InterruptedException {
        Stopwatch sw = new Stopwatch();
        Thread.sleep(6000);
        sw.resetTime();
        double time = sw.elapsedTime();
        assert(time < 5);
    }
}