package graphen_zeichnen;

import java.util.ArrayList;
import java.lang.Math;

public class Generator {
    // Die Orginale String Formel wird in eine Liste aus Strings geändert welche die
    // einezelnen Rechenaufgaben zwischen + und - enthalten
    static ArrayList<String> splitString(String formel) {
        char[] formelCharList = formel.toCharArray(); // String in CharArray um für + und - zu suchen
        ArrayList<String> formelList = new ArrayList<>(); // Liste mit den aufgeteilte Formel Parts
        int oldSign = 0;
        for (int index = 0; index < formelCharList.length; index++) {
            if (formelCharList[index] == '+' || formelCharList[index] == '-') { // index von + und - finden
                formelList.add(formel.substring(oldSign, index));
                oldSign = index;
            }
        }

        formelList.add(formel.substring(oldSign, formelCharList.length)); // Substring von letzen +/- bis zum Ende
        String firstPart = formelList.get(0);
        formelList.set(0, "+" + firstPart); // TODO: foramel kann Negativ anfangen

        return formelList;
    }

    static boolean testForNummer(char nummber) {
        for (int i = 0; i < 10; i++) {
            int tempNummer = Character.getNumericValue(nummber);
            if (tempNummer == i) {
                return true;
            }
        }
        return false;
    }

    static Operation testForOperation(char caracter) {
        switch (caracter) {
            case '*':
                return Operation.times;
            case '/':
                return Operation.divided;
            case '^':
                return Operation.power;
            default: {
                System.out.println("Falsche einfabe bei Operation");
                System.exit(1);
                return Operation.none; // wird nicht erreicht nur für den Compieler
            }
        }
    }

    static Sign testForSign(char caracter) {
        switch (caracter) {
            case '+':
                return Sign.add;
            case '-':
                return Sign.subtract;
            default: {
                System.out.println("Falsche eingabe bei dem Vorzeichen");
                System.exit(1);
                return Sign.none; // wird nicht erreicht nur für den Compieler
            }
        }
    }

    static ArrayList<FormalPart> getFormelPartOpjektsfromString(ArrayList<String> formelList) {
        ArrayList<FormalPart> formelObjekts = new ArrayList<>();
        for (String formelPartString : formelList) {
            FormalPart formalPart = new FormalPart();
            char[] formelPartCharArray = formelPartString.toCharArray();

            formalPart.sign = testForSign(formelPartCharArray[0]);
            if (testForNummer(formelPartCharArray[1]) == true) {
                formalPart.firstNummber = Character.getNumericValue(formelPartCharArray[1]);
            } else {
                formalPart.indexforX = 1;
            }
            if (formelPartCharArray.length == 2) {
                formalPart.partVersion = PartVersion.one;
                formelObjekts.add(formalPart);
                continue;
            }

            formalPart.firstOperation = testForOperation(formelPartCharArray[2]);
            if (testForNummer(formelPartCharArray[3]) == true) {
                formalPart.secondNummber = Character.getNumericValue(formelPartCharArray[3]);
            } else {
                formalPart.indexforX = 3;
            }
            if (formelPartCharArray.length == 4) {
                formalPart.partVersion = PartVersion.three;
                formelObjekts.add(formalPart);
                continue;
            }

            formalPart.secOperation = testForOperation(formelPartCharArray[4]);
            if (testForNummer(formelPartCharArray[5]) == true) {
                formalPart.thirtNummber = Character.getNumericValue(formelPartCharArray[5]);
            } else {
                formalPart.indexforX = 5;
            }
            formalPart.partVersion = PartVersion.five;
            formelObjekts.add(formalPart);
        }

        return formelObjekts;
    }

    static ArrayList<Coordinate2D> calculateGraph(ArrayList<FormalPart> formelObjekts) {
        ArrayList<Coordinate2D> graph = new ArrayList<>();
        for (int x = 1; x < 20; x++) {
            double tempResulte = 0;
            for (FormalPart part : formelObjekts) {
                tempResulte += part.calculatePart(x);
            }
            if (tempResulte < 0) {
                continue;
            }
            if (tempResulte > 50) {
                continue;
            }
            graph.add(new Coordinate2D(x, (int) Math.round(tempResulte)));
        }
        return graph;
    }

    static ArrayList<Coordinate2D> generateBasicGraph(String formel) {
        ArrayList<String> formelList = splitString(formel);
        ArrayList<FormalPart> formelObjekts = getFormelPartOpjektsfromString(formelList);
        return calculateGraph(formelObjekts);
    }
}
