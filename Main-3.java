public class Main {
   private static Node head = null;

    private static class Node {
        int data;
        Node next = null;

        Node(int data) {
            this.data = data;
        }
    }

    public static void prepend(int data) {
        Node nn = new Node(data);
        nn.next = head;
        head = nn;
    }

    public static void print(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    public static Node split(Node head) {
        Node slow = head;
        Node fast = head.next;

        if (head == null) {
            return head;
        }
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

     public static Node merge(Node head) {
        Node m = split(head);
        Node mNext = m.next;

        if (head == null || head.next == null) {
            return head;
        }
        m.next = null;
        Node a = merge(head);
        Node b = merge(mNext);
        return mergeSort(a, b);
    }

     public static Node mergeSort(Node a, Node b) {
        Node result ;

        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.data <= b.data) {
            result = a;
            result.next = mergeSort(a.next, b);
        }
        else {
            result = b;
            result.next = mergeSort(a, b.next);
        }
        return result;
    }

    public static void main(String[] args){
        prepend(5);
        prepend(4);
        prepend(33);
        prepend(2);
        prepend(1);
        print(head);
        System.out.println();
        head = merge(head);
        print(head);
    }
}
