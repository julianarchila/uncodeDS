import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Islas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String[] arr = input.split(" ");

        Integer[] intArr = new Integer[arr.length];

        for (int i = 0; i < arr.length; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
        }

        int a = sc.nextInt();

        Tree tree = new Tree();
        tree.createTree(intArr);

        List<Integer> pre = tree.preorder();
        List<Integer> post = tree.postorder();
        List<Integer> in = tree.inorder();

        System.out.println("Preorder: " + pre);

        System.out.println("Inorder: " + in);
        System.out.println("Postorder: " + post);

        int preSum = 0, postSum = 0, inSum = 0;

        for (int i = 0; i < a; i++) {
            preSum += pre.get(i);
            postSum += post.get(i);
            inSum += in.get(i);
        }

        if (preSum >= postSum && preSum >= inSum) {
            System.out.println("Preorder " + preSum);
        } else if (inSum >= preSum && inSum >= postSum) {
            System.out.println("Inorder " + inSum);
        } else {
            System.out.println("Postorder " + postSum);
        }

        sc.close();

    }

}

class Tree {

    private class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            this.left = this.right = null;
        }
    }

    private Node root;
    Queue<Node> queue = new LinkedList<>();

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
            queue.add(root);
            return;
        }

        Node node = queue.peek();
        if (node.left == null) {
            node.left = new Node(value);
            queue.add(node.left);
        } else if (node.right == null) {
            node.right = new Node(value);
            queue.add(node.right);
            queue.poll();
        }
    }

    void createTree(Integer[] arr) {
        for (Integer s : arr) {
            insert(s);
        }
    }

    List<Integer> preorder() {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(Node node, List<Integer> list) {
        if (node != null) {
            if (node.value != -1)
                list.add(node.value);
            preorder(node.left, list);
            preorder(node.right, list);
        }
    }

    List<Integer> postorder() {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(Node node, List<Integer> list) {
        if (node != null) {
            postorder(node.left, list);
            postorder(node.right, list);
            if (node.value != -1)
                list.add(node.value);
        }
    }

    List<Integer> inorder() {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(Node node, List<Integer> list) {
        if (node != null) {
            inorder(node.left, list);
            if (node.value != -1)
                list.add(node.value);
            inorder(node.right, list);
        }
    }

}