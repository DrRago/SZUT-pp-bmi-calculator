package de.szut.dqi14.vriesgahr.BMI;

import java.util.Map;

/**
 * @author Pascal de Vries
 * @author Leonhard Gahr
 */

public class BmiCalcImpl implements BmiCalc {

    private Sex sex;
    private double size;
    private int age;
    private double weight;

    /**
     * current BMI
     *
     * @return BMI (see exercise for details)
     */
    @Override
    public double getBmi() {
        return weight/(size*size);
    }

    /**
     * current Weight Category
     *
     * @return current weight category
     *
     * @throws IllegalArgumentException if required arguments are missing
     */
    @Override
    public WeightCategory getCategory() {
        // Throw exception if required arguments are missing
        if (size == 0.0 | weight == 0.0) throw new IllegalArgumentException("Please make sure that your weight and your size is entered");

        double bmi = getBmi();

        // change the bmi in reference to the age instead of changing the borders of the weightcategories
        if (age != 0) {

            // iterate through the map of the nrc table
            for (Object o : BmiMain.nrc.entrySet()) {
                Map.Entry current = (Map.Entry) o;
                if (age >= ((double[]) current.getValue())[0] && age < ((double[]) current.getValue())[1]) {
                    bmi = bmi + Integer.parseInt((String) current.getKey());
                }
            }
        }

        // check in which weightcategorie the bmi belongs for male or female in the dge table
        if (sex != null) {
            if (Sex.FEMALE == sex) {
                for (Object o : BmiMain.dgeFemale.entrySet()) {
                    Map.Entry current = (Map.Entry) o;
                    if (bmi >= ((double[]) current.getValue())[0] && bmi < ((double[]) current.getValue())[1]) {
                        return WeightCategory.valueOf((String) current.getKey());
                    }
                }
            }
            else if (Sex.MALE == sex) {
                for (Object o : BmiMain.dgeMale.entrySet()) {
                    Map.Entry current = (Map.Entry) o;
                    if (bmi >= ((double[]) current.getValue())[0] && bmi < ((double[]) current.getValue())[1]) {
                        return WeightCategory.valueOf((String) current.getKey());
                    }
                }
            }
        }
        // check in which weightcategorie the bmi belongs for no gender in the who table
        else {
            for (Object o : BmiMain.who.entrySet()) {
                Map.Entry current = (Map.Entry) o;
                if (bmi >= ((double[]) current.getValue())[0] && bmi < ((double[]) current.getValue())[1]) {
                    return WeightCategory.valueOf((String) current.getKey());
                }
            }
        }
        return null;
    }

    /**
     * sex getter
     *
     * @return the sex
     */
    @Override
    public Sex getSex() {
        return sex;
    }

    /**
     * sex setter
     *
     * @param sex the sex to set, null to reset
     */
    @Override
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * size getter
     *
     * @return the size
     */
    @Override
    public double getSize() {
        return size;
    }

    /**
     * size setter
     *
     * @param size the size to set
     */
    @Override
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * age getter
     *
     * @return the age
     */
    @Override
    public int getAge() {
        return age;
    }

    /**
     * age setter
     *
     * @param age the age to set, null to reset
     */
    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * weight getter
     *
     * @return the weight
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * weight setter
     *
     * @param weight the weight to set
     */
    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * calculate ideal weight
     *
     * @return the ideal weight according to given values
     */
    @Override
    public double getIdealWeight() {
        if (size == 0.0) throw new IllegalArgumentException("Please make sure that your size is entered");

        // save the value to change the bmi in reference to the age
        int ageValue = 0;

        if (age != 0) {
            for (Object o : BmiMain.nrc.entrySet()) {
                Map.Entry current = (Map.Entry) o;
                if (age >= ((double[]) current.getValue())[0] && age < ((double[]) current.getValue())[1]) {
                    ageValue = Integer.parseInt((String) current.getKey());
                }
            }
        }

        double idealBmi = 0;

        // get the ideal bmi for male and female
        if (sex != null) {
            if (Sex.FEMALE == sex) {
                double[] normalBmi = {BmiMain.dgeFemale.get("NORMAL")[0] + (ageValue * -1), BmiMain.dgeFemale.get("NORMAL")[1] + (ageValue * -1)};

                // calculate the average of the borders of the ideal bmi and change the ideal bmi according to the age with the age value
                idealBmi = ((normalBmi[0] + normalBmi[1]) / 2);
            } else if (Sex.MALE == sex) {
                double[] normalBmi = {BmiMain.dgeMale.get("NORMAL")[0] + (ageValue * -1), BmiMain.dgeMale.get("NORMAL")[1] + (ageValue * -1)};

                // calculate the average of the borders of the ideal bmi and change the ideal bmi according to the age with the age value
                idealBmi = ((normalBmi[0] + normalBmi[1]) / 2);
            }
        }
        else {
            double[] normalBmi = {BmiMain.dgeMale.get("NORMAL")[0] + (ageValue * -1), BmiMain.dgeMale.get("NORMAL")[1] + (ageValue * -1)};

            // calculate the average of the borders of the ideal bmi and change the ideal bmi according to the age with the age value
            idealBmi = ((normalBmi[0] + normalBmi[1]) / 2);
        }
        // calculate the ideal weight with the size and the ideal bmi
        return idealBmi * size * size;
    }
}