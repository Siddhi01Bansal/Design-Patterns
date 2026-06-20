public class File implements FileSystemItem{
    private String name;
    private int size;
    public File(String name, int size){
        this.name = name;
        this.size = size;
    }
    @Override
    public int getSize(){
        return size;
    }
    @Override
    public void delete(){
        System.out.println("Deleting file: "+ name);
    }
    @Override
    public void printStructure(String indent){
        System.out.println(indent + "- " + name + " (" + size + " MB)");
    }
}
