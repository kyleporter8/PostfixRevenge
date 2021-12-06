//package chapter_10;

import java.util.Scanner;

public class BinaryHeap {
	static int[] heap;
	static int size = 0;
	static int maxSize = 100;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n;
		
		heap = new int[2*maxSize];
		
		do {
			System.out.print("Next number? (0 to delete min, -1 to quit) ");
			n = in.nextInt();
			
			if (n > 0) 
				add(n);
			else if (n == 0)
				remove();

			printHeap();

		} while (n >= 0);
		

		in.close();
	}

	private static void add(int n) {
		heap[size++] = n;
		int p = size - 1;
		while (p > 0) {
			if (heap[parent(p)] > heap[p]) {
				int temp = heap[parent(p)];
				heap[parent(p)] = heap[p];
				heap[p] = temp;
			}
			p = parent(p);
		}
	}
	
	private static void remove() {
		heap[0] = heap[--size];
		int p = 0;
		
		while (l_child(p) < size) {
			int small = l_child(p);
			if (r_child(p) < size && heap[r_child(p)] < heap[small])
					small = r_child(p);
			
			if (heap[small] < heap[p]) {
				int temp = heap[p];
				heap[p] = heap[small];
				heap[small] = temp;				
			}
			p = small;
		}
	}
	
	private static int parent(int n) {
		return (n-1)/2;
	}

	private static int l_child(int n) {
		return 2*n+1;
	}

	private static int r_child(int n) {
		return 2*n+2;
	}

	private static void printHeap() {
		for (int i = 0; i < size; i++)
			System.out.print(heap[i] + " ");
		System.out.println();
	}
}
