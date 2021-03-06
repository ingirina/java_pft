package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoAddNewContact() {
    click(By.linkText("add new"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    if (creation) {
      if (contactData.getGroup() != null) {
        try {
          new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } catch (NoSuchElementException ex) {
          System.out.println("Группа " + contactData.getGroup() + " не существует"); //надо усовершенствовать, реализовав создание новой группы в такой ситуации
        }
      }
    }
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id +"']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
  }

  public void initContactModificationById(int id) {
  //  wd.findElement(By.cssSelector("a[href='edit.php?id=" + id +"']>img")).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void deleteModifyingContact() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void openContactDetails() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[7]/a/img"));
  }

  public void initContactDetailsModification() {
    click(By.name("modifiy"));
  }

  public void create(ContactData contactData) {
    gotoAddNewContact();
    fillContactForm(contactData, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    contactCache = null;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  private Contacts contactCache = null;


  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }


}
