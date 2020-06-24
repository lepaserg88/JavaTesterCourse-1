package ru.point.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{
  public NavigationHelper(ApplicationManager app) {
    super(app);
    wd = app.getDriver();
  }

  public void manageUsersPage(){
    click(By.xpath("//span[contains(text(),'Управление')]/.."));
    click(By.linkText("Управление пользователями"));
  }

  public void UserPage() {
    String user = app.getProperty("web.user");
    click(By.linkText(user));
  }

  public  void resetPassword(){
    click(By.xpath("//input[@value='Сбросить пароль']"));
  }
}
