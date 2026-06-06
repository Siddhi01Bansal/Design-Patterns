public interface Button {
    void onClick();
}

class WindowsButton implements Button {
    public void onClick(){
        System.out.println("Windows button clicked");
    }
}

class MacButton implements Button{
    public void onClick(){
        System.out.println("Mac button clicked");
    }
}