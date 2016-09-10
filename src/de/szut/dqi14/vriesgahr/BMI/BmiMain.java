package de.szut.dqi14.vriesgahr.BMI;

import javax.json.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Bmi main.
 *
 * @author Leonhard Gahr
 * @author Pascal de Vvries
 */
public class BmiMain {
    public static Map<String, double[]> who;
    public static Map<String, double[]> nrc;
    public static Map<String, double[]> dgeMale;
    public static Map<String, double[]> dgeFemale;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        BmiCalcImpl bmi = new BmiCalcImpl();
        bmi.setSex(Sex.FEMALE);
        bmi.setSize(1.82);
        bmi.setAge(38);
        bmi.setWeight(63);

        who = getMap("who.bmi", new String[] {"UNDERWEIGHT", "NORMAL", "OVERWEIGHT", "OBESE", "SEVERELY_OBESE", "VERY_SEVERELY_OBESE"});

        nrc = getMap("nrc.bmi", new String[] {"1", "0", "-1", "-2", "-3", "-4"});

        dgeMale = getDGE("dge.bmi", "male");

        dgeFemale = getDGE("dge.bmi", "female");

        System.out.println(bmi.getCategory());
    }

    private static Map<String,double[]> getDGE(String jsonFile, String gender) throws IOException {
        Map<String, double[]> dgeMale = new HashMap<>();

        try (FileInputStream is = new FileInputStream(jsonFile) ) {
            try (JsonReader rdr = Json.createReader(is)) {

                JsonObject obj = rdr.readObject();
                String[] weights = new String[] {"UNDERWEIGHT", "NORMAL", "OVERWEIGHT", "OBESE", "SEVERELY_OBESE", "VERY_SEVERELY_OBESE"};

                JsonArray maleData = obj.getJsonArray(gender);

                for (JsonObject res: maleData.getValuesAs(JsonObject.class)) {
                    for (String weight: weights) {

                        JsonArray results = res.getJsonArray(weight);

                        double[] values = new double[]{results.getJsonNumber(0).doubleValue(), results.getJsonNumber(1).doubleValue()};
                        dgeMale.put(weight, values);
                    }
                }
            }
        }
        return dgeMale;
    }

    private static Map<String, double[]> getMap(String jsonFile, String[] weights) throws IOException {
        Map<String, double[]> map = new HashMap<>();

        try (FileInputStream is = new FileInputStream(jsonFile) ) {
            try (JsonReader rdr = Json.createReader(is)) {

                JsonObject obj = rdr.readObject();

                for (String weight: weights) {

                    JsonArray results = obj.getJsonArray(weight);

                    double[] values = new double[]{results.getJsonNumber(0).doubleValue(), results.getJsonNumber(1).doubleValue()};
                    map.put(weight, values);
                }
            }
        }
        return map;
    }
}