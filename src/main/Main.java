package main;

import calc.Calculator;

public class Main {
    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        calculator.loadData();

        calculator.userService();

        calculator.compute();
    }
}
