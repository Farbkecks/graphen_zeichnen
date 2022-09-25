package graphen_zeichnen;

import java.lang.Math;

public class FormalPart {
    Sign sign;
    Operation firstOperation;
    double firstNumber;
    double secondNumber;
    Operation secOperation;
    double thirdNumber;
    int indexForX;
    PartVersion partVersion;

    public double calculateVersionOne() {
        switch (this.sign) {
            case add:
                return this.firstNumber;
            case subtract:
                return this.firstNumber * -1;
            default: {
                System.out.println("Berechnung Fehlerhaft 1");
                System.exit(1);
                return 0;
            }
        }
    }

    public double calculateVersionThree(double ValueOfX) {
        double result;
        double firstNumLocal;
        double secNumLocal;

        switch (this.indexForX) {
            case 1: {
                firstNumLocal = ValueOfX;
                secNumLocal = this.secondNumber;
                break;
            }
            case 3: {
                firstNumLocal = this.firstNumber;
                secNumLocal = ValueOfX;
                break;
            }
            default: {
                System.out.println("Berechnung fehlerhaft 21");
                System.exit(1);
                firstNumLocal = 0;
                secNumLocal = 0;
            }
        }

        switch (this.firstOperation) {
            case power:
                result = Math.pow(firstNumLocal, secNumLocal);
                break;
            case times:
                result = firstNumLocal * secNumLocal;
                break;
            case divided:
                result = firstNumLocal / secNumLocal;
                break;
            default: {
                System.out.println("Berechnung fehlerhaft 22");
                System.exit(1);
                result = 0;
            }
        }
        switch (this.sign) {
            case add:
                return result;
            case subtract:
                return result * -1;
            default: {
                System.out.println("Berechnung Fehlerhaft 23");
                System.exit(1);
                return 0;
            }
        }
    }

    public int indexPower() {
        if (this.firstOperation == Operation.power) {
            return 1;
        } else if (this.secOperation == Operation.power) {
            return 2;
        } else {
            return 0;
        }
    }

    public double calculateVersionFive(double ValueOfX) {
        double result;
        double firstNumLocal;
        double secNumLocal;
        double thirdNumLocal;

        switch (this.indexForX) {
            case 1: {
                firstNumLocal = ValueOfX;
                secNumLocal = this.secondNumber;
                thirdNumLocal = this.thirdNumber;
                break;
            }
            case 3: {
                firstNumLocal = this.firstNumber;
                secNumLocal = ValueOfX;
                thirdNumLocal = this.thirdNumber;
                break;
            }
            case 5: {
                firstNumLocal = this.firstNumber;
                secNumLocal = this.secondNumber;
                thirdNumLocal = ValueOfX;
                break;
            }
            default: {
                System.out.println("Berechnung fehlerhaft 31");
                System.exit(1);
                firstNumLocal = 0;
                secNumLocal = 0;
                thirdNumLocal = 0;
            }
        }

        switch (indexPower()) {
            case 0: {
                switch (this.firstOperation) {
                    case times:
                        result = firstNumLocal * secNumLocal;
                        break;
                    case divided:
                        result = firstNumLocal / secNumLocal;
                        break;
                    default: {
                        System.out.println("Berechnung fehlerhaft 32");
                        System.exit(1);
                        result = 0;
                    }
                }
                switch (this.secOperation) {
                    case times:
                        result = result * thirdNumLocal;
                        break;
                    case divided:
                        result = result / thirdNumLocal;
                        break;
                    default: {
                        System.out.println("Berechnung fehlerhaft 33");
                        System.exit(1);
                        result = 0;
                    }
                }
            }
            case 1: {
                result = Math.pow(firstNumLocal, secNumLocal);
                switch (this.secOperation) {
                    case times:
                        result = result * thirdNumLocal;
                        break;
                    case divided:
                        result = result / thirdNumLocal;
                        break;
                    default: {
                        System.out.println("Berechnung fehlerhaft 34");
                        System.exit(1);
                        result = 0;
                    }
                }
                break;
            }
            case 2: {
                result = Math.pow(secNumLocal, thirdNumLocal);
                switch (this.firstOperation) {
                    case times:
                        result = result * firstNumLocal;
                        break;
                    case divided:
                        result = result / firstNumLocal;
                        break;
                    default: {
                        System.out.println("Berechnung fehlerhaft 35");
                        System.exit(1);
                    }
                }
                break;
            }
            default: {
                System.out.println("Berechnung fehlerhaft 36");
                System.exit(1);
                result = 0;
            }

        }
        switch (this.sign) {
            case add:
                return result;
            case subtract:
                return result * -1;
            default: {
                System.out.println("Berechnung Fehlerhaft 37");
                System.exit(1);
                return 0;
            }
        }

    }

    public double calculatePart(double ValueOfX) {
        switch (partVersion) {
            case one:
                return calculateVersionOne();
            case three:
                return calculateVersionThree(ValueOfX);
            case five:
                return calculateVersionFive(ValueOfX);
            default: {
                System.out.println("Berechnung fehlerhaft");
                System.exit(1);
                return 0;
            }
        }
    }
}

enum PartVersion {
    one,
    three,
    five
}

enum Operation {
    none,
    times,
    divided,
    power
}

enum Sign {
    none,
    add,
    subtract
}