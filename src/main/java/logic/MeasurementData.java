package logic;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;



public class MeasurementData {
    private ArrayList<Double> data;
    private String type;

    public MeasurementData() {
        this.data = new ArrayList<Double>();
    }

    public void addData(double value) {
        data.add(value);
    }

    public double getAverage() {

        if (data.size() == 0) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < data.size(); i++) {
            sum += data.get(i);
        }

        return round(sum / data.size(),2);
    }

    public double getMedian() {

        Collections.sort(data);

        double median;
        if (data.size() % 2 == 0)
            median = ((double) data.get(data.size() / 2) + (double) data.get(data.size() / 2 - 1)) / 2;
        else
            median = (double) data.get(data.size() / 2);
       // return median;
        return round(median, 2);
    }
    public  String getDataListAsString() {

        String s = "{";
        for (int i = 0; i < data.size() ; i++) {
            s += "" + round(data.get(i), 2);
            if(i < data.size()-1)
            s += ",";
        }
        s += "}";

        return s;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Double> getData() {
        return data;
    }

    public void setData(ArrayList<Double> data) {
        this.data = data;
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

