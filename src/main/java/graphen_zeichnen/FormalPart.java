package graphen_zeichnen;

import java.lang.Math;

public class FormalPart {
    Sign sign;
    Operation firstOperation;
    double firstNummber;
    double secondNummber;
    Operation secOperation;
    double thirtNummber;
    int indexforX;
    PartVersion partVersion;

    public double calculateVersionOne() {
        switch (this.sign) {
            case add:
                return this.firstNummber;
            case subtract:
                return this.firstNummber * -1;
            default: {
                System.out.println("Berechung Felerhaft 1");
                System.exit(1);
                return 0;
            }
        }
    }

    public double calculateVersionThree(double ValueOfX) {
        double resault;
        double firstNumLocal;
        double secNumLocal;

        switch (this.indexforX) {
            case 1: {
                firstNumLocal = ValueOfX;
                secNumLocal = this.secondNummber;
                break;
            }
            case 3: {
                firstNumLocal = this.firstNummber;
                secNumLocal = ValueOfX;
                break;
            }
            default: {
                System.out.println("Berechung fehlerhaft 21");
                System.exit(1);
                firstNumLocal = 0;
                secNumLocal = 0;
            }
        }

        switch (this.firstOperation) {
            case power:
                resault = Math.pow(firstNumLocal, secNumLocal);
                break;
            case times:
                resault = firstNumLocal * secNumLocal;
                break;
            case divided:
                resault = firstNumLocal / secNumLocal;
                break;
            default: {
                System.out.println("Berechung fehlerhaft 22");
                System.exit(1);
                resault = 0;
            }
        }
        switch (this.sign) {
            case add:
                return resault;
            case subtract:
                return resault * -1;
            default: {
                System.out.println("Berechung Felerhaft 23");
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
        double resault;
        double firstNumLocal;
        double secNumLocal;
        double thirtNumLocal;

        switch (this.indexforX) {
            case 1: {
                firstNumLocal = ValueOfX;
                secNumLocal = this.secondNummber;
                thirtNumLocal = this.thirtNummber;
                break;
            }
            case 3: {
                firstNumLocal = this.firstNummber;
                secNumLocal = ValueOfX;
                thirtNumLocal = this.thirtNummber;
                break;
            }
            case 5: {
                firstNumLocal = this.firstNummber;
                secNumLocal = this.secondNummber;
                thirtNumLocal = ValueOfX;
                break;
            }
            default: {
                System.out.println("Berechung fehlerhaft 31");
                System.exit(1);
                firstNumLocal = 0;
                secNumLocal = 0;
                thirtNumLocal = 0;
            }
        }

        switch (indexPower()) {
            case 0: {
                switch (this.firstOperation) {
                    case times:
                        resault = firstNumLocal * secNumLocal;
                        break;
                    case divided:
                        resault = firstNumLocal / secNumLocal;
                        break;
                    default: {
                        System.out.println("Berechung fehlerhaft 32");
                        System.exit(1);
                        resault = 0;
                    }
                }
                switch (this.secOperation) {
                    case times:
                        resault = resault * thirtNumLocal;
                        break;
                    case divided:
                        resault = resault / thirtNumLocal;
                        break;
                    default: {
                        System.out.println("Berechung fehlerhaft 33");
                        System.exit(1);
                        resault = 0;
                    }
                }
            }
            case 1: {
                resault = Math.pow(firstNumLocal, secNumLocal);
                switch (this.secOperation) {
                    case times:
                        resault = resault * thirtNumLocal;
                        break;
                    case divided:
                        resault = resault / thirtNumLocal;
                        break;
                    default: {
                        System.out.println("Berechung fehlerhaft 34");
                        System.exit(1);
                        resault = 0;
                    }
                }
                break;
            }
            case 2: {
                resault = Math.pow(secNumLocal, thirtNumLocal);
                switch (this.firstOperation) {
                    case times:
                        resault = resault * firstNumLocal;
                        break;
                    case divided:
                        resault = resault / firstNumLocal;
                        break;
                    default: {
                        System.out.println("Berechung fehlerhaft 35");
                        System.exit(1);
                    }
                }
                break;
            }
            default: {
                System.out.println("Berechung fehlerhaft 36");
                System.exit(1);
                resault = 0;
            }

        }
        switch (this.sign) {
            case add:
                return resault;
            case subtract:
                return resault * -1;
            default: {
                System.out.println("Berechung Felerhaft 37");
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
                System.out.println("Berechung fehlerhaft");
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