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
     */
    @Override
    public WeightCategory getCategory() {
        if (size == 0.0 | weight == 0.0) throw new IllegalArgumentException("Please make sure that your weight and your size is entered");

        double bmi = getBmi();

        if (age != 0) {
            for (Object o : BmiMain.nrc.entrySet()) {
                Map.Entry current = (Map.Entry) o;
                if (age >= ((double[]) current.getValue())[0] && age < ((double[]) current.getValue())[1]) {
                    bmi = bmi + Integer.parseInt((String) current.getKey());
                }
            }
        }

        if (sex != null){
            if (Sex.FEMALE == sex){
                for (Object o : BmiMain.dgeFemale.entrySet()) {
                    Map.Entry current = (Map.Entry) o;
                    if (bmi >= ((double[]) current.getValue())[0] && bmi < ((double[]) current.getValue())[1]) {
                        return WeightCategory.valueOf((String) current.getKey());
                    }
                }
            }
            else if (Sex.MALE == sex){
                for (Object o : BmiMain.dgeMale.entrySet()) {
                    Map.Entry current = (Map.Entry) o;
                    if (bmi >= ((double[]) current.getValue())[0] && bmi < ((double[]) current.getValue())[1]) {
                        return WeightCategory.valueOf((String) current.getKey());
                    }
                }
            }
        }
        else{
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

        int ageValue = 0;

        if (age != 0) {
            for (Object o : BmiMain.nrc.entrySet()) {
                Map.Entry current = (Map.Entry) o;
                if (age >= ((double[]) current.getValue())[0] && age < ((double[]) current.getValue())[1]) {
                    ageValue = Integer.parseInt((String) current.getKey());
                }
            }
        }

        if (sex != null) {
            double idealBmi = 0;
            if (Sex.FEMALE == sex) {
                double[] normalBmi = BmiMain.dgeFemale.get("NORMAL");
                idealBmi = ((normalBmi[0] + normalBmi[1]) / 2) + ageValue;
            } else if (Sex.MALE == sex) {
                double[] normalBmi = BmiMain.dgeMale.get("NORMAL");
                idealBmi = ((normalBmi[0] + normalBmi[1]) / 2) + ageValue;
            }
            return idealBmi * size * size;
        }
        else {
            double[] normalBmi = BmiMain.who.get("NORMAL");
            double idealBmi = ((normalBmi[0] + normalBmi[1]) / 2) + ageValue;

            return idealBmi * size * size;
        }
    }
}