package ru.point.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;

public class DeleteUser  extends TestBase{

  @Test
  public void testDeleteUser(){
    if (! app.getUserHelper().isThereAUser()){
    app.getUserHelper().createUser(new ContactData("ФИО", "ФИО", "79899999999", "test@test.test", "test1"), true);
    app.getUserHelper().returnToHomePage();
    }
    int before = app.getUserHelper().getUserCount();
    app.getUserHelper().selectUser();
    app.getUserHelper().deleteUser();
    app.getUserHelper().accept();
    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after, before - 1);
  }

}
