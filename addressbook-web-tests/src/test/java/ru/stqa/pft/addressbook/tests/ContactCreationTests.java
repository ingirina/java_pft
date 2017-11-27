package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test// (enabled = false)
  public void testContactCreation() {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/testfile.png");
    ContactData contact = new ContactData().withFirstName("fname9").withLastName("lname10").withAddress("address2").withHomePhone("111")
            .withMobilePhone("222").withWorkPhone("333").withEmail("email2@test.com").withGroup("test1").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

 /* @Test
  public void getCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/testfile.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  } */
}

