package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.EntryData;

public class AddressbookEntryCreationTests extends TestBase {

  @Test
  public void testAddressbookEntryCreation() {
    app.getNavigationHelper().gotoAddNewEntry();
    app.getContactHelper().fillEntryForm(new EntryData("fname1", "lname1", "address1", "mphone1", "email@test.com"));
    app.getContactHelper().submitEntryCreation();
    app.getContactHelper().returnToHomePage();
  }

}
