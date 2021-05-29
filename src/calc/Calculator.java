package calc;

import parser.FileLoader;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.Console;
import java.util.Scanner;


public class Calculator {
    public NodeList currenciesList;
    public String currency;
    public double result, factor1, factor2;

    public Calculator() {
        this.currenciesList = null;
        this.currency = "";
        this.result = 0;
        this.factor1 = 0;
        this.factor2 = 0;
    }

    public void loadData() {
        System.out.println("\nCurrency calculator");

        System.out.println("\nRates:");
        FileLoader fileLoader = new FileLoader();
        currenciesList = fileLoader.readFile();
    }

    public void userService() {
        Console console = System.console();

        System.out.println("\nEnter the amount in EUR: ");
        Scanner scanner = new Scanner(console.reader());
        String amount = scanner.nextLine();

        try {
            factor1 = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            System.out.println("\nEntered amount is not a number!\n");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("\nEnter the target currency: ");
        currency = scanner.nextLine();
    }

    public void compute() {
        for (int i=2; i < currenciesList.getLength(); i++) {
            Node node = currenciesList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                if (currency.equals(element.getAttribute("currency"))) {
                    try {
                        factor2 = Double.parseDouble(element.getAttribute("rate"));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    result = factor1 * factor2;
                    break;
                }
            }

            if (i == currenciesList.getLength()-1) {
                System.out.println("\nNo data about the entered currency!\n");
                System.exit(1);
            }
        }

        System.out.println("\nCalculated amount is " + result + " " + currency + "\n");
    }
}
