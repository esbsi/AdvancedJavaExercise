package be.abis.exercise.test;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class TestEx3_1 {

    @Test
    public void ex3_1Lambda(){
        BinaryOperator<Double> reducePercent = (a, b) -> a*(1-b/100);
        System.out.println(reducePercent.apply(50.0,10.0));
    }



}
