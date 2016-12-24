package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	
	public static void main(String[] args) {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(3, 3);
		System.out.println("Расстояние между точками: " + Math.sqrt(Math.pow((p2.a - p1.a), 2) + Math.pow((p2.b - p1.b), 2)));
	}
}