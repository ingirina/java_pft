package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    String[] langs = new String[4];
    langs[0] = "Java";
    langs[1] = "C#";
    langs[2] = "Pyphon";
    langs[3] = "PHP";

    String[] langs2 = {"Java", "C#", "Python", "PHP"};

    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");

    List<String> languages2 = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : langs) {
      System.out.println("I want to learn " + l);
    }

    for (String l : languages2) {
      System.out.println("I wanted to learn " + l);
    }

    for (int i = 0; i < languages2.size(); i++) {
      System.out.println("I would like to learn " + languages2.get(i));
    }

  }
}
