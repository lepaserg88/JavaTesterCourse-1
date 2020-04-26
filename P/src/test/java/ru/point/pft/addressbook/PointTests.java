package ru.point.pft.addressbook;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(1,4);
    Point p2 = new Point(4,8);
    //assert p1.distance(p2) == 5;
    Assert.assertEquals(p1.distance(p2), 5);
  }
}