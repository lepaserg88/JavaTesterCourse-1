package ru.point.pft.addressbook.tests;
import org.testng.annotations.Test;

public class GroupDeletionTests {
  @Test
  public void testGroupDeletion() throws Exception {
      double k = 0;
      for (int i = 0; i < 80000; i++) {
          for (int j = 0; j < 80000; j++) {
              k = (k + 1)*1.00000001;
          }
      }
      System.out.println(k);
          System.out.println("Test");}
  /*
  @Test
  public void testGroupDeletion() throws Exception {
    gotoGroupPage();
    selectGroup();
    deleteCreaatedGroups();
    returntoGroupPage();
  }*/
}