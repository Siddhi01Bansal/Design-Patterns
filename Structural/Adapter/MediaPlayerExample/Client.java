public class Client {
    public static void main(String[] args) {
         VLCPlayer vlcPlayer = new VLCPlayer();

        MediaPlayer mediaPlayer = new VLCPlayerAdapter(vlcPlayer);

        mediaPlayer.play("ShapeOfYou.vlc");
    }
}
