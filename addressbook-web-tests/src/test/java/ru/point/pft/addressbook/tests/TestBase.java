package ru.point.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.point.pft.addressbook.appmanager.ApplicationManager;
import ru.point.pft.addressbook.model.ContactData;
import ru.point.pft.addressbook.model.Contacts;
import ru.point.pft.addressbook.model.GroupData;
import ru.point.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream().
              map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).
              collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, equalTo(dbContacts.stream().
              map((c) -> new ContactData().withId(c.getId()).withLastName(c.getLastName()).withFirstName(c.getFirstName())).
              collect(Collectors.toSet())));
    }
  }
}
