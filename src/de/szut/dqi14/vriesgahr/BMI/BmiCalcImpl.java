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
        if (size == 0.0 | weight == 0.0) throw new IllegalArgumentException("Please make sure that your weight and your size is entered");
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
        if (getSize() == 0.0 | getWeight() == 0.0) throw new IllegalArgumentException("Please make sure that your weight and your size is entered"); // throw exception if required arguments are missing

        double bmi = getBmi();

        /* change the bmi in reference to the age instead of changing the borders of the weightcategories */
        if (getAge() != 0) {
            Map.Entry current;

            /* iterate through the map of the nrc table */
            for (Object o : BmiMain.nrc.entrySet()) {
                current = (Map.Entry) o;
                if (getAge() >= ((double[]) current.getValue())[0] && getAge() < ((double[]) current.getValue())[1]) {
                    bmi = bmi + Integer.parseInt((String) current.getKey());
                }
            }
        }

        /* check in which weightcategorie the bmi belongs for male or female in the dge table */
        if (getSex() != null){
            if (Sex.FEMALE == getSex()){
                for (Object o : BmiMain.dgeFemale.entrySet()) {
                    Map.Entry current = (Map.Entry) o;
                    if (bmi >= ((double[]) current.getValue())[0] && bmi < ((double[]) current.getValue())[1]) {
                        return WeightCategory.valueOf((String) current.getKey());
                    }
                }
            }
            else if (Sex.MALE == getSex()){
                for (Object o : BmiMain.dgeMale.entrySet()) {
                    Map.Entry current = (Map.Entry) o;
                    if (bmi >= ((double[]) current.getValue())[0] && bmi < ((double[]) current.getValue())[1]) {
                        return WeightCategory.valueOf((String) current.getKey());
                    }
                }
            }
        }
        /* check in which weightcategorie the bmi belongs for no gender in the who table */
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
        return this.sex;
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
        return this.age;
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
        return this.weight;
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
        if (getSize() == 0.0) throw new IllegalArgumentException("Please make sure that your size is entered"); // throw exception if the required argument is missing

        /* save the value to change the bmi in reference to the age */
        int ageValue = 0;

        if (getAge() != 0) {
            for (Object o : BmiMain.nrc.entrySet()) {
                Map.Entry current = (Map.Entry) o;
                if (getAge() >= ((double[]) current.getValue())[0] && getAge() < ((double[]) current.getValue())[1]) {
                    ageValue = Integer.parseInt((String) current.getKey());
                }
            }
        }

        double idealBmi = 0;

        /* get the ideal bmi for male and female */
        if (getSex() != null) {
            if (Sex.FEMALE == getSex()) {
                double[] normalBmi = BmiMain.dgeFemale.get("NORMAL");

                /* calculate the average of the borders of the ideal bmi and change the ideal bmi according to the age with the age value */
                idealBmi = ((normalBmi[0] + normalBmi[1]) / 2) - ageValue;
            } else if (Sex.MALE == getSex()) {
                double[] normalBmi = BmiMain.dgeMale.get("NORMAL");

                /* calculate the average of the borders of the ideal bmi and change the ideal bmi according to the age with the age value */
                idealBmi = ((normalBmi[0] + normalBmi[1]) / 2) - ageValue;
            }
        }
        else {
            double[] normalBmi = BmiMain.who.get("NORMAL");

            /* calculate the average of the borders of the ideal bmi and change the ideal bmi according to the age with the age value */
            idealBmi = ((normalBmi[0] + normalBmi[1]) / 2) - ageValue;

        }
        return idealBmi * getSize() * getSize(); // calculate the ideal weight with the size and the ideal bmi see
    }
}