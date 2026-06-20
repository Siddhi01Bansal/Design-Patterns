public interface FileSystemItem {
    public int getSize();
    public void delete();
    public void printStructure(String indent);
}