import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchAlgorithms {

    public static void main (String[] args){
        List<Integer> list = new ArrayList();
        Random rand = new Random();


       System.out.println(getDigit(1234, 3));

    }

    public static void RadixSort(List<Integer> list){

        int maxDigits = getMaxDigitNumber(list);
        ArrayList<Integer>[] matrix = new ArrayList[10];

        for (int number : list){
            matrix[getDigit(number, 1)].add(number);
        }

        // i = digit que estem mirant
        // pos = posició de l'array de llistes
        for (int i = 2; i  <= maxDigits; i++){

            int[] size = new int[10];
            int j =  0;

            for (List<Integer> pos : matrix){
                size[j] = pos.size();
                j++;
            }

            j = 0;
            for (List<Integer> pos : matrix){
                if (size[j] != 0){
                    for (int h = 0; h < size[j]; h++){

                    }
                }
                j++;
            }
        }
    }

    public static int getMaxDigitNumber(List<Integer> list){
        int max = 0;

        for (int i : list){
            int digits = 0;
            int number = i;

            while (number > 0){
                number = number/10;
                digits++;
            }
            if (digits > max) max = digits;
        }

        return max;
    }

    public static int getDigit(int number, int pos){
        int digit = number % (int) Math.pow(10, pos);
        digit = digit / (int) Math.pow(10, pos - 1);
        return (digit);
    }
}
