import java.util.*;

class Node<T> {
  T data;
  Node<T> next;

  public Node(T data) {
    this.data = data;
  }
}

class Stack<T> {
  private Node<T> head;
  private int size;

  public Stack() {
    head = null;
    size = 0;
  }

  public int size() {
    return size;
  }

  public void push(T data) {
    Node<T> nn = new Node(data);
    nn.next = head;
    head = nn;
    size++;
  }

  public T pop() {

    if (head == null) {
      System.out.println("Stack is Empty.");
    }
    Node<T> tem = head;
    T tem2;
    head = head.next;
    tem2 = tem.data;
    size--;
    return tem2;
  }

  public T top() {
    if (head == null) {
      System.out.println("Stack is Empty.");
    }
    T tem = head.data;
    return tem;
  }

  public boolean isEmpty() {
    return (size == 0);
  }
}

class Queue<T> {
  private Node<T> front;
  private Node<T> back;
  private int size;

  public Queue() {
    front = null;
    back = null;
    size = 0;
  }

  public void enqueue(T data) {
    Node<T> nn = new Node(data);
    size++;
    if (front == null && back == null) {
      front = nn;
      back = nn;
      return;
    }
    back.next = nn;
    back = nn;
    return;
  }

  public T dequeue() {

    if (front == null) {
      System.out.println("Queue is empty.");
    }
    T tem = front.data;
    if (front.next == null) {
      back = null;
    }
    front = front.next;
    size--;
    return tem;
  }

  public T front() {
    if (front == null) {
      System.out.println("Queue is empty.");
    }
    T tem = front.data;
    return tem;
  }

  public boolean isEmpty() {
    return (size == 0);
  }

  public int size() {
    return size;
  }
}

class PostFix {
  public static int Prec(char c) {
    switch(c) {
      case '+':
      return 1;
      case '-':
      return 1;
      case '*':
      return 2;
      case '/':
      return 2;
    }
    return -1;
  }

  private static boolean opCheck(char c) {
    if(c == '*' || c == '+' || c == '-' || c == '/') {
      return true;
    }
    else
      return false;
  }

  public static String infixToPostfix(String e) {
    Stack<Character> stack = new Stack();
    Queue<Character> queue = new Queue();
    String str = "";
    for(int i = 0; i < e.length(); i ++) {
      char cha = e.charAt(i);
      if (Character.isLetterOrDigit(cha)) {
        queue.enqueue(cha);
      }
      else if (cha == '(') {
        stack.push(cha);
      }
      else if (cha == ')') {
        while (!stack.isEmpty() && stack.top() != '(') {
          queue.enqueue(stack.pop());
        }  
        if (!stack.isEmpty() && stack.top() != '(') {
          return "Invalid expression";
          } else {
            stack.pop();
          }
        
      }
      else {
        while (!stack.isEmpty() && Prec(cha) <= Prec(stack.top())) {
          if (stack.top() == '(') {
            return  "Invalid expression";
          }
          queue.enqueue(stack.pop());
        }
        stack.push(cha);
      }

    } 
    while (!stack.isEmpty()) {
      if (stack.top() == '(') {
        return "Invalid expression";
      }
      queue.enqueue(stack.pop());
    }
    while (!queue.isEmpty()) {
      str = str + queue.dequeue();
    }
    return str;
  }

  static int solution(int x, int y, char oper) {
    int answer;
    if (oper == '+') {
      answer = x + y;
    }
    else if (oper == '-') {
      answer = x - y;
    }
    else if (oper == '*') {
      answer = x * y;
    }
    else {
      answer = x / y;
    }
    return answer;
  }

  public static int evaluation(String e) {
    Stack<Integer> stack2 = new Stack();

    for (int i = 0; i < e.length(); i++) {
      char c = e.charAt(i);
      if(!opCheck(c)) {
        int number = c - '0';
        stack2.push(number);
      }
      else {
        if (stack2.size() > 1) {
          int x = stack2.pop();
          int y = stack2.pop();
          int str = solution(x, y, c);
          stack2.push(str);
        }
      }
    }
    if (!stack2.isEmpty()) {
      return stack2.pop();
    }
    else 
      return Integer.MIN_VALUE;
  }
}

class Main {
  public static void main(String[] args) {
    System.out.println("Enter an expression:");
    Scanner scan = new Scanner(System.in);
    String e = scan.nextLine();
    String conv = PostFix.infixToPostfix(e);
    int str = PostFix.evaluation(conv);
    
    if (str != Integer.MIN_VALUE) {
      System.out.println(str);
    }
    else 
      System.out.println("enter expression again:");
    scan.close();
  }

}