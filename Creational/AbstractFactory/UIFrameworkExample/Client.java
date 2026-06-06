public class Client {
    public static void main(String[] args){
        UIFactory factory = new WindowsUIFactory();
        Button button = factory.createButton();
        button.onClick();
        CheckBox checkbox = factory.createCheckBox();
        checkbox.check();
        TextBox textbox = factory.createTextBox();
        textbox.typeText("Hola everyone!");

        factory = new MacUIFactory();
        Button button2 = factory.createButton();
        button2.onClick();
        CheckBox checkbox2 = factory.createCheckBox();
        checkbox2.check();
        TextBox textbox2 = factory.createTextBox();
        textbox2.typeText("Hola Mac Users!");
    }
}
