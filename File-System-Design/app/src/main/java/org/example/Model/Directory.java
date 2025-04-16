package org.example.Model;

import java.util.ArrayList;
import java.util.List;

import org.example.Abstract.DirectoryAbstract;


public class Directory extends DirectoryAbstract {
    public String name;
    List<Directory> subDirectories;
    List<File> files;

    public Directory(String name) {
        this.name = name;
        this.subDirectories = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    public Directory subDirectoryIsFound(String name){
        for (Directory subDirectory : subDirectories) {
            if (subDirectory.name.equals(name)) {
                return subDirectory;
            }
        }
        return null;
    }

    public File fileIsFound(String name){
        for (File file : files) {
            if (file.name.equals(name)) {
                return file;
            }
        }
        return null;
    }

    

    public  void add(String directoryName){
        Directory directory = new Directory(directoryName);
        subDirectories.add(directory);
        System.out.println("Directory added: " + directoryName);
    }

    public  void remove(String path){
        for (Directory subDirectory : subDirectories) {
            if (subDirectory.name.equals(path)) {
                subDirectories.remove(subDirectory);
                System.out.println("Subdirectory removed: " + path);
                return;
            }
        }
    }
    public  Directory ls(){
        for (Directory subDirectory : subDirectories) {
            System.out.println(subDirectory.name);
        }
        for (File file : files) {
            System.out.println(file.name);
        }
        return this;
    }

    
    public void rmfile(String fileName) {
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                files.remove(file);
                System.out.println("File removed: " + fileName);
                return;
            }
        }
    }

  


    public File echo(String fileName) {
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                System.out.println("File found content: " + file.getFileContent());
                return file;
            }
        }
        return null;
    }

    public void newFile(String fileName, String content) {
        File file = new File(fileName, content);
        files.add(file);
        System.out.println("File added: " + fileName);
    }
    public void updateFile(String fileName, String content) {
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                System.out.println("File found: " + fileName);
                file.updateFileContent(content);
            }else {
                System.out.println("File not found");
            }
        }
    }
    
}
