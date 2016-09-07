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
    public static Map<String, int[]> who;
    public static Map<String, Integer> nrc;
    public static Map<String, int[]> dgeMale;
    public static Map<String, int[]> dgeFemale;

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
        bmi.setAge(28);
        bmi.setWeight(80.0);

        who = getWHO("who.bmi");

        nrc = getNRC("nrc.bmi");

        dgeMale = getDGE("dge.bmi", "male");

        dgeFemale = getDGE("dge.bmi", "female");
    }

    private static Map<String,int[]> getDGE(String jsonFile, String gender) throws IOException {
        Map<String, int[]> dgeMale = new HashMap<>();

        try (FileInputStream is = new FileInputStream(jsonFile) ) {
            try (JsonReader rdr = Json.createReader(is)) {

                JsonObject obj = rdr.readObject();
                String[] weights = new String[] {"untergewicht", "normalgewicht", "übergewicht", "starkesÜbergewicht", "adipositasII", "adipositasIII"};

                JsonArray maleData = obj.getJsonArray(gender);

                for (JsonObject res: maleData.getValuesAs(JsonObject.class)) {
                    for (String weight: weights) {

                        JsonArray results = res.getJsonArray(weight);

                        int[] values = new int[]{results.getJsonNumber(0).intValue(), results.getJsonNumber(1).intValue()};
                        dgeMale.put(weight, values);
                    }
                }
            }
        }
        return dgeMale;
    }

    private static Map<String,Integer> getNRC(String jsonFile) throws IOException {
        Map<String, Integer> nrc = new HashMap<>();

        try (FileInputStream is = new FileInputStream(jsonFile) ) {
            try (JsonReader rdr = Json.createReader(is)) {

                JsonObject obj = rdr.readObject();
                String[] weights = new String[] {"19", "25", "35", "45", "55", "65"};

                for (String weight: weights) {

                    JsonNumber results = obj.getJsonNumber(weight);

                    int value = results.intValue();
                    nrc.put(weight, value);
                }
            }
        }
        return nrc;
    }

    private static Map<String, int[]> getWHO(String jsonFile) throws IOException {
        Map<String, int[]> who = new HashMap<>();

        try (FileInputStream is = new FileInputStream(jsonFile) ) {
            try (JsonReader rdr = Json.createReader(is)) {

                JsonObject obj = rdr.readObject();
                String[] weights = new String[] {"untergewicht", "normalgewicht", "übergewicht", "starkesÜbergewicht", "adipositasII", "adipositasIII"};

                for (String weight: weights) {

                    JsonArray results = obj.getJsonArray(weight);

                    int[] values = new int[]{results.getJsonNumber(0).intValue(), results.getJsonNumber(1).intValue()};
                    who.put(weight, values);
                }
            }
        }
        return who;
    }
}