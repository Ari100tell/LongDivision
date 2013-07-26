package ua.kpi_java_training.morochenets.module1;

import java.util.Scanner;

/**
 * Class is designed for input two numbers from the keyboard and
 * division of integers in a column.
 *
 * @author Morochenets Olexiy
 * @version 1.00.05
 * @data 24.05.2013
 */

public class LongDivision {
    final int FUNDATION_DEGREE = 10;
    int dividendWithSign =0;
    int divisorWithSign = 0;
    int resultOfDivision = 0;

    public static void main(String[] args) {
        new LongDivision().runLongDivision();
    }

    /**
     * Runs long division
     */
    public void runLongDivision() {
        dividendWithSign = inputDividendAndDivisor(true);
        controlDivisionByZero();
        int resultOfDivision = (int) (dividendWithSign / divisorWithSign);
        int dividend = Math.abs(dividendWithSign);
        int divisor = Math.abs(divisorWithSign);
        outputDividendAndDivisor(dividendWithSign, divisorWithSign, resultOfDivision, countingNumberDigits(dividend));
        while (dividend >= divisor) {
            dividend = divisionInsideColumn(dividend, divisor);
        }
        dividend = controlModuloSign(resultOfDivision, dividend);
        outputModuloAndResult(dividend);
    }

    /**
     * Checks for modulo sign
     *
     * @param resultOfDivision The result of dividing
     * @param modulo           The modulo of dividing
     * @return
     */
    private int controlModuloSign(int resultOfDivision, int modulo) {
        if ((resultOfDivision < 0) && (dividendWithSign < 0)) {
            modulo = -modulo;
        }
        return modulo;
    }

    /**
     * Checks for division by zero
     */
    private void controlDivisionByZero() {
        while (divisorWithSign == 0) {
            divisorWithSign = inputDividendAndDivisor(false);
        }
    }

    /**
     * Performs intermediate calculations of data division, which are displayed in the column division
     *
     * @param dividend Number which needs to be dividend
     * @param divisor  Divider for division performance in a column
     * @return Dividend
     */

    public int divisionInsideColumn(int dividend, int divisor) {
        int moduloPartOfDividend = 0;
        int digitsForTrip = countingNumberDigits(dividend) - countingNumberDigits(divisor);
        int partOfDividend = trimmingEndOfIntegers(dividend, (digitsForTrip));
        if (partOfDividend >= divisor) {
            moduloPartOfDividend = (int) (partOfDividend / divisor) * divisor;
            dividend = dividend - moduloPartOfDividend * exponentiationWithBaseTen(digitsForTrip);
        } else {
                partOfDividend = trimmingEndOfIntegers(dividend, (digitsForTrip - 1));
                moduloPartOfDividend = (int) (partOfDividend / divisor) * divisor;
                dividend = (int) (dividend - (moduloPartOfDividend * exponentiationWithBaseTen(countingNumberDigits(dividend)
                        - (countingNumberDigits(partOfDividend)))));
        }
        outputDividingColumn(partOfDividend, moduloPartOfDividend);
        return dividend;
    }

    /**
     * Displays the specified character set number of times
     *
     * @param numberOfSymbol Quantity of symbols for a conclusion on the screen
     * @param typeOfSymbol   Type of symbol for a conclusion on the screen
     * @return Empty string
     */

    public String drawSymbol(int numberOfSymbol, char typeOfSymbol) {
        switch (typeOfSymbol) {
            case ' ': {
                for (int i = 1; i <= numberOfSymbol; i++) {
                    System.out.print(" ");
                }
                break;
            }
            case '-': {
                for (int i = 1; i <= numberOfSymbol; i++) {
                    System.out.print("-");
                }
                break;
            }
        }
        return "";
    }

    /**
     * Counting the number of digits specified number
     *
     * @param numberForCounting Number for calculation of digits
     * @return Number of figures for the set number
     */
    public int countingNumberDigits(int numberForCounting) {
        if (numberForCounting != 0) {
            return (int) Math.log10(Math.abs(numberForCounting)) + 1;
        } else {
            return 1;
        }
    }

    /**
     * Exponentiation of the specified power with base 10
     *
     * @param exponent exponent in which it is necessary to erect the set number
     * @return The result of the exponentiation of the specified power with base 10
     */

    public int exponentiationWithBaseTen(int exponent) {
        return (int) Math.pow(FUNDATION_DEGREE, exponent);
    }

    /**
     * Does cutting of the specified number on the set number of figures since the end
     *
     * @param integerForTrimming  The number for cutting of end
     * @param numberDigitsForTrim The number of digits to trim the specified number
     * @return Cropped number
     */
    public int trimmingEndOfIntegers(int integerForTrimming, int numberDigitsForTrim) {
        return (int) (integerForTrimming / (Math.pow(10, numberDigitsForTrim)));
    }

    /**
     * Reads the input data for long division
     *
     * @param inputDividendOrDivisor true, if has to the dividend is entered , and false, if has to the divisor is entered
     * @return Value of a dividend or divider depending on value of parameter inputDividendOrDivisor
     */
    public int inputDividendAndDivisor(boolean inputDividendOrDivisor) {
        int moduloPartOfDividend = 0;
        Scanner scanner = new Scanner(System.in);
        boolean errorInputDividendAndDivisor = false;
        do {
            try {
                if (inputDividendOrDivisor) {
                    System.out.println("Enter dividend");
                    inputDividendOrDivisor = false;
                } else {
                    System.out.println("Enter divisor(not 0)");
                    inputDividendOrDivisor = true;
                }
                moduloPartOfDividend = Integer.parseInt(scanner.nextLine());
                errorInputDividendAndDivisor = false;
            } catch (Exception error) {
                System.out.println("Error: Enter another number");
                errorInputDividendAndDivisor = true;
                if (inputDividendOrDivisor) {
                    inputDividendOrDivisor = false;
                }
            }
        } while (errorInputDividendAndDivisor);
        return moduloPartOfDividend;
    }

    /**
     * Carries out a conclusion of entry conditions for division in a column
     *
     * @param dividendWithSign The value of the dividend with sign
     * @param divisorWithSign  The value of the divisor with sign
     * @param result           The value of the divisor with sign
     * @param dividendDigits   The number of digits in a divider
     */

    public void outputDividendAndDivisor(int dividendWithSign, int divisorWithSign, int result, int dividendDigits) {
        System.out.println(dividendWithSign + "|" + divisorWithSign);
        drawSymbol(dividendDigits, ' ');
        System.out.print("|");
        drawSymbol(countingNumberDigits(divisorWithSign), '-');
        System.out.println();
        drawSymbol(dividendDigits, ' ');
        System.out.println("|" + result);
        drawSymbol(dividendDigits, ' ');
    }

    /**
     * Carries out a conclusion of a column of division
     *
     * @param partOfDividend       Part of the dividend that divides
     * @param partOfDividend       Part of the dividend that divides
     * @param moduloPartOfDividend The remainder after dividing  from part of the dividend
     */
    public void outputDividingColumn(int partOfDividend, int moduloPartOfDividend) {
        System.out.println();
        System.out.println(partOfDividend);
        System.out.println(moduloPartOfDividend);
        drawSymbol(countingNumberDigits(partOfDividend), '-');
    }

    /**
     * Carries out a result and remainder of division conclusion
     *
     * @param modulo The remainder of the long division
     */
    public void outputModuloAndResult(int modulo) {
        System.out.println();
        System.out.println(modulo);
        System.out.println();
        System.out.println("Result:" + resultOfDivision);
        System.out.println("Modulo:" + modulo);
    }
}