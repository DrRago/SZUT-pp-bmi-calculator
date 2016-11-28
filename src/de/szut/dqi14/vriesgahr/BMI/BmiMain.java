package de.szut.dqi14.vriesgahr.BMI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
public class BmiMain extends Application {
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
        who = getMap("who.bmi", new String[] {"UNDERWEIGHT", "NORMAL", "OVERWEIGHT", "OBESE", "SEVERELY_OBESE", "VERY_SEVERELY_OBESE"});

        nrc = getMap("nrc.bmi", new String[] {"1", "0", "-1", "-2", "-3", "-4"});

        dgeMale = getDGE("male");

        dgeFemale = getDGE("female");

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final FXMLLoader loader = new FXMLLoader();

        Parent root = loader.load(getClass().getResource("Bmi.fxml").openStream());

        Controller controller = loader.getController();
        controller.init();

        primaryStage.getIcons().add(new Image("file:icon.png"));

        primaryStage.setTitle("Bmi calculator!");
        primaryStage.setScene(new Scene(root));

        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        primaryStage.show();
    }

    private static Map<String,double[]> getDGE(String gender) throws IOException {
        Map<String, double[]> dgeMale = new HashMap<>();

        try (FileInputStream is = new FileInputStream("dge.bmi") ) {
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

    private static Map<String, double[]> getMap(String jsonFile, String[] indexes) throws IOException {
        Map<String, double[]> map = new HashMap<>();

        try (FileInputStream is = new FileInputStream(jsonFile) ) {
            try (JsonReader rdr = Json.createReader(is)) {

                JsonObject obj = rdr.readObject();

                for (String weight: indexes) {

                    JsonArray results = obj.getJsonArray(weight);

                    double[] values = new double[]{results.getJsonNumber(0).doubleValue(), results.getJsonNumber(1).doubleValue()};
                    map.put(weight, values);
                }
            }
        }
        return map;
    }
}