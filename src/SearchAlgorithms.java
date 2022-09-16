import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementation and evaluation of different ordering and search algorithms
 *
 * @author Mireia Gasco Agorreta
 * @author Aina Ponseti Busquets
 *
 * @version 1.0
 * @since 16/09/2022
 */

public class SearchAlgorithms {

    public static void main (String[] args){
        List<Integer> list = new ArrayList();
        Random rand = new Random();

        for (int i = 0; i < 10; i++){
            list.add(rand.nextInt(1000));
        }

        List<Integer> oringialList = list;

        System.out.println("Unordered list");
        System.out.println(list);

        System.out.println("Ordered by RadixSort:");
        RadixSort(list);
        System.out.println(list);

        System.out.println("Ordered by SelectionSort:");
        list = oringialList;
        SelectionSort(list);
        System.out.println(list);

        System.out.println("Ordered by InsertionSort:");
        list = oringialList;
        InsertionSort(list);
        System.out.println(list);

        System.out.println("Ordered by BubbleSort:");
        list = oringialList;
        BubbleSort(list);
        System.out.println(list);

        System.out.println("Llista ordenada by MergeSort:");
        list = oringialList;
        MergeSort(list);
        System.out.println(list);
        
        //Cerca lineal a llista desordenada
        list = originalList;
        int i=cercaLineal(list,9);
        comprovar(i);
        //Cerca lineal a llista desordenada
        i= cercaLineal(list,9);
        comprovar(i);
        //Cerca binaria a llista desordenada
        i= cercaBinaria(list,9);
        comprovar(i);


    }

    /**
     * Implementation of the Selection Sort Algorithm
     * @param list - Integer list (arrayList) to be ordered
     */
    public static void SelectionSort(List<Integer> list){
        for (int i = 0; i < list.size() - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < list.size(); j++){
                if (list.get(j) < list.get(index)){
                    index = j;//searching for lowest index
                }
            }
            int smallerNumber = list.get(index);
            list.set(index,list.get(i));
            list.set(i,smallerNumber);
        }
    }

    /**
     * Implementation of the Insertion Sort Algorithm
     * @param list - Integer list (arrayList) to be ordered
     */
    public static void InsertionSort(List<Integer> list){
        int i, key, j;
        for (i = 1; i < list.size(); i++)
        {
            key = list.get(i);
            j = i - 1;

            while (j >= 0 && list.get(j) > key)
            {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }

    /**
     * Implementation of the Bubble Sort Algorithm
     * @param list - Integer list (arrayList) to be ordered
     */
    public static void BubbleSort(List<Integer> list) {
        int n = list.size();
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (list.get(j - 1) > list.get(j)) {
                    temp = list.get(j - 1);
                    list.set(j - 1, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    /**
     * Implementation of the Merge Sort Algorithm
     * @param list - Integer list (arrayList) to be ordered
     */
    public static void MergeSort(List<Integer> list) {

        //if the list only has one position, we do not need to order it
        if (list.size() < 2) {
            return;
        }
        int mid = list.size() / 2;  //index of the element in the middle of the list
        List<Integer> l = new ArrayList<>(list.subList(0, list.size() / 2));
        List<Integer> r = new ArrayList<>(list.subList((list.size() / 2), list.size()));


        MergeSort(l);
        MergeSort(r);

        merge(list, l, r, mid, list.size() - mid);
    }

    /**
     * Merge method for the merge sort.  Combines two ordered lists to form a new ordered list
     * @param a - list
     * @param l - left side of the list
     * @param r - right side of the list
     * @param left - left end of the list
     * @param right - right end of the list
     */
    public static void merge(List<Integer> a, List<Integer> l, List<Integer> r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l.get(i) <= r.get(j)) {
                a.set(k++, l.get(i++));
            }
            else {
                a.set(k++, r.get(j++));
            }
        }
        while (i < left) {
            a.set(k++, l.get(i++));
        }
        while (j < right) {
            a.set(k++, r.get(j++));
        }
    }

    /**
     * Implementation of the Radix Sort Algorithm
     * @param list - Integer list (arrayList) to be ordered
     */
    public static void RadixSort(List<Integer> list){

        int maxDigits = getMaxDigitNumber(list);            // number of digits of the longest number
        ArrayList<Integer>[] listArray = new ArrayList[10]; // array of 10 lists (values from 0 to 9)

        for (int i = 0; i < 10; i++){
            listArray[i] = new ArrayList<Integer>();
        }

        // the first iteration fills the listArray with the numbers
        // each number is placed in the corresponding list according to its less significant digit
        for (int number : list){
            listArray[getDigit(number, 1)].add(number);
        }

        // the following iterations read the numbers in order
        // each number is replaced according to each digit
        // d -> digit
        for (int d = 2; d  <= maxDigits; d++){

            // before the listArray is modified, we calculate how many numbers
            // there are in each list
            int[] size = new int[10];
            int i =  0; //list index

            for (List<Integer> ilist : listArray){  // ilist = list in the i position of the listArray
                size[i] = ilist.size();
                i++;
            }

            //read the lists in order and replace the numbers
            i = 0;
            for (List<Integer> ilist : listArray){
                for (int in = 0; in < size[i]; size[i]--){  // ni -> index of the number we are treating
                    listArray[getDigit(ilist.get(in), d)].add(ilist.get(in)); //place the number in its position
                    ilist.remove(0);    //remove the number from the initial position
                }
                i++;
            }
        }

        list.clear();
        for (int i = 0; i < 10; i++){
            list.addAll(listArray[i]);
        }

    }

    /**
     * Method that calculates the number of digits from the largest number in a list
     * @param list - Integer arrayList
     * @return an integer indicating the maximum number of digits
     */
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

    /**
     * Method that obtains the digit in a specific position
     * @param number - number from which we want to obtain the digit
     * @param pos - position of the digit we want to obtain
     * @return the digit in the position indicated by pos
     */
    public static int getDigit(int number, int pos){
        int digit = number % (int) Math.pow(10, pos);
        digit = digit / (int) Math.pow(10, pos - 1);
        return (digit);

    }
    
    
     public static int cercaLineal(List<Integer> llist, int x)
    {
	for (int i = 0; i < llist.size(); i++) {
	    if (llist.get(i) == x)
		return i;
	}
	return -1;
    }

    public static int cercaBinaria(List<Integer> llist, int x)
    {
	int indMenor=0;
	int indMajor=llist.size()-1;
	while (indMenor <= indMajor) {
	    int indMitja = indMenor + (indMajor - indMenor) / 2;

	    if (llist.get(indMitja) == x)
		return indMitja;

	    if (llist.get(indMitja) < x)
		indMenor = indMitja + 1;

	    else
		indMajor = indMitja - 1;
	}

	return -1;
    }

    public static void comprovar(int n) {
	if(n !=-1) {
		System.out.println("S'ha trobat; Posicio :" + n);
	}
	else
		System.out.println("No s'ha trobat el nombre");
}

