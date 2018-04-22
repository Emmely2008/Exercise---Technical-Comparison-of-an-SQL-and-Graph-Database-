package logic;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


class MeasurementDataTest {

    @Test
    void addData() {
        MeasurementData msd = new MeasurementData();
        msd.addData(10.0);
        assertThat(msd.getAverage(), equalTo(10.0));
        msd.addData(20.0);
        assertThat(msd.getAverage(), equalTo(15.0));
        assertThat(msd.getData().size(), equalTo(2));



    }

    @Test
    void getAverage() {
        MeasurementData msd = new MeasurementData();
        msd.addData(10.0);
        assertThat(msd.getAverage(), equalTo(10.0));
        msd.addData(20.0);
        assertThat(msd.getAverage(), equalTo(15.0));
        msd.addData(30.0);
        assertThat(msd.getAverage(), equalTo(20.0));
        msd.addData(0);
        assertThat(msd.getAverage(), equalTo(15.0));
    }

    @Test
    void getMedian() {
        MeasurementData msd = new MeasurementData();
        msd.addData(10.0);
        assertThat(msd.getMedian(), equalTo(10.0));
        msd.addData(20.0);
        assertThat(msd.getMedian(), equalTo(15.0));
        msd.addData(20.0);
        assertThat(msd.getMedian(), equalTo(20.0));
    }
}