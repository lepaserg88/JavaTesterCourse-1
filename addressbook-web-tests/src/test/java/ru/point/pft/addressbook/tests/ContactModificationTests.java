package ru.point.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;
import ru.point.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditionsContact() {
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().
              withFirstName("ФИО").withLastName("ФИО").withMobile("79899999999").withEmail("test@test.test").withGroup("test1"), true);
    }
    app.contact().returnToHomePage();
  }

  @Test
  public void testUserModification(){
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).withFirstName("Иван").withLastName("Иванович").withMobile("79899999988").withEmail("test@test.test");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
