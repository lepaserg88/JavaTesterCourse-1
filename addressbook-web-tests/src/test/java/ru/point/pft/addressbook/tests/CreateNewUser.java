package ru.point.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;
import ru.point.pft.addressbook.model.GroupData;

import java.util.List;

public class CreateNewUser extends TestBase{

  @Test
  public void testCreateNewUser() {
    List<ContactData> before = app.getUserHelper().getUserList();
    app.getUserHelper().createUser(new ContactData("ФИО", "ФИО", "79899999999", "test@test.test", "test1"), true);
    app.getNavigationHelper().clickHomePage();
    List<ContactData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
