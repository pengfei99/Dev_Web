package org.etriks.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pliu on 6/4/15.
 */
public class DirectoryManager {
    private String path;
    private File[] listOfFiles;
    public DirectoryManager(String DirPathInWebAPP){
       this.path=System.getProperty("user.dir")+DirPathInWebAPP;
        File folder=new File(path);
        this.listOfFiles=folder.listFiles();
    }

    public List<String> getFileNameList(){
    List<String> fileNameList=new ArrayList<String>();
    for (int i = 0; i < listOfFiles.length; i++) {
        if (listOfFiles[i].isFile()) {
            fileNameList.add(listOfFiles[i].getName());
        }

   }
        return fileNameList;
    }

    public List<String> getDirNameList(){
        List<String> dirNameList=new ArrayList<String>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                dirNameList.add(listOfFiles[i].getName());
            }

        }
        return dirNameList;
    }

    public List<String> getContentList(){
        List<String> contentList=new ArrayList<String>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {

                contentList.add("File: " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                contentList.add("Directory: " + listOfFiles[i].getName());
            }
    }
        return contentList;
}
}
