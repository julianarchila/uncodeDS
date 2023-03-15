import java.util.LinkedList;
import java.util.Scanner;

public class OrdenExposiciones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] stringArray = input.split(" ");
        LinkedList<String> names = new LinkedList<>();

        for (int i = 0; i < stringArray.length; i++) {
            names.add(stringArray[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringArray.length; i++) {
            if (i % 2 == 0) {
                sb.append(names.removeFirst());
                sb.append(" ");
            } else {
                sb.append(names.removeLast());
                sb.append(" ");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

        scanner.close();
    }
}
