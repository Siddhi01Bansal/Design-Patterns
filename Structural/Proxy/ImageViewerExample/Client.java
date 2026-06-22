public class Client {
    public static void main(String []args){
        System.out.println("Creating Image Proxy...\n");

        Image image = new ImageProxy("vacation.jpg","10MB");

        System.out.println("Fetching image metadata:");
        System.out.println("File Size: " + image.getFileSize());

        System.out.println("\nFirst display request:");
        image.display();

        System.out.println("\nSecond display request:");
        image.display();
    }
}
