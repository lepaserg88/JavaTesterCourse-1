package ru.point.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;

public class CreateNewUser extends TestBase{

  @Test
  public void testCreateNewUser() {
    int before = app.getUserHelper().getUserCount();
    app.getUserHelper().createUser(new ContactData("ФИО", "ФИО", "79899999999", "test@test.test", "test1"), true);
    app.getNavigationHelper().clickHomePage();
    int after = app.getUserHelper().getUserCount();
    Assert.assertEquals(after, before + 1);
  }

}
