public class RealImage implements Image{
    private String fileSize;
    private String fileName;
    public RealImage(String fileSize, String fileName){
        System.out.println("Loading image from disk: " + fileName);
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        this.fileSize = fileSize;
        this.fileName = fileName;
        System.out.println("Image loaded successfully.");
    }
    @Override
    public void display(){
        System.out.println("Displaying image: " + fileName);
    }
    @Override
    public String getFileSize(){
        return this.fileSize;
    }
}

