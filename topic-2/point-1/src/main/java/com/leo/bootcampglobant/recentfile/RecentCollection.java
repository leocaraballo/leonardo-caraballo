package com.leo.bootcampglobant.recentfile;

import java.util.List;

/**
 * Interface for a collection with the behavior of a Recent List.
 *
 * Every element is inserted at the front of the collection, and in the case where a element
 * already exist, it is removed and then placed in front.
 * It also presents a size limit which. When it's surpassed, the oldest element in the collection is
 * removed.
 *
 * @param <T> type of the elements inside the collection.
 */
public interface RecentCollection<T> {

  /**
   * @return a list with all the elements of the collection, ordered by most recent insertion.
   * When the collection is created, it returns an empty list.
   */
  List<T> getList();

  /**
   * Adds an element at the front of the collection. If it was inserted before, its bumped to the
   * front.
   * @param element element to be added (or bumped to the front) in the collection.
   */
  void add(T element);

  /**
   * @return the size limit at which the collection start to remove oldest elements.
   */
  int getSizeLimit();

  void setSizeLimit();
}
