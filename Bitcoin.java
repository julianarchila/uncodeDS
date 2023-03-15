import java.util.*;

public class Bitcoin {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    String[] stringPrices = input.split(" ");
    int[] prices = new int[stringPrices.length];
    for (int i = 0; i < stringPrices.length; i++) {
      prices[i] = Integer.parseInt(stringPrices[i]);
    }

    int[] result = new int[prices.length];

    Stack<Integer> stack = new Stack<>();
    for (int i = prices.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && prices[i] >= prices[stack.peek()]) {
        stack.pop();
      }
      result[i] = stack.isEmpty() ? 0 : stack.peek() - i;
      stack.push(i);
    }

    // use string builder
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < result.length; i++) {
      sb.append(result[i]);
      sb.append(" ");
    }
    sb.deleteCharAt(sb.length() - 1);
System.out.println(sb.toString());
    scanner.close();


  }
}
