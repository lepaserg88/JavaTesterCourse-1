package ru.point.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.point.pft.addressbook.model.ContactData;

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

  public void selectUser() {
    click(By.name("selected[]"));
  }

  public void deleteUser() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void accept() {
    wd.switchTo().alert().accept();
    getElement();
  }

  public void redactUser() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void updateUser() {
    click(By.name("update"));
    getElement();
  }

}
