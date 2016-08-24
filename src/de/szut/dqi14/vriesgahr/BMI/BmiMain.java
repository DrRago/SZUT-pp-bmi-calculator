package de.szut.dqi14.vriesgahr.BMI;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * @author Leonhard Gahr
 * @author Pascal de Vvries
 */
public class BmiMain {

    public static void main(String[] args) {
        BmiCalcImpl bmi = new BmiCalcImpl();
        bmi.setSex(Sex.FEMALE);
        bmi.setSize(1.82);
        bmi.setAge(28);
        bmi.setWeight(80.0);

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("file1.txt"));

            JSONObject jsonObject = (JSONObject) obj;

            System.out.println((String) jsonObject.get(""));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}