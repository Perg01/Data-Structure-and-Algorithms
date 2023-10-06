public class Main {
    public static void main(String[] args) {
        int[] arr = {0, 1, 4, 3, 7, 9};
        int len = arr.length;

        Radix.sort(arr,len);
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
        for (int i = arr.length -1; i >= 0; i--) System.out.print(arr[i] + " ");
    }
}

class Queue {
   private Node front, rear;
    int size;

     class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }
    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }
    public void enqueue(int data) {
        Node nn = new Node(data);

        if(front == null && rear == null) {
            front = rear = nn;
            return;
        }
        rear.next = nn;
        rear = nn;
        size++;
        return;
    }
    public int dequeue () {
        if (front == null) {
            throw new IllegalStateException("Can't dequeue, list is empty.");
        }
        int firstItem = front.data;
        if (front.next == null) {
            rear = null;
        }
        size--;
        return firstItem;
    }
    public boolean isEmpty() {
         return (front == null);
    }
    public int size() {
         return size;
    }
}

class Radix {
    static int[] qArr = new int[10];
    public static void sort(int[] arr, int len) {
        int i;
        int exp = 1;
        int buck[] = new int[10];
        for (i = 0; i < len; i++) {
            qArr[(arr[i] / exp) % 10]++;
        }
        for (i = 1; i < 10; i++) {
            qArr[i] += qArr[i - 1];
        }
        for (i = len - 1; i >= 0; i--) {
            buck[--qArr[(arr[i] / exp) % 10]] = arr[i];
        }
        for (i = 0; i < len; i++) {
            arr[i] = buck[i];
            exp = exp * 10;
        }
    }
}