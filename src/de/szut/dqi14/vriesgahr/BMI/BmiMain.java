package de.szut.dqi14.vriesgahr.BMI;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
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
    static Map<String, double[]> who;
    static Map<String, double[]> nrc;
    static Map<String, double[]> dgeMale;
    static Map<String, double[]> dgeFemale;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        /* get who table as map from a json file with given indexes */
        who = getMapFromJson("who.bmi", new String[] {"UNDERWEIGHT", "NORMAL", "OVERWEIGHT", "OBESE", "SEVERELY_OBESE", "VERY_SEVERELY_OBESE"});

        /* get nrc table as map from a json file with given indexes */
        nrc = getMapFromJson("nrc.bmi", new String[] {"1", "0", "-1", "-2", "-3", "-4"});

        /* get dge table as map from a json file */
        dgeMale = getDGE("dge.bmi", "male", new String[] {"UNDERWEIGHT", "NORMAL", "OVERWEIGHT", "OBESE", "SEVERELY_OBESE", "VERY_SEVERELY_OBESE"});
        dgeFemale = getDGE("dge.bmi", "female", new String[] {"UNDERWEIGHT", "NORMAL", "OVERWEIGHT", "OBESE", "SEVERELY_OBESE", "VERY_SEVERELY_OBESE"});

        /* set parameters for the calculation */
        BmiCalcImpl bmiCalc = new BmiCalcImpl();

        bmiCalc.setAge(55);
        bmiCalc.setSize(1.82);
        bmiCalc.setWeight(0);
        bmiCalc.setSex(Sex.MALE);

        /* print calculations */
        System.out.println("BMI: " + bmiCalc.getBmi());
        System.out.println("Weight Category: " + bmiCalc.getCategory());
        System.out.println("Ideal Weight: " + bmiCalc.getIdealWeight());
    }

    /**
     * get the DGE table from a json file
     *
     * @param gender the gender for the table
     * @param jsonFile the path to the json file
     *
     * @return the table as map
     *
     * @throws IOException the io exception
     */
    private static Map<String,double[]> getDGE(String jsonFile, String gender, String[] indexes) throws IOException {
        Map<String, double[]> dge = new HashMap<>(); // create map for the output

        /* read the json string */
        JsonReader rdr = Json.createReader(new FileInputStream(jsonFile));
        JsonObject obj = rdr.readObject();
        JsonArray data = obj.getJsonArray(gender);

        /* read the jason arrays in the json string and save it into the map */
        for (JsonObject res: data.getValuesAs(JsonObject.class)) {
            for (String weight: indexes) {

                JsonArray results = res.getJsonArray(weight);

                double[] values = new double[]{results.getJsonNumber(0).doubleValue(), results.getJsonNumber(1).doubleValue()};
                dge.put(weight, values);
            }
        }
        return dge;
    }

    /**
     * get a map from a json file
     *
     * @param indexes the indexes of the json file
     * @param jsonFile the path to the json file
     *
     * @return the table as map
     *
     * @throws IOException the io exception
     */
    private static Map<String, double[]> getMapFromJson(String jsonFile, String[] indexes) throws IOException {
        Map<String, double[]> map = new HashMap<>(); // create map for the output

        /* read the data from the json string */
        JsonReader rdr = Json.createReader(new FileInputStream(jsonFile));
        JsonObject obj = rdr.readObject();

        /* read the data from the json object and save them into the map */
        for (String weight: indexes) {
            JsonArray results = obj.getJsonArray(weight);

            double[] values = new double[]{results.getJsonNumber(0).doubleValue(), results.getJsonNumber(1).doubleValue()};
            map.put(weight, values);
        }
        return map;
    }
}