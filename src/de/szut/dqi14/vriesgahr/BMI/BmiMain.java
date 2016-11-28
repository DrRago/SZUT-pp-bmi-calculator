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
        // get who table as map from a json file with given indexes
        who = getMap("who.bmi", new String[] {"UNDERWEIGHT", "NORMAL", "OVERWEIGHT", "OBESE", "SEVERELY_OBESE", "VERY_SEVERELY_OBESE"});

        // Get nrc table as map from a json file with given indexes
        nrc = getMap("nrc.bmi", new String[] {"1", "0", "-1", "-2", "-3", "-4"});

        // get dge table as map from a json file
        dgeMale = getDGE("male");
        dgeFemale = getDGE("female");

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final FXMLLoader loader = new FXMLLoader();

        // Load fxml file
        Parent root = loader.load(getClass().getResource("Bmi.fxml").openStream());

        // Get controller from FXMLLoader
        Controller controller = loader.getController();
        controller.init();

        // Set the icon
        primaryStage.getIcons().add(new Image("file:icon.png"));

        // Set the title
        primaryStage.setTitle("Bmi calculator!");

        primaryStage.setScene(new Scene(root));

        // Deactivate resizability
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        primaryStage.show();
    }

    /**
     * Get the DGE table from a json file
     *
     * @param gender the gender for the table
     *
     * @return the table as map
     *
     * @throws IOException the io exception
     */
    private static Map<String,double[]> getDGE(String gender) throws IOException {
        // create map for the output
        Map<String, double[]> dgeMale = new HashMap<>();

        // read the json string
        try (FileInputStream is = new FileInputStream("dge.bmi") ) {
            try (JsonReader rdr = Json.createReader(is)) {
                JsonObject obj = rdr.readObject();

                String[] weights = new String[] {"UNDERWEIGHT", "NORMAL", "OVERWEIGHT", "OBESE", "SEVERELY_OBESE", "VERY_SEVERELY_OBESE"};

                JsonArray maleData = obj.getJsonArray(gender);

                // read the jason arrays in the json string and save them into the map
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

    /**
     * Get a map from a json file
     *
     * @param indexes the indexes of the json file
     * @param jsonFile the path to the json file
     *
     * @return the table as map
     *
     * @throws IOException the io exception
     */
    private static Map<String, double[]> getMap(String jsonFile, String[] indexes) throws IOException {
        // Create map for the output
        Map<String, double[]> map = new HashMap<>();

        // Read the data from the json string
        try (FileInputStream is = new FileInputStream(jsonFile) ) {
            try (JsonReader rdr = Json.createReader(is)) {
                JsonObject obj = rdr.readObject();

                /* Read the data from the json object and save them into the map */
                for (String index: indexes) {
                    JsonArray results = obj.getJsonArray(index);

                    double[] values = new double[]{results.getJsonNumber(0).doubleValue(), results.getJsonNumber(1).doubleValue()};
                    map.put(index, values);
                }
            }
        }
        return map;
    }
}