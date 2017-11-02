package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("editedFname1", "editedLname1", "editedAddress1", "editedMphone1", "editedEmail1@test.com"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }

  @Test
  public void testContactDetailsModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().openContactDetails();
    app.getContactHelper().initContactDetailsModification();
    app.getContactHelper().fillContactForm(new ContactData("editedFname2", "editedLname2", "editedAddress2", "editedMphone2", "editedEmail2@test.com"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }

}
