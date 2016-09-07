package de.szut.dqi14.vriesgahr.BMI;

/**
 * @author Pascal de Vries
 * @author Leonhard Gahr
 */

// TODO: 24.08.2016 add config file 
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
        // TODO: 07.09.2016 19.5 mit idealem BMI in dem Altex ersetzen
        return 19.5 * (size * size);
    }
}
