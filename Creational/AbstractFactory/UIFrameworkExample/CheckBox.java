public interface CheckBox {
    public void check();
    public void uncheck();
    public Boolean isChecked();
}

class WindowsCheckbox implements CheckBox{
    private Boolean checked;
    public void check(){
        checked = true;
        System.out.println("Windows Checkbox checked");
    }
    public void uncheck(){
        checked = false;
        System.out.println("Windows Checkbox unchecked");
    }
    public Boolean isChecked(){
        return checked;
    }
}
class MacCheckbox implements CheckBox{
    private Boolean checked;
    public void check(){
        checked = true;
        System.out.println("Mac Checkbox checked");
    }
    public void uncheck(){
        checked = false;
        System.out.println("Mac Checkbox unchecked");
    }
    public Boolean isChecked(){
        return checked;
    }
}
