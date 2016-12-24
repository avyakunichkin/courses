package ru.stqa.pft.sandbox;

public class Point {

    int a;
    int b;

    public Point(int a, int b){
        this.a = a;
        this.b = b;
    }

    public double distance(Point p){
        return Math.sqrt(Math.pow((p.a - this.a), 2) + Math.pow((p.b - this.b), 2));
    }
}
