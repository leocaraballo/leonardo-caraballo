package com.leo.bootcampglobant.recentfile;

import java.io.File;
import java.util.List;

public class RecentFileList {

  private RecentCollection<File> list;

  public RecentFileList(int sizeLimit) {
    this.list = new RecentList<>(sizeLimit);
  }

  public void addFile(File file) {
    list.add(file);
  }

  public List<File> getFiles() {
    return list.getList();
  }

}
