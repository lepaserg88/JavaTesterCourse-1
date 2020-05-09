package ru.point.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;
import ru.point.pft.addressbook.model.GroupData;

public class DeleteUser  extends TestBase{

  @Test
  public void testDeleteUser(){
    if (! app.getUserHelper().isThereAUser()){
      app.getUserHelper().createUser(new ContactData("ФИО", "ФИО", "79899999999", "test@test.test", "test1"), true);
    }
    app.getUserHelper().selectUser();
    app.getUserHelper().deleteUser();
    app.getUserHelper().accept();
  }

}
