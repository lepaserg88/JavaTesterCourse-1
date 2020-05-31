package ru.point.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.point.pft.addressbook.model.ContactData;

import java.util.List;

public class DeleteUser  extends TestBase{

  @BeforeMethod
  public void ensurePreconditionsContact() {
    if (! app.getUserHelper().isThereAUser()){
      app.getUserHelper().createUser(new ContactData("ФИО", "ФИО", "79899999999", "test@test.test", "test1"), true);
    }
    app.getUserHelper().returnToHomePage();
  }

  @Test
  public void testDeleteUser(){
    List<ContactData> before = app.getUserHelper().getUserList();
    app.getUserHelper().selectUser(before.size() - 1);
    app.getUserHelper().deleteUser();
    app.getUserHelper().accept();
    app.getUserHelper().returnToHomePage();
    List<ContactData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
