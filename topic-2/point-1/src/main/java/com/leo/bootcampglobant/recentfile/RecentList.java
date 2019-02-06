package com.leo.bootcampglobant.recentfile;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of the RecentCollection interface, which make use of Linked List, taking
 * advantage of the O(1) elimination (once it finds the element, which would be O(n) in both
 * ArrayList and LinkedList).
 */
public class RecentList<T> implements RecentCollection<T> {

  private List<T> items = new LinkedList<>();

  @Override
  public List<T> getList() {
    return Collections.unmodifiableList(items);
  }

  @Override
  public void add(T element) {
    //remove if exist
    items.remove(element);
    items.add(0, element);
  }

  @Override
  public int getSizeLimit() {
    return 0;
  }

  @Override
  public void setSizeLimit() {

  }
}
