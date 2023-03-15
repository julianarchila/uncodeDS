import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Robots {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    sc.close();
    Scanner sc2 = new Scanner(input);

    Stack<Integer> left_robots = new Stack<>();
    Queue<Integer> right_robots = new LinkedList<>();

    while (sc2.hasNextInt()){
      int robot = sc2.nextInt();
      if (robot > 0){
        left_robots.add(robot);
      } else {
        right_robots.add(robot);
      }
    }

    while (!left_robots.isEmpty() && !right_robots.isEmpty()){
      int left_robot = left_robots.peek();
      int right_robot = right_robots.peek();
      if (left_robot + right_robot == 0){
        left_robots.pop();
        right_robots.poll();
      } else if (left_robot + right_robot > 0){
        right_robots.poll();
      } else {
        left_robots.pop();
      }
    }

    if (left_robots.isEmpty() && right_robots.isEmpty()) {
      System.out.println("No quedan robots");
      return;
    }

    StringBuilder sb = new StringBuilder();
    if (!left_robots.isEmpty()){
      for (Integer robot : left_robots){
        sb.append(robot + " ");
      }
    } else {
        for (Integer robot : right_robots){
            sb.append(robot + " ");
        }
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.println(sb.toString());
    sc2.close();
  }
}
