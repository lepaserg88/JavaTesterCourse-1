package ru.point.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;
import ru.point.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testCreateNewUser() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().
            withFirstName("ФИО").withLastName("ФИО").withMobile("79899999999").withEmail("test@test.test").withGroup("test1").
            withHomePhone("111").withWorkPhone("333").withAddress("Пермь").
            withEmail2("test2@test.test").withEmail3("test3@test.test");
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
