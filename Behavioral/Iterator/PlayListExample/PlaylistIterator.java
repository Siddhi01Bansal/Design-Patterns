public class PlaylistIterator implements Iterator<String>{
    private Playlist playlist;
    private int index;
    public PlaylistIterator(Playlist playlist){
        this.playlist = playlist;
        this.index = 0;
    }
    public boolean hasNext(){
        return index<playlist.getSize();
    }
    public String next(){
        if(!hasNext()){
            throw new IndexOutOfBoundsException("No more songs in the playlist");
        }
        return playlist.getSongAt(index++);
    }
    
}