package org.example.Controller;

import org.example.Model.Directory;

public class FileManagerController {
    Directory main;

    public FileManagerController() {
        this.main = new Directory("Main");
    }

    private void createSubDirectory(int index, String[] pathList, Directory current){
        if (index >= pathList.length ) return;
        if (index == pathList.length - 1 && pathList[index].contains(".")) {
            current.newFile(pathList[index], "");
            return ;
        };
        if (current.subDirectoryIsFound(pathList[index]) == null){
            
            current.add(pathList[index]);
        }
        createSubDirectory(index + 1, pathList, current.subDirectoryIsFound(pathList[index]));
    }

    private Directory getParentDirectory(int index, String[] pathList, Directory current){
        if (current == null) {
            System.out.println("Parent directory not found");
            return null;
        };

        if (pathList.length == 1){ 
            System.out.println("Parent directory: " + current.name);
            return current;
        }

        if (index == pathList.length - 1) return current;
        return getParentDirectory(index + 1, pathList, current.subDirectoryIsFound(pathList[index]));
    }

    private Directory getDirectory(String path){
        String[] pathList = path.split("/");
        Directory parent = getParentDirectory(1, pathList, main);
        if (parent == null) return null;
        return parent.subDirectoryIsFound(pathList[pathList.length - 1]);
    }

    public void mkdir(String path) {    
        String[] pathList = path.split("/");
        createSubDirectory(1, pathList, main);
    }

    public  void rmdir(String path){
        String[] pathList = path.split("/");
        Directory parent = getParentDirectory(1, pathList, main);
        if (parent == null) return;

        parent.remove(pathList[pathList.length - 1]);
    }
    public void ls(String path){
        String[] pathList = path.split("/");
        Directory directory = getDirectory(path);
        if (directory == null) return;
        directory.ls();
    }

    public void echo(String path){
        String[] pathList = path.split("/");
        Directory parent = getParentDirectory(1, pathList, main);
        if (parent == null) return;
        parent.echo(pathList[pathList.length - 1]);
        
    }

    public void updateFile(String path, String content){
        String[] pathList = path.split("/");
        Directory parent = getParentDirectory(1, pathList, main);
        if (parent == null) return;
        parent.updateFile(pathList[pathList.length - 1], content);
    }

    public void newFile(String path, String content){
        String[] pathList = path.split("/");
        Directory parent = getParentDirectory(1, pathList, main);
        if (parent == null) return;
        if (parent.fileIsFound(pathList[pathList.length - 1]) != null){
            System.out.println("File already exists");
            return;
        }
        parent.newFile(pathList[pathList.length - 1], content);
    }

    public void rmfile(String path){
        String[] pathList = path.split("/");
        Directory parent = getParentDirectory(1, pathList, main);
        if (parent == null) return;
        parent.rmfile(pathList[pathList.length - 1]);
    }
} 