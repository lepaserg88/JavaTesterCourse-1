package ru.point.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;
import ru.point.pft.addressbook.model.GroupData;

public class UserModification extends TestBase{

  @Test
  public void testUserModification(){
    if (! app.getUserHelper().isThereAUser()){
    app.getUserHelper().createUser(new ContactData("ФИО", "ФИО", "79899999999", "test@test.test", "test1"), true);
    }
    app.getUserHelper().returnToHomePage();
    int before = app.getUserHelper().getUserCount();
    app.getUserHelper().redactUser();
    app.getUserHelper().contactInformation(new ContactData("Иван", "Иванович", "79899999988", "test@test.test", null), false);
    app.getUserHelper().updateUser();
    app.getUserHelper().returnToHomePage();
    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after, before);

  }

}
