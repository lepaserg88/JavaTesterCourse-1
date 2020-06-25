package ru.point.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.GroupData;

public class GroupModificationTests  {
  @Test
  public void testGroupModification() {
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