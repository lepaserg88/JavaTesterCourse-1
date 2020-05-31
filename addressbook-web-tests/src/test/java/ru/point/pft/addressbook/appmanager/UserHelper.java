package ru.point.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.point.pft.addressbook.model.ContactData;
import ru.point.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class UserHelper extends HelperBase {

  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContact() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void contactInformation(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("mobile"),contactData.getMobile());
    type(By.name("email"),contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void addNew() {
    click(By.linkText("add new"));
  }

  public void selectUser(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteUser() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void accept() {
    wd.switchTo().alert().accept();
    getElement();
  }

  public void redactUser(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void updateUser() {
    click(By.name("update"));
    getElement();
  }

  public void createUser(ContactData data, boolean b) {
    addNew();
    contactInformation(new ContactData("ФИО", "ФИО", "79899999999", "test@test.test", "test1"), true);
    submitContact();
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

  public List<ContactData> getUserList() {
    List<ContactData> users = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element: elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String name = cells.get(1).getText();
      String secondName = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData user = new ContactData(id, name, secondName,null,null,null);
      users.add(user);
    }
    return users;
  }
}
