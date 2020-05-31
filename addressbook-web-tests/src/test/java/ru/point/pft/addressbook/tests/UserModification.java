package ru.point.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class UserModification extends TestBase{

  @Test
  public void testUserModification(){
    if (! app.getUserHelper().isThereAUser()){
    app.getUserHelper().createUser(new ContactData("ФИО", "ФИО", "79899999999", "test@test.test", "test1"), true);
    }
    app.getUserHelper().returnToHomePage();
    List<ContactData> before = app.getUserHelper().getUserList();
    app.getUserHelper().redactUser(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Иван", "Иванович", "79899999988", "test@test.test", null);
    app.getUserHelper().contactInformation(contact, false);
    app.getUserHelper().updateUser();
    app.getUserHelper().returnToHomePage();
    List<ContactData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
