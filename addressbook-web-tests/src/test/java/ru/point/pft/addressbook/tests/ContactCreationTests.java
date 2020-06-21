package ru.point.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;
import ru.point.pft.addressbook.model.Contacts;
import ru.point.pft.addressbook.model.GroupData;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line !=null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line !=null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line !=null) {
      String[] split = line.split(";");
      list.add(new Object[] {new ContactData().withFirstName(split[0]).withLastName(split[1]).
              withAddress(split[2]).withMobile(split[3]).withEmail(split[4]).withGroup(split[5])});
      line = reader.readLine();
    };
    return list.iterator();
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) {
    File photo = new File("src/test/resources/1.jpg");
    contact = contact.withPhoto(photo);
      //ContactData contact = new ContactData().withFirstName(firstName).withLastName(lastName).withMobile(mobile).withEmail(email).withAddress(address);
              //withFirstName("Мяу").withLastName("Мяу").withMobile("79899999999").withEmail("test@test.test").withGroup("test1").
             // withHomePhone("111").withWorkPhone("333").withAddress("Пермь").
             // withEmail2("test2@test.test").withEmail3("test3@test.test").withPhoto(new File("src\\test\\resources\\1.jpg"));
    Contacts before = app.contact().all();
    //File photo = new File("src\\test\\resources\\1.jpg");
    /*
    ContactData contact = new ContactData().
            withFirstName("Мяу").withLastName("Мяу").withMobile("79899999999").withEmail("test@test.test").withGroup("test1").
            withHomePhone("111").withWorkPhone("333").withAddress("Пермь").
            withEmail2("test2@test.test").withEmail3("test3@test.test").withPhoto(new File("src\\test\\resources\\1.jpg"));
    */
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }


  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    File photo = new File("src/test/resources/1.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
