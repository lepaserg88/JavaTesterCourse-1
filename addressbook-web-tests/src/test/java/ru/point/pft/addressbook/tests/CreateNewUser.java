package ru.point.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;

public class CreateNewUser extends TestBase{

  @Test
  public void testCreateNewUser() throws Exception {

    app.addNew();
    app.contactInformation(new ContactData("ФИО", "ФИО", "79899999999", "test@test.test"));
    app.submitContact();
    app.getNavigationHelper().clickHomePage();
  }

}
