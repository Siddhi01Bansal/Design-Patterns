public class Client {
    public static void main(String[] args) {
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();
        Lights lights = new Lights();
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(dvdPlayer, projector, soundSystem, lights);
        homeTheaterFacade.watchMovie("Interstellar");
        System.out.println();
        homeTheaterFacade.endMovie();
    }
}
