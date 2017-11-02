package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testAddressbookEntryCreation() {
    app.getNavigationHelper().gotoAddNewContact();
    app.getContactHelper().fillContactForm(new ContactData("fname1", "lname1", "address1", "mphone1", "email@test.com"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }

}
