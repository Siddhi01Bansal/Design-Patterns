public class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;
    private Lights lights;
    public HomeTheaterFacade(DVDPlayer dvdPlayer, Projector projector, SoundSystem soundSystem, Lights lights){
        this.dvdPlayer = dvdPlayer;
        this.lights = lights;
        this.projector = projector;
        this.soundSystem = soundSystem;
    }
    public void watchMovie(String movieName){
        lights.dim();
        projector.on();
        soundSystem.on();
        soundSystem.setVolume(50);
        dvdPlayer.playMovie(movieName);
    }
    public void endMovie() {
        System.out.println("\nShutting down Home Theater...");
        projector.off();
        soundSystem.off();
        lights.normal();
    }
}
