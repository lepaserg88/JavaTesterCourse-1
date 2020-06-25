package ru.point.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.GroupData;

public class GroupModificationTests  {
  @Test
  public void testGroupModification() {
    double k = 0;
    for (int i = 0; i < 70000; i++) {
      for (int j = 0; j < 70000; j++) {
        k = (k + 1)*1.00000001;
      }
    }
    System.out.println("Test");
  }
  //@Test
  /*
  public void testGroupModification() {
    gotoGroupPage();
    selectGroup();
    initGroupModification();
    fillGroupForm(new GroupData("test99", "test88", "test77"));
    submitGroupModification();
    returntoGroupPage();
  }


  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }*/
}