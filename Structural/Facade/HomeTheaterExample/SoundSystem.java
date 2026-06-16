public class SoundSystem {
    public int volume;
    public SoundSystem(){
        volume=50;
    }
    public void setVolume(int volume){
        this.volume = volume;
        System.out.println("Volume set to " + volume);
    }
    public void on() {
        System.out.println("Sound System turned on");
    }

    public void off() {
        System.out.println("Sound System turned off");
    }
}
