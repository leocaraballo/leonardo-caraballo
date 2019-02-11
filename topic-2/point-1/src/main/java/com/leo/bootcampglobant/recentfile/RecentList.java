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

  private static final int DEFAULT_SIZE_LIMIT = 15;

  private List<T> items;
  private int sizeLimit;

  public RecentList(int sizeLimit) {
    this.items = new LinkedList<>();
    this.sizeLimit = sizeLimit;
  }

  public RecentList() {
    this(DEFAULT_SIZE_LIMIT);
  }

  @Override
  public List<T> getList() {
    return Collections.unmodifiableList(items);
  }

  /**
   * Inserts an element at the front of the list. If the element was inserted before, its swapped
   * from its original position to the front of the list.
   *
   * @implSpec This element type should implement a custom <code>equals</code> (and therefore a
   *           custom <code>hashCode</code>) method, so that this method can correctly remove
   *           duplicates elements. Failing to do so would result in a List with repeated
   *           elements.
   * @param element element to be added (or bumped to the front) in the collection. Should
   *        implement a custom equals method.
   */
  @Override
  public void add(T element) {
    //remove if exist
    items.remove(element);
    if (items.size() == sizeLimit) {
      items.remove(sizeLimit - 1);
    }
    items.add(0, element);
  }

  @Override
  public int getSizeLimit() {
    return sizeLimit;
  }

  @Override
  public void setSizeLimit(int sizeLimit) {
    if (sizeLimit < items.size()) {
      //remove the oldest elements
      items.subList(sizeLimit, items.size()).clear();
    }
    this.sizeLimit = sizeLimit;
  }
}
