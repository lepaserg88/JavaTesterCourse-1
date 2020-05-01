package ru.point.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.point.pft.addressbook.model.ContactData;

public class UserHelper extends HelperBase {

  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContact() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void contactInformation(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("mobile"),contactData.getMobile());
    type(By.name("email"),contactData.getEmail());
  }

  public void addNew() {
    click(By.linkText("add new"));
  }

}
