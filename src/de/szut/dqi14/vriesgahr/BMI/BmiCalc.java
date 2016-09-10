package de.szut.dqi14.vriesgahr.BMI;

/** Interface for BMI Calculation
 * Created by loos on 13.11.2015.
 */
public interface BmiCalc {

	/** current BMI
	 *
	 * @return BMI (see exercise for details)
	 */
	double getBmi();

	/** current Weight Category
	 *
	 * @return current weight category
	 */
	WeightCategory getCategory();

	/** sex getter
	 * @return the sex
	 */
	Sex getSex();

	/** sex setter
	 * @param sex the sex to set, null to reset
	 */
	void setSex(Sex sex);

	/** size getter
	 * @return the size
	 */
	double getSize();

	/** size setter
	 * @param size the size to set
	 */
	void setSize(double size);

	/** age getter
	 * @return the age
	 */
	int getAge();

	/** age setter
	 * @param age the age to set, null to reset
	 */
	void setAge(Integer age);

	/** weight getter
	 * @return the weight
	 */
	double getWeight();

	/** weight setter
	 * @param weight the weight to set
	 */
	void setWeight(double weight);

	/** calculate ideal weight
	 *
	 * @return the ideal weight according to given values
	 */
	double getIdealWeight();
}
