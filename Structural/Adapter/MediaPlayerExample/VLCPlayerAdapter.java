public class VLCPlayerAdapter implements MediaPlayer{
    private VLCPlayer vlcPlayer;
    public VLCPlayerAdapter(VLCPlayer vlcPlayer){
        this.vlcPlayer = vlcPlayer;
    }
    public void play(String filename){
        vlcPlayer.playVLC(filename);
    }
}
