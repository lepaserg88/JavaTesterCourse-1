package ru.point.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.point.pft.addressbook.model.ContactData;
import ru.point.pft.addressbook.model.Contacts;
import ru.point.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContact() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void contactInformation(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstName());
    type(By.name("lastname"),contactData.getLastName());
    type(By.name("mobile"),contactData.getMobile());
    type(By.name("email"),contactData.getEmail());
    type(By.name("address"),contactData.getAddress());

  if (creation)  {
    if (contactData.getGroups().size() > 0) {
      Assert.assertTrue(contactData.getGroups().size() == 1);
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
  }  else {
    Assert.assertFalse(isElementPresent(By.name("new_group")));
  }
  }
  }

  public void addNew() {
    click(By.linkText("add new"));
  }

  public void selectUserById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteUser() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void accept() {
    wd.switchTo().alert().accept();
    getElement();
  }

  public void redactUserById(int id) {
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
  }

  public void update() {
    click(By.name("update"));
    getElement();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void create(ContactData contact) {
    addNew();
    contactInformation(contact, false);
    submitContact();
    contactCache = null;
    }

  public void modify(ContactData contact) {
    redactUserById(contact.getId());
    contactInformation(contact, false);
    update();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectUserById(contact.getId());
    deleteUser();
    accept();
    contactCache = null;
    returnToHomePage();
  }

  public boolean isThereAUser() {
    return isElementPresent(By.name("selected[]"));
  }

  public void returnToHomePage() {
    if (isElementPresent(By.name("MainForm"))) {
      return;
    }
    click(By.linkText("home"));
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache !=null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element: elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName).
            withHomePhone(home).withMobile(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
  }

  public void addContactToGroup(ContactData contact, GroupData group) {
    selectUserById(contact.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByValue(group.getId().toString());
    AddToGroup();
  }

  public void deleteContactFromGroup(ContactData contact, GroupData group) {
    new Select(wd.findElement(By.name("group"))).selectByValue(group.getId().toString());
    selectUserById(contact.getId());
    DeleteFromGroup();
  }

  public void AddToGroup() {
    wd.findElement(By.name("add")).click();
  }

  private void DeleteFromGroup() {
    wd.findElement(By.name("remove")).click();
  }
}
