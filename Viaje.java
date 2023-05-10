import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Viaje {

  // private static String[] ciudades = { "Mongui", "Sachica", "Tinjaca",
  // "Combita", "Chiquiza", "Sutamarchan", "Tibasosa",
  // "Toca", "Guican", "Chivata", "Topaga", "Soraca", "Gameza", "Guayata",
  // "Raquira", "Nobsa", "Tenza", "Aquitania" };

  private static String[] ciudades = { "a", "b", "c", "d", "e", "f", "g", "h", "i" };

  public static void main(String[] args) {
    AVLTree tree = new AVLTree();
    for (int i = 0; i < ciudades.length; i++) {
      tree.insert(ciudades[i]);
    }

    Scanner sc = new Scanner(System.in);

    String input = sc.nextLine();

    String pueblo1 = input.split(" ")[0];
    String pueblo2 = input.split(" ")[1];

    // find the distance between the two cities, including the cities themselves,
    // print the int value

    // cities will always be in the tree and will always be different

    // find the common ancestor of the two cities, then calculate the distance from
    // the ancestor to each city and add them together

    // common ancestor

    // create two list containing the path from the root to each city
    // when the paths diverge, the last common node is the common ancestor

    // pahts

    List<Integer> path1 = tree.binaryPathTo(pueblo1);
    List<Integer> path2 = tree.binaryPathTo(pueblo2);

    int commonAncestor = 0;

    for (int i = 0; i < Math.min(path1.size(), path2.size()); i++) {
      if (path1.get(i) == path2.get(i)) {
        commonAncestor++;
      } else {
        break;
      }
    }

    int distance = path1.size() + path2.size() - 2 * commonAncestor - 1;
    System.out.println(distance);

    sc.close();
    return;
  }

}

class AVLTree {
  private Node root;

  private class Node {
    String value;
    Node left, right;
    int height;

    Node(String value) {
      this.value = value;
      this.height = 1;
    }
  }

  public void insert(String value) {
    root = insert(root, value);
  }

  private Node insert(Node node, String value) {
    if (node == null) {
      return new Node(value);
    }

    if (value.compareTo(node.value) < 0) {
      node.left = insert(node.left, value);
    } else if (value.compareTo(node.value) > 0) {
      node.right = insert(node.right, value);
    } else {
      return node;
    }

    node.height = 1 + Math.max(height(node.left), height(node.right));

    int balance = getBalance(node);

    if (balance > 1 && value.compareTo(node.left.value) < 0) {
      return rightRotate(node);
    }

    if (balance < -1 && value.compareTo(node.right.value) > 0) {
      return leftRotate(node);
    }

    if (balance > 1 && value.compareTo(node.left.value) > 0) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }

    if (balance < -1 && value.compareTo(node.right.value) < 0) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    return node;
  }

  private Node rightRotate(Node y) {
    Node x = y.left;
    Node T2 = x.right;

    x.right = y;
    y.left = T2;

    y.height = Math.max(height(y.left), height(y.right)) + 1;
    x.height = Math.max(height(x.left), height(x.right)) + 1;

    return x;
  }

  private Node leftRotate(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    y.left = x;
    x.right = T2;

    x.height = Math.max(height(x.left), height(x.right)) + 1;
    y.height = Math.max(height(y.left), height(y.right)) + 1;

    return y;
  }

  private int height(Node node) {
    if (node == null) {
      return 0;
    }
    return node.height;
  }

  private int getBalance(Node node) {
    if (node == null) {
      return 0;
    }
    return height(node.left) - height(node.right);
  }

  public void inOrder() {
    inOrder(root);
    System.out.println();
  }

  private void inOrder(Node node) {
    if (node != null) {
      inOrder(node.left);
      System.out.print(node.value + " ");
      inOrder(node.right);
    }
  }

  public List<Integer> binaryPathTo(String value) {
    List<Integer> path = new ArrayList<>();
    // if it goes left, add -1, if it goes right, add 1 if it finds the value add 0
    Node current = root;
    while (current != null) {
      if (value.compareTo(current.value) < 0) {
        path.add(-1);
        current = current.left;
      } else if (value.compareTo(current.value) > 0) {
        path.add(1);
        current = current.right;
      } else {
        path.add(0);
        break;
      }
    }
    return path;
  }

}