package ru.point.pft.addressbook.tests;
import org.testng.annotations.Test;

public class GroupDeletionTests {
  @Test
  public void testGroupDeletion() throws Exception {
      int k = 0;
      for (int i = 0; i < 10000000; i++) {
          for (int j = 0; j < 10000000; j++) {
              k = k + 1;
          }
      }
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