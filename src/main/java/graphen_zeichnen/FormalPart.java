package graphen_zeichnen;

import java.lang.Math;

public class FormalPart {
    private int sign = 1;
    private int number1 = 1;
    private int number2 = 1;
    private int number3 = 1;
    private PosX posX = PosX.none;

    public int calculate(int x) {
        switch (posX) {
            case none:
                return sign * number1 * (int) Math.pow(number2, number3);
            case one:
                return sign * x * (int) Math.pow(number2, number3);
            case two:
                return sign * number1 * (int) Math.pow(x, number3);
            case three:
                return sign * x * (int) Math.pow(number2, x);
            default: {
                System.out.println("Something got wrong while calculating");
                System.exit(1);
                return 0;
            }
        }
    }

    public FormalPart(int sign, int num1, int num2, int num3, PosX posX) {
        this.sign = sign;
        this.number1 = num1;
        this.number2 = num2;
        this.number3 = num3;
        this.posX = posX;
    }
}

enum PosX {
    one,
    two,
    three,
    none
}
