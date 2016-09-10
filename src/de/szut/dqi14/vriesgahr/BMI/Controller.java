package de.szut.dqi14.vriesgahr.BMI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.text.ParseException;
import java.text.StringCharacterIterator;

import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 * Created by pasca on 10.09.2016.
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

    public void buttonActionEvent(ActionEvent actionEvent) {
        if (textWeight.getText().equals("") | textSize.getText().equals("")){
            errorAlert("Please enter your Size and Weight");
        }
        else {
            textWeight.setText(textWeight.getText().replace(',', '.'));
            textSize.setText(textSize.getText().replace(',', '.'));
            try {
                BmiCalcImpl bmiCalc = new BmiCalcImpl();
                if (!textAge.getText().equals("")) {
                    int age = Integer.parseInt(textAge.getText());
                    bmiCalc.setAge(age);
                }

                if ( choiceSex.getValue().equals("FEMALE") | choiceSex.getValue().equals("MALE")){
                    bmiCalc.setSex(Sex.valueOf((String) choiceSex.getValue()));
                }

                double size = Double.parseDouble(textSize.getText());
                bmiCalc.setSize(size);

                double weight = Double.parseDouble(textWeight.getText());
                bmiCalc.setWeight(weight);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Your BMI score");
                alert.setHeaderText("Your Results:");
                alert.setContentText("Your BMI is: " + (Math.round(bmiCalc.getBmi() * 100) / 100) + "\n" + "With this BMI you are in the BMI category: " +
                        bmiCalc.getCategory() + "\n" + "Your Ideal weight would be around: " + (Math.round(bmiCalc.getIdealWeight() * 100) / 100) + "kg");
                alert.showAndWait();
            }
            catch (NumberFormatException e) {
                errorAlert("Please enter only numbers");
            }
        }
    }

    private void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
