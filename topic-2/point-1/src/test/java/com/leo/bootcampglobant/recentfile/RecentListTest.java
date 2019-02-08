package com.leo.bootcampglobant.recentfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class RecentListTest {

  @Test
  public void getList_emptyOnFirstTime() {
    RecentList<String> recentList = new RecentList<>();
    assertTrue(recentList.getList().isEmpty());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getList_immutableList() {
    RecentList<String> recentList = new RecentList<>();
    recentList.add("Element 1");
    recentList.getList().add("Shouldn't add this");
  }

  @Test
  public void add_uniqueElementsCorrectOrder() {
    RecentList<String> recentList = new RecentList<>();
    String[] testStrings = {"Element 1", "Another element", "And another one", "Test String",
        "Yet another string", "Last String"};

    for (String s : testStrings) {
      recentList.add(s);
    }

    List<String> expected = Arrays.asList(testStrings);
    Collections.reverse(expected);
    assertEquals(expected, recentList.getList());
  }

  @Test
  public void add_noRepetition() {
    RecentList<String> recentList = new RecentList<>();

    recentList.add("Unique element");
    recentList.add("Last one");
    recentList.add("Another string");
    recentList.add("Last one");
    recentList.add("Another string");
    recentList.add("Unique element");

    assertEquals(Arrays.asList("Unique element", "Another string", "Last one"),
        recentList.getList());
  }

  @Test
  public void add_bumpToFront() {
    RecentList<String> recentList = new RecentList<>();

    recentList.add("Unique element");
    recentList.add("Just a normal String");
    recentList.add("The one true String");
    recentList.add("Unique element");

    assertEquals("Unique element", recentList.getList().get(0));
  }

  @Test
  public void add_removeOldestAfterReachingSizeLimit() {
    RecentList<String> recentList = new RecentList<>();

    recentList.setSizeLimit(4);

    recentList.add("Should be removed");
    recentList.add("This one too");
    recentList.add("One element");
    recentList.add("Element two");
    recentList.add("String three");
    recentList.add("Final string");

    assertEquals(Arrays.asList("Final string", "String three", "Element two", "One element"),
        recentList.getList());
  }

  @Test
  public void getSetSizeLimit() {
    RecentList<String> recentList = new RecentList<>();

    recentList.setSizeLimit(15);
    assertEquals(15, recentList.getSizeLimit());
  }

  @Test
  public void getSizeLimit_fromContructor() {
    RecentList<String> recentList = new RecentList<>(20);

    assertEquals(20, recentList.getSizeLimit());
  }

  @Test
  public void setSizeLimit_smallerSizeAfterInsertions() {
    RecentList<String> recentList = new RecentList<>(10);

    recentList.add("Should be removed");
    recentList.add("Remove this too");
    recentList.add("One element");
    recentList.add("Element two");
    recentList.add("String three");
    recentList.add("Final string");

    recentList.setSizeLimit(4);

    assertEquals(Arrays.asList("Final string", "String three", "Element two", "One element"),
        recentList.getList());
  }

  @Test
  public void setSizeLimit_greaterSizeAfterInsertions() {
    RecentList<String> recentList = new RecentList<>(4);

    recentList.add("One element");
    recentList.add("Element two");
    recentList.add("String three");
    recentList.add("Final string");

    recentList.setSizeLimit(20);

    assertEquals(Arrays.asList("Final string", "String three", "Element two", "One element"),
        recentList.getList());
  }

  @Test
  public void setSizeLimit_sameSizeAfterInsertions() {
    RecentList<String> recentList = new RecentList<>(4);

    recentList.add("One element");
    recentList.add("Element two");
    recentList.add("String three");
    recentList.add("Final string");

    recentList.setSizeLimit(4);

    assertEquals(Arrays.asList("Final string", "String three", "Element two", "One element"),
        recentList.getList());
  }
}