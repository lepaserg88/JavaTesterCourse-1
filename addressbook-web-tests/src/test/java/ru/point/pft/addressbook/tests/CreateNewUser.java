package ru.point.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class CreateNewUser extends TestBase{

  @Test
  public void testCreateNewUser() {
    List<ContactData> before = app.getUserHelper().getUserList();
    ContactData contact = new ContactData("ФИО", "ФИО", "79899999999", "test@test.test", "test1");
    app.getUserHelper().createUser(contact, true);
    app.getNavigationHelper().clickHomePage();
    List<ContactData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);

    Comparator<? super ContactData> byId = (Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    int max = after.stream().max(byId).get().getId();
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
