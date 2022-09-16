import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchAlgorithms {
    public static void main(String [] args) {
		List<Integer> list = new ArrayList<Integer>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++){
		    list.add(rand.nextInt(10));
		}
	//Cerca lineal a llista desordenada
	int i=cercaLineal(list,9);
	comprovar(i);
	//Cerca lineal a llista desordenada
	i= cercaLineal(list,9);
	comprovar(i);
	//Cerca binaria a llista desordenada
	i= cercaBinaria(list,9);
	comprovar(i);

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
}
