package de.szut.dqi14.vriesgahr.BMI;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * The type Controller.
 */
public class Controller {

    @FXML
    private TextField textAge;

    @FXML
    private TextField textWeight;

    @FXML
    private TextField textSize;

    @FXML
    private ChoiceBox choiceSex;

    /**
     * Button action event that handles the input and output for the gui
     */
    @FXML
    void buttonActionEvent() {
        // Check for required data
        if (textWeight.getText().isEmpty() | textSize.getText().isEmpty()){
            errorAlert();
        }
        else {
            // Replace every comma(',') with a dot('.') to cast the numbers into a double
            textWeight.setText(textWeight.getText().replace(',', '.'));
            textSize.setText(textSize.getText().replace(',', '.'));


            BmiCalcImpl bmiCalc = new BmiCalcImpl();
            // Check if optional data age is set and adds it to the calculation
            if (!textAge.getText().equals("")) {
                int age = Integer.parseInt(textAge.getText());
                bmiCalc.setAge(age);
            }

            // Check if optional data sex is set and adds it to the calculation
            if (choiceSex.getValue() != "" && choiceSex.getValue().equals("FEMALE") | choiceSex.getValue().equals("MALE")){
                bmiCalc.setSex(Sex.valueOf((String) choiceSex.getValue()));
            }

            // Cast required data into a double
            double size = Double.parseDouble(textSize.getText());
            double weight = Double.parseDouble(textWeight.getText());

            // Add required data to the calculation
            bmiCalc.setSize(size);
            bmiCalc.setWeight(weight);

            // Show the calculation result as a alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Your BMI score");
            alert.setHeaderText("Your Results:");
            // Round the bmi and the ideal weight to a 2 digit decimal number
            alert.setContentText("Your BMI is: " + (Math.round(bmiCalc.getBmi() * 100) / 100.0f) + "\n" + "With this BMI you are in the BMI category: " +
                    bmiCalc.getCategory() + "\n" + "Your Ideal weight would be around: " + (Math.round(bmiCalc.getIdealWeight() * 100) / 100.0f) + "kg");
            alert.showAndWait();
        }
    }


    /**
     * Raise a error message
     */
    private void errorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("Please enter your Size and Weight");
        alert.showAndWait();
    }

    /**
     * Initialize the textfield properties
     */
    void init() {
        // Make textAge to accept integers only
        textAge.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!"\\d*".matches(newValue)) {
                textAge.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Make textWeight to accept floats with ',' or '.' only
        textWeight.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*[.|,][0-9]*|[0-9]*[.|,]|[0-9]*")) {
                textWeight.setText(oldValue);
            }
        });

        // Make textWeight to accept floats with ',' or '.' only
        textSize.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*[.|,][0-9]*|[0-9]*[.|,]|[0-9]*")) {
                textSize.setText(oldValue);
            }
        });
    }
}
