package ru.point.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class DeleteUser  extends TestBase{

  @Test
  public void testDeleteUser(){
    app.getUserHelper().selectUser();
    app.getUserHelper().deleteUser();
    app.getUserHelper().accept();
    //app.getNavigationHelper().clickHomePage();

  }

}
