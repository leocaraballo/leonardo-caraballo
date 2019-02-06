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
  public void add_oneElement() {
    RecentList<String> recentList = new RecentList<>();
    recentList.add("Element 1");
    assertEquals("Element 1", recentList.getList().get(0));
  }

  @Test
  public void add_twoElements() {
    RecentList<String> recentList = new RecentList<>();
    recentList.add("Element 1");
    recentList.add("Last Element");
    assertEquals("Last Element", recentList.getList().get(0));
  }

  @Test
  public void add_multipleElementsCorrectOrder() {
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
}