public interface TextBox {
    void typeText(String text);
}

class WindowsTextBox implements TextBox{
    public void typeText(String text){
        System.out.println("Typing in Windows TextBox: "+text);
    }
}

class MacTextBox implements TextBox{
    public void typeText(String text){
        System.out.println("Typing in Windows TextBox: "+text);
    }
}
