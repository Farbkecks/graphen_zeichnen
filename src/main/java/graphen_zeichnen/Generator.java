package graphen_zeichnen;

import java.util.ArrayList;

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

    private static int findChar(String formula, char find) {
        for (int i = 0; i < formula.length(); i++) {
            if (formula.toCharArray()[i] == find) {
                return i;
            }
        }
        return -1;
    }

    private static ArrayList<FormulaPart> changeStringsInToFormulaParts(ArrayList<String> formulas) {
        var formulaParts = new ArrayList<FormulaPart>();
        for (String formula : formulas) {
            var formulaChar = formula.toCharArray();
            var indexX = findChar(formula, 'x');
            var indexTimes = findChar(formula, '*');
            var indexPow = findChar(formula, '^');
            int start = 0;
            if (formulaChar[0] == '+' || formulaChar[0] == '-') {
                start = 1;
            }
            int number2;
            if (indexPow > indexTimes) {
                number2 = Integer.valueOf(formula.substring(indexTimes, indexPow));
            } else {
                number2 = Integer.valueOf(formula.substring(indexPow, indexPow));
            }
            int number1, number3;
            if (indexTimes < indexPow) {
                number1 = Integer.valueOf(formula.substring(start, indexTimes));
                number3 = Integer.valueOf(formula.substring(indexPow, formula.length()));
            } else {
                number3 = Integer.valueOf(formula.substring(start, indexTimes));
                number1 = Integer.valueOf(formula.substring(indexPow, formula.length()));
            }
            System.out.print("obHallj");
        }
        return formulaParts;
    }

    public static void generateBasicGraph(String formula) {
        var formulaList = splitFormulaInToArrayList(formula);
        var formulaParts = changeStringsInToFormulaParts(formulaList);
    }
}