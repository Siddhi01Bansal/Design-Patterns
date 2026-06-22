public class ImageProxy implements Image{
    private String fileSize;
    private String fileName;
    private RealImage realImage;
    public ImageProxy(String fileName,String fileSize){
        this.fileName = fileName;
        this.fileSize = fileSize;
    }
    @Override
    public void display(){
        if(realImage == null){
            System.out.println("[Proxy] Image not loaded yet. Creating RealImage...");
            realImage = new RealImage(fileSize, fileName);
        } else{
            System.out.println("[Proxy] Reusing already loaded image.");
        }
        realImage.display();
    }
    @Override 
    public String getFileSize(){
        return this.fileSize;
    }
}
