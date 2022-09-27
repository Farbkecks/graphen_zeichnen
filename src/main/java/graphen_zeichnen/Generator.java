package graphen_zeichnen;

import java.util.ArrayList;
import java.util.Arrays;

public class Generator {

    private static ArrayList<String> splitFormula(String formula) {
        return new ArrayList<String>(Arrays.asList(formula.split("+-")));

    }

    public static void generateBasicGraph(String formula) {
        var formulaList = splitFormula(formula);
        System.out.println("lala");
    }
}