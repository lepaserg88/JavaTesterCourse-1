package ru.point.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;

public class CreateNewUser extends TestBase{

  @Test
  public void testCreateNewUser() {

    app.getUserHelper().addNew();
    app.getUserHelper().contactInformation(new ContactData("ФИО", "ФИО", "79899999999", "test@test.test", "test1"), true);
    app.getUserHelper().submitContact();
    app.getNavigationHelper().clickHomePage();
  }

}
