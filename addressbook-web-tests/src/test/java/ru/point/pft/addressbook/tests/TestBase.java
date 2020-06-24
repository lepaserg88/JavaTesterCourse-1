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

import java.io.IOException;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import java.io.IOException;

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

  private Executor getExecutor() {
    return Executor.newInstance().auth("19ce16e741b7cc860c7418001642e2b4","");
  }

  public boolean isBugifyIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsedIssue = new JsonParser().parse(json);
    System.out.println(parsedIssue);
    String issueStatus  = parsedIssue.getAsJsonObject().get("state_name").getAsString();
    if (issueStatus.equals("Deleted")) {
      return false;
    }
    return true;
  }

  public void skipIfNotFixedInBugify(int issueId) throws IOException {
    if (isBugifyIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }


}
