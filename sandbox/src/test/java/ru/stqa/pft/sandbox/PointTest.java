package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 3);
        Assert.assertTrue(p1.distance(p2) - 2.23 < 0.01);
    }

    @Test
    public void testZeroDistance(){
        Point p1 = new Point(2, 2);
        Point p2 = new Point(2, 2);
        Assert.assertEquals(p1.distance(p2), 0.0);
    }

    @Test
    public void testNegativeCoordinates(){
        Point p1 = new Point(-1, -5);
        Point p2 = new Point(-5, -5);
        Assert.assertEquals(p1.distance(p2), 4.0);
    }
}
