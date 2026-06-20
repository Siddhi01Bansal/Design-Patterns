public class Client {
    public static void main(String[] args) {
        File resume = new File("Resume.pdf", 10);
        File photo = new File("Photo.jpg", 20);
        File notes = new File("Notes.txt", 5);
        File javaBook = new File("Java.pdf", 15);

        Folder documents = new Folder("Documents");
        documents.add(notes);
        documents.add(javaBook);

        Folder root = new Folder("Root");
        root.add(resume);
        root.add(photo);
        root.add(documents);

        root.printStructure("");

        System.out.println("\nTotal Size: " + root.getSize() + " MB");
    }
}
