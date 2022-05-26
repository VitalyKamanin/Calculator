import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CalculatorException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        String result = calc(input);
        System.out.println(result);
    }

    public static String calc(String input) throws CalculatorException {
        String[] arabic_numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] roman_numbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] inputs = input.toUpperCase().split(" ");
        int expression_length = inputs.length;
        if (expression_length < 3) {throw new CalculatorException("Вы ввели слишком мало символов");}
        if (expression_length > 3) {throw new CalculatorException("Вы ввели слишком много символов");}
        String word1 = inputs[0];
        String word2 = inputs[2];
        if (inputs[1].length() != 1) {throw new CalculatorException("Введите только один арифметический оператор");}
        char operator = inputs[1].charAt(0);
        String preresult;
        if ((Arrays.asList(roman_numbers).contains(word1)) && (Arrays.asList(roman_numbers).contains(word2))) {
            int a = Integer.parseInt(arabic_numbers[Arrays.asList(roman_numbers).indexOf(word1)]);
            int b = Integer.parseInt(arabic_numbers[Arrays.asList(roman_numbers).indexOf(word2)]);
            preresult = switch (operator) {
                case '+' -> ConvertResult(a + b);
                case '-' -> ConvertResult(a - b);
                case '*' -> ConvertResult(a * b);
                case '/' -> ConvertResult(a / b);
                default -> throw new CalculatorException("Вы не указали арифметическую операцию");
            };
        }
        else if ((Arrays.asList(arabic_numbers).contains(word1)) && (Arrays.asList(arabic_numbers).contains(word2))) {
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[2]);
            preresult = switch (operator) {
                case '+' -> String.valueOf(a + b);
                case '-' -> String.valueOf(a - b);
                case '*' -> String.valueOf(a * b);
                case '/' -> String.valueOf(a / b);
                default -> throw new CalculatorException("Вы не указали арифметическую операцию");
            };
        }
        else if ((Arrays.asList(arabic_numbers).contains(word1)) && (Arrays.asList(roman_numbers).contains(word2)) ||
                (Arrays.asList(roman_numbers).contains(word1)) && (Arrays.asList(arabic_numbers).contains(word2))) {
            throw new CalculatorException("Вы ввели и римские и арабские числа");}
        else {throw new CalculatorException("калькулятор работает только с числами от 1 до 10 / I до X");}
        return preresult;
    }
    public static String ConvertResult(int roman_preresult) throws CalculatorException {
        StringBuilder roman_result = new StringBuilder();
        if (roman_preresult <= 0){
            throw new CalculatorException("Результат меньше или равен 0, нет римских чисел для отображения");}
        else{
            while (roman_preresult >= 100) {
                roman_result.append("C");
                roman_preresult -= 100;
            }
            while (roman_preresult >= 90) {
                roman_result.append("XC");
                roman_preresult -= 90;
            }
            while (roman_preresult >= 50) {
                roman_result.append("L");
                roman_preresult -= 50;
            }
            while (roman_preresult >= 40) {
                roman_result.append("XL");
                roman_preresult -= 40;
            }
            while (roman_preresult >= 10) {
                roman_result.append("X");
                roman_preresult -= 10;
            }
            while (roman_preresult >= 9) {
                roman_result.append("IX");
                roman_preresult -= 9;
            }
            while (roman_preresult >= 5) {
                roman_result.append("V");
                roman_preresult -= 5;
            }
            while (roman_preresult >= 4) {
                roman_result.append("IV");
                roman_preresult -= 4;
            }
            while (roman_preresult >= 1) {
                roman_result.append("I");
                roman_preresult -= 1;
            }
        }
        return roman_result.toString();
    }
}

class CalculatorException extends Exception {
    public CalculatorException(String message){super(message);}
}
