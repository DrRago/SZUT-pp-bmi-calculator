package de.szut.dqi14.vriesgahr.BMI;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Leonhard.Gahr on 10.09.2016
 */
public class BmiCalcImplTest {
    @Test
    public void getCategory() throws Exception {
        BmiCalcImpl bmi = new BmiCalcImpl();

        BmiMain bmi2 = new BmiMain();

        bmi2.main(new String[0]);

        bmi.setSex(Sex.FEMALE);
        bmi.setWeight(97);
        bmi.setAge(24);
        bmi.setSize(1.80);

        assertEquals(WeightCategory.OBESE, bmi.getCategory());

        bmi.setSex(Sex.MALE);
        bmi.setWeight(97);
        bmi.setAge(24);
        bmi.setSize(1.80);

        assertEquals(WeightCategory.OBESE, bmi.getCategory());

        bmi.setSex(Sex.FEMALE);
        bmi.setWeight(75);
        bmi.setAge(40);
        bmi.setSize(1.70);

        assertEquals(WeightCategory.OVERWEIGHT, bmi.getCategory());

        bmi.setSex(Sex.MALE);
        bmi.setWeight(75);
        bmi.setAge(40);
        bmi.setSize(1.70);

        assertEquals(WeightCategory.NORMAL, bmi.getCategory());




        bmi.setWeight(90);
        bmi.setAge(56);
        bmi.setSize(1.83);

        assertEquals(WeightCategory.NORMAL, bmi.getCategory());

        bmi.setWeight(78);
        bmi.setAge(45);
        bmi.setSize(1.60);

        assertEquals(WeightCategory.OVERWEIGHT, bmi.getCategory());

        bmi.setWeight(30);
        bmi.setAge(36);
        bmi.setSize(1.00);

        assertEquals(WeightCategory.OVERWEIGHT, bmi.getCategory());

        bmi.setWeight(120);
        bmi.setAge(68);
        bmi.setSize(2.10);

        assertEquals(WeightCategory.NORMAL, bmi.getCategory());
    }

    @Test
    public void getIdealWeight() throws Exception {
        BmiCalcImpl bmi = new BmiCalcImpl();

        BmiMain bmi2 = new BmiMain();

        bmi2.main(new String[0]);

        bmi.setSex(Sex.FEMALE);
        bmi.setAge(24);
        bmi.setSize(1.80);

        assertEquals(72.9 , bmi.getIdealWeight(), 1e-15);

        bmi.setSex(Sex.MALE);
        bmi.setAge(65);
        bmi.setSize(1.90);

        assertEquals(66.785 , bmi.getIdealWeight(), 1e-15);


        bmi.setAge(0);


        bmi.setSex(Sex.FEMALE);
        bmi.setSize(1.30);

        assertEquals(36.335 , bmi.getIdealWeight(), 1e-15);

        bmi.setSex(Sex.MALE);
        bmi.setSize(2.10);

        assertEquals(99.22500000000001 , bmi.getIdealWeight(), 1e-15);
    }

}