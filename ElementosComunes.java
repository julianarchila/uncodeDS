import java.util.LinkedList;
import java.util.Scanner;

public class ElementosComunes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String input = scanner.nextLine();
        String[] stringArray = input.split(" ");
        int size = stringArray.length;
        LinkedList<Integer> list1 = new LinkedList<>();

        String input2 = scanner.nextLine();
        String[] stringArray2 = input2.split(" ");
        LinkedList<Integer> list2 = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            list1.add(Integer.parseInt(stringArray[i]));
            list2.add(Integer.parseInt(stringArray2[i]));
        }

        for (int i = 0; i < size; i++){
            if (list1.getFirst() == list2.getLast()){
                list1.removeFirst();
                list2.removeLast();
            } else {
                list1.addLast(list1.removeFirst());
                list2.addFirst(list2.removeLast());
            }
        }

        int list1Sum = 0;
        int list2Sum = 0;

        for (int i = list1.size() - 1; i >= 0; i--){
            list1Sum += list1.remove();
            list2Sum += list2.remove();
        }
        System.out.println(list1Sum);
        System.out.println(list2Sum);

        scanner.close();

    }
}
