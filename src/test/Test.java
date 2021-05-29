package test;

import calc.Calculator;
import org.junit.Assert;
import org.w3c.dom.Node;

public class Test {

    @org.junit.Test
    public void dataLoadingTest() {
        Calculator calculator = new Calculator();
        Assert.assertNotNull(calculator);

        calculator.loadData();
        Assert.assertNotNull(calculator.currenciesList);

        for (int i=0; i < calculator.currenciesList.getLength(); i++) {
            Node node = calculator.currenciesList.item(i);
            Assert.assertEquals(Node.ELEMENT_NODE, node.getNodeType());
        }
    }

    @org.junit.Test
    public void computingTest() {
        Calculator calculator = new Calculator();
        calculator.loadData();

        calculator.currency = "CHF";
        calculator.factor1 = 100;
        calculator.compute();
        Assert.assertNotEquals(0, calculator.result);
        Assert.assertNotEquals(true, Double.isNaN(calculator.result));
    }
}
