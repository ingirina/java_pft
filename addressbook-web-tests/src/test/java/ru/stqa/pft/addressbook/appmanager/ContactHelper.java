package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.EntryData;

public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitEntryCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillEntryForm(EntryData entryData) {
    type(By.name("firstname"), entryData.getFirstName());
    type(By.name("lastname"), entryData.getLastName());
    type(By.name("address"), entryData.getAddress());
    type(By.name("mobile"), entryData.getMobilePhone());
    type(By.name("email"), entryData.getEmail());
  }
}
