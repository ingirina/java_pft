package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class AddressbookEntryCreationTests extends TestBase {

  @Test
  public void testAddressbookEntryCreation() {
    initEntryCreation();
    fillEntryForm(new EntryData("fname1", "lname1", "address1", "mphone1", "email@test.com"));
    submitEntryCreation();
    gotoHomePage();
  }

}
