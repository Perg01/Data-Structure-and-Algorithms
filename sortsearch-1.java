import java.util.Scanner;
class sortsearch {
	private static int r = 5;
	private static int c = 4;
	private static int arr[][] = {
			 { 5, 3, 2, 16 },
		        { 9, 8, 10, 17 },
		        { 4, 7, 11, 18 },
		        { 2, 5, 9, 12 },
		        { 7, 9, 4, 10 },
		        };
	
	public static void main(String[] args) {
		int arrTwo[][] = new int[5][4];
		Scanner sc = new Scanner(System.in);
		
		reset(arrTwo);
		System.out.println("Original Array: ");
		display(arr);
		System.out.println("Bubble Sort: ");
		bubble(arrTwo,0);
		display(arrTwo);
		reset(arrTwo);
		System.out.println("Selection Sort: ");
		selection(arrTwo,1);
		display(arrTwo);
		reset(arrTwo);
		System.out.println("Shell Sort: ");
		shell(arrTwo, 2);
		display(arrTwo);
		reset(arrTwo);
		System.out.println("Insertion Sort: ");
		insertion(arrTwo,4);
		display(arrTwo);
//		reset(arrTwo);
		
		int val = sc.nextInt();
		System.out.println("What number are you searching for in the 5th row?" + val);
		int index = binarySearch(arrTwo, val);
		if (index != -1) {
			System.out.println("The value " + val + " was found in the 5th row.");
			for (int i = 0; i < r; i++) {
				System.out.println(arrTwo[i][index]);
			}
		}
		else {
			System.out.println(val + " not found.");
		}
	}
	
	static void bubble(int arr[][],int col) {
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < (r - i - 1); j++) {
				if (arr[j][col] > arr[j+1][col]) {
					swap(arr, j, j+1);
				}
			}
		}
	}
	
	static void selection(int arr[][], int col) {
		for (int i = 0; i < r-1; i++) {
			int maxInd = i;
			for (int j = i + 1; j < r; j++) {
				if (arr[j][col] > arr[maxInd][col]) {
					maxInd = j;
				}
			}
			swap(arr, i, maxInd);
		}
	}
	
	static void shell(int arr[][], int col) {
		int half = r / 2;
		
		while(half > 0) {
			int num;
			for(int i = 0; i < r; i ++) {
				int temp = arr[i][col];
				num = i;
				while (num >= half && arr[i - half][col] > temp) {
					arr[i][col] = arr[i - half][col];
					num = num - half;
				}
				arr[i][col] = temp;
			}
			half = half / 2;
		}
	}
	
	static void insertion(int arr[][], int row) {
		for (int i = 0; i < c; i++) {
			int temp[] = new int[r];
			for (int j = 0; j < r; j++) {
				temp[j] = arr[j][i];
			}
			int x = i - 1;
			while (x >= 0 && arr[row][x] > temp[row]) {
				for (int j = 0; j < r; j++) {
					arr[j][x+1] = arr[j][x];
				}
				x--; //
			}
			for(int j = 0; j < r; j++) {
				arr[j][x+1] = temp[j];
			}
		}
	}
	
	static int binarySearch(int arr[][], int value) {
		int high = c - 1;
		int low = 0;
		
		while (low <= high) {
			int midIndex = low + ((high - low) / 2);
			if (arr[4][midIndex] == value) {
				return midIndex;
			}
			if (arr[4][midIndex] < value) {
				low = midIndex + 1;
			}
			else {
				high = midIndex + 1;
			}
		}
		return -1;
	}
	
	static void display(int arr[][]) {
		for (int i = 0; i < r; i++) {
			System.out.print("[");
			for (int j = 0; j < c; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.print("]");
			System.out.println();
		}
	}
	
	static void reset(int arrTwo[][]) {
		for (int i =0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				arrTwo[i][j] = arr[i][j];
			}
		}
	}
	
	static void swap(int arr[][], int rOne,int rTwo) {
		for (int i = 0; i < c; i++) {
			int tempR = arr[rOne][i];
			arr[rOne][i] = arr[rTwo][i];
			arr[rTwo][i] = tempR;
		}
	}
	
}
