package com.leo.bootcampglobant.recentfile;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class RecentFileListTest {

  private static final List<File> correctFileList = Arrays.asList(
      new File("MyDatabase.sql"),
      new File("Spreadsheet.csv"),
      new File("Documentation.txt"),
      new File("BestClass.java")
  );

  private RecentFileList recentFileList;

  @Before
  public void initialize() {
    recentFileList = new RecentFileList(4);
  }

  @Test
  public void getFiles_emptyOnFirstTime() {
    assertTrue(recentFileList.getFiles().isEmpty());
  }

  @Test
  public void openFile_uniqueFiles() {
    recentFileList.openFile(new File("BestClass.java"));
    recentFileList.openFile(new File("Documentation.txt"));
    recentFileList.openFile(new File("Spreadsheet.csv"));
    recentFileList.openFile(new File("MyDatabase.sql"));

    assertEquals(correctFileList, recentFileList.getFiles());
  }

  @Test
  public void openFile_duplicateFiles() {
    recentFileList.openFile(new File("Documentation.txt"));
    recentFileList.openFile(new File("Spreadsheet.csv"));
    recentFileList.openFile(new File("Documentation.txt"));
    recentFileList.openFile(new File("BestClass.java"));
    recentFileList.openFile(new File("BestClass.java"));
    recentFileList.openFile(new File("Documentation.txt"));
    recentFileList.openFile(new File("Spreadsheet.csv"));
    recentFileList.openFile(new File("MyDatabase.sql"));

    assertEquals(correctFileList, recentFileList.getFiles());
  }

  @Test
  public void openFile_exceedSizeLimit() {
    // size limit is 4
    recentFileList.openFile(new File("oldIndex.htm"));
    recentFileList.openFile(new File("importantList.doc"));
    recentFileList.openFile(new File("myPicture.bmp"));
    recentFileList.openFile(new File("Documentation.txt"));
    recentFileList.openFile(new File("Spreadsheet.csv"));
    recentFileList.openFile(new File("Documentation.txt"));
    recentFileList.openFile(new File("BestClass.java"));
    recentFileList.openFile(new File("BestClass.java"));
    recentFileList.openFile(new File("Documentation.txt"));
    recentFileList.openFile(new File("Spreadsheet.csv"));
    recentFileList.openFile(new File("MyDatabase.sql"));

    assertEquals(correctFileList, recentFileList.getFiles());

  }
}