package de.szut.dqi14.vriesgahr.BMI;

/** enum class to handle the sex of a person
 *
 */
public enum Sex {
	FEMALE(0), MALE(1);

	private final int index;

	private Sex(int index) {
		this.index = index;
		;
	}

	/** get a certain index for each sex
	 *
	 * @return definite index for a sex
	 */
	public int getIndex() {
		return index;
	}
}
