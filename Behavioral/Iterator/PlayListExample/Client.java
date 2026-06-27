class Client {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong("Sapphire");
        playlist.addSong("Perfect");
        playlist.addSong("Gasolina");
        Iterator<String> iterator = playlist.createIterator();
        System.out.println("Now Playing:");
        while (iterator.hasNext()) {
            System.out.println("🎵🎵🎶🎼" + iterator.next());
        }
    }    
}