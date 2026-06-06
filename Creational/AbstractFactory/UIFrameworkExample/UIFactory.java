public interface UIFactory {
    Button createButton();
    CheckBox createCheckBox();
    TextBox createTextBox();
}

class WindowsUIFactory implements UIFactory{
    public Button createButton(){
        return new WindowsButton();
    }
    public CheckBox createCheckBox(){
        return new WindowsCheckbox();
    }
    public TextBox createTextBox(){
        return new WindowsTextBox();
    }
}
class MacUIFactory implements UIFactory{
    public Button createButton(){
        return new MacButton();
    }
    public CheckBox createCheckBox(){
        return new MacCheckbox();
    }
    public TextBox createTextBox(){
        return new MacTextBox();
    }
}