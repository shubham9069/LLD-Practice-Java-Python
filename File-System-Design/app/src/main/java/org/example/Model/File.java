package org.example.Model;

import java.util.Date;

import org.example.Abstract.FileAbstract;

public class File extends FileAbstract {
    String name;
    String content;
    String createdAt;
    String updatedAt;;
    int size;

    public File(String name, String content) {
        this.name = name;
        this.size = (int) (Math.random() * 1000);
        this.createdAt = new Date().toString();
        this.updatedAt = new Date().toString();
        this.content = content;
    }

    public String getFileContent() {
        System.out.println("File content: " + content);
        return content;
    }

    public void updateFileContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

   
}