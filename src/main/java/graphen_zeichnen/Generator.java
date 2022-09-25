package graphen_zeichnen;

import java.util.ArrayList;
import java.lang.Math;

public class Generator {
    // Die Originale String Formel wird in eine Liste aus Strings geändert welche
    // die
    // einezellen Rechenaufgaben zwischen + und - enthalten
    static ArrayList<String> splitString(String formula) {
        char[] formulaCharList = formula.toCharArray(); // String in CharArray um für + und - zu suchen
        ArrayList<String> formelList = new ArrayList<>(); // Liste mit den aufgeteilte Formel Parts
        int oldSign = 0;
        for (int index = 0; index < formulaCharList.length; index++) {
            if (formulaCharList[index] == '+' || formulaCharList[index] == '-') { // index von + und - finden
                formelList.add(formula.substring(oldSign, index));
                oldSign = index;
            }
        }

        formelList.add(formula.substring(oldSign, formulaCharList.length)); // Substring von letzten +/- bis zum Ende
        String firstPart = formelList.get(0);
        formelList.set(0, "+" + firstPart); // TODO: foramel kann Negativ anfangen

        return formelList;
    }

    static boolean testForNummer(char number) {
        for (int i = 0; i < 10; i++) {
            int tempNummer = Character.getNumericValue(number);
            if (tempNummer == i) {
                return true;
            }
        }
        return false;
    }

    static Operation testForOperation(char character) {
        // System.out.println(character);
        switch (character) {
            case '*':
                return Operation.times;
            case '/':
                return Operation.divided;
            case '^':
                return Operation.power;
            default: {
                System.out.println(
                        "Falsche eingabe bei Operation\n Beachte das unter Windows die Formel in Anführungszeichen angegeben werden muss");
                System.exit(1);
                return Operation.none; // wird nicht erreicht nur für den Compiler
            }
        }
    }

    static Sign testForSign(char character) {
        switch (character) {
            case '+':
                return Sign.add;
            case '-':
                return Sign.subtract;
            default: {
                System.out.println("Falsche eingabe bei dem Vorzeichen");
                System.exit(1);
                return Sign.none; // wird nicht erreicht nur für den Compiler
            }
        }
    }

    static ArrayList<FormalPart> getFormelPartObjektsFromString(ArrayList<String> formelList) {
        ArrayList<FormalPart> formelObjekts = new ArrayList<>();
        for (String formelPartString : formelList) {
            FormalPart formalPart = new FormalPart();
            char[] formelPartCharArray = formelPartString.toCharArray();

            formalPart.sign = testForSign(formelPartCharArray[0]);
            if (testForNummer(formelPartCharArray[1]) == true) {
                formalPart.firstNumber = Character.getNumericValue(formelPartCharArray[1]);
            } else {
                formalPart.indexForX = 1;
            }
            if (formelPartCharArray.length == 2) {
                formalPart.partVersion = PartVersion.one;
                formelObjekts.add(formalPart);
                continue;
            }

            formalPart.firstOperation = testForOperation(formelPartCharArray[2]);
            if (testForNummer(formelPartCharArray[3]) == true) {
                formalPart.secondNumber = Character.getNumericValue(formelPartCharArray[3]);
            } else {
                formalPart.indexForX = 3;
            }
            if (formelPartCharArray.length == 4) {
                formalPart.partVersion = PartVersion.three;
                formelObjekts.add(formalPart);
                continue;
            }

            formalPart.secOperation = testForOperation(formelPartCharArray[4]);
            if (testForNummer(formelPartCharArray[5]) == true) {
                formalPart.thirdNumber = Character.getNumericValue(formelPartCharArray[5]);
            } else {
                formalPart.indexForX = 5;
            }
            formalPart.partVersion = PartVersion.five;
            formelObjekts.add(formalPart);
        }

        return formelObjekts;
    }

    static ArrayList<Coordinate2D> calculateGraph(ArrayList<FormalPart> formelObjekts) {
        ArrayList<Coordinate2D> graph = new ArrayList<>();
        for (int x = 1; x < 50; x++) {
            double tempResult = 0;
            for (FormalPart part : formelObjekts) {
                tempResult += part.calculatePart(x);
            }
            if (tempResult < 0) {
                continue;
            }
            graph.add(new Coordinate2D(x, (int) Math.round(tempResult)));
        }
        return graph;
    }

    static ArrayList<Coordinate2D> generateBasicGraph(String formula) {
        ArrayList<String> formelList = splitString(formula);
        ArrayList<FormalPart> formelObjekts = getFormelPartObjektsFromString(formelList);
        return calculateGraph(formelObjekts);
    }
}
