package Number;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberHelper {

    public static void main(String[] args) {
        getTickets(10, 5);
//        int[] numbers = getArrayNumbers(5);
//        System.out.println(Arrays.toString(numbers));
    }

    public static int getNumber() {
        var faker = new Faker();
        return faker.number().numberBetween(1, 37);
    }

    public static int[] getArrayNumbers(int length) {
        boolean success = false;
        int[] numbers = new int[length];
        do {
            for (int i = 0; i < length; i++) {
                numbers[i] = getNumber();
            }
            int[] sortNumbers = Arrays.copyOf(numbers, numbers.length);
            Arrays.sort(sortNumbers);
            for (int i = 0; i < length-1; i++) {
                if (sortNumbers[i] == sortNumbers[i+1]) {
                    i = length;
                    success = false;
                } else {
                    success = true;
                }
            }
        } while ( !success );
        return numbers;
    }

    public static ArrayList<int[]> getTickets(int count, int length) {
        ArrayList<int[]> loto = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int[] ticket = getArrayNumbers(length);
            if (i == 0) {
                loto.add(ticket);
            } else {
                if (checkTickets(loto, ticket, i)) {
                    loto.add(ticket);
                } else {
                    i = i - 1;
                }
            }
        }
        return loto;
    }

    public static boolean checkTickets(ArrayList<int[]> loto, int[] ticket, int length) {
        boolean success = true;
        int total = Arrays.stream(ticket).sum();
        for (int j = 0; j < length; j++) {
            int oldTotal = Arrays.stream(loto.get(j)).sum();
            if (total == oldTotal) {
                success = false;
                j = length;
            }
        }
        return success;
    }
}
