package ru.point.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;

public class UserModification extends TestBase{

  @Test
  public void testUserModification(){

    app.getUserHelper().redactUser();
    app.getUserHelper().contactInformation(new ContactData("Иван", "Иванович", "79899999988", "test@test.test", null), false);
    app.getUserHelper().updateUser();

  }

}
