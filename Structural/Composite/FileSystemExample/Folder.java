import java.util.*;

public class Folder implements FileSystemItem{
    private String name;
    private List<FileSystemItem> children = new ArrayList<>();
    public Folder(String name){
        this.name = name;
    }
    @Override
    public int getSize(){
        int totalSize=0;
        for(FileSystemItem item: children){
            totalSize+=item.getSize();
        }
        return totalSize;
    }
    @Override
    public void delete(){
        for(FileSystemItem item: children){
            item.delete();
        }
        System.out.println("Deleting folder: "+name);
    }
    @Override
    public void printStructure(String indent){
        System.out.println(indent + "+ " + name);

        for(FileSystemItem item : children){
            item.printStructure(indent + "    ");
        }
    }
    public void add(FileSystemItem item){
        children.add(item);
    }
    public void remove(FileSystemItem item){
        children.remove(item);
    }
}
