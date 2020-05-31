package ru.point.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testCreateNewUser() {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().
            withFirstName("ФИО").withLastName("ФИО").withMobile("79899999999").withEmail("test@test.test").withGroup("test1");
    app.contact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    Comparator<? super ContactData> byId = (Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    int max = after.stream().max(byId).get().getId();
    contact.withId(max);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
