package ru.point.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditionsContact() {
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().
              withFirstName("ФИО").withLastName("ФИО").withMobile("79899999999").withGroup("test1").
              withHomePhone("111").withMobile("222").withWorkPhone("333").withAddress("Пермь").
              withEmail("test@test.test").withEmail2("test2@test.test").withEmail3("test3@test.test"), true);
    }
    app.contact().returnToHomePage();
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals("")).
                    collect(Collectors.joining("\n"));
  }


}
