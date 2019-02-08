package com.leo.bootcampglobant.recentfile;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class RecentFileListTest {

  private RecentFileList recentFileList;

  @Before
  public void initialize() {
    recentFileList = new RecentFileList(5);
  }

  @Test
  public void getFiles_emptyOnFirstTime() {
    assertTrue(recentFileList.getFiles().isEmpty());
  }

  @Test
  public void add_uniqueFiles() {
    recentFileList.addFile(new File("BestClass.java"));
    recentFileList.addFile(new File("Documentation.txt"));
    recentFileList.addFile(new File("Spreadsheet.csv"));
    recentFileList.addFile(new File("MyDatabase.sql"));

    assertEquals(Arrays.asList(new File("MyDatabase.sql"), new File("Spreadsheet.csv"),
        new File("Documentation.txt"), new File("BestClass.java")),
        recentFileList.getFiles());
  }

  @Test
  public void add_duplicateFiles() {
    recentFileList.addFile(new File("Documentation.txt"));
    recentFileList.addFile(new File("Spreadsheet.csv"));
    recentFileList.addFile(new File("Documentation.txt"));
    recentFileList.addFile(new File("BestClass.java"));
    recentFileList.addFile(new File("BestClass.java"));
    recentFileList.addFile(new File("Documentation.txt"));
    recentFileList.addFile(new File("Spreadsheet.csv"));
    recentFileList.addFile(new File("MyDatabase.sql"));

    assertEquals(Arrays.asList(new File("MyDatabase.sql"), new File("Spreadsheet.csv"),
        new File("Documentation.txt"), new File("BestClass.java")),
        recentFileList.getFiles());
  }

  @Test
  public void add_exceedSizeLimit() {
    // size limit is 5
    recentFileList.addFile(new File("File1.txt"));
    recentFileList.addFile(new File("File2.docx"));
    recentFileList.addFile(new File("File3.png"));
    recentFileList.addFile(new File("File3.png"));
    recentFileList.addFile(new File("File4.mp4"));
    recentFileList.addFile(new File("File5.java"));
    recentFileList.addFile(new File("File5.java"));
    recentFileList.addFile(new File("File6.sql"));

    assertEquals(Arrays.asList(new File("File6.sql"), new File("File5.java"),
        new File("File4.mp4"), new File("File3.png"), new File("File2.docx")),
        recentFileList.getFiles());

  }
}