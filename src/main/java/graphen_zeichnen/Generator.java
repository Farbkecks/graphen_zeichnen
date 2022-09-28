package graphen_zeichnen;

import java.util.ArrayList;
import java.util.Arrays;

public class Generator {

    private static ArrayList<String> splitFormulaInToArrayList(String formula) {
        var splittedFormula = new ArrayList<String>();
        var formulaChar = formula.toCharArray();
        var start = 0;
        for (int i = 0; i < formulaChar.length; i++) {
            if (formulaChar[i] == '+' || formulaChar[i] == '-' && i != 0) {
                splittedFormula.add(formula.substring(start, i));
                start = i;
            }
        }

        splittedFormula.add(formula.substring(start, formulaChar.length));
        return splittedFormula;
    }

    public static void generateBasicGraph(String formula) {
        var formulaList = splitFormulaInToArrayList(formula);
    }
}