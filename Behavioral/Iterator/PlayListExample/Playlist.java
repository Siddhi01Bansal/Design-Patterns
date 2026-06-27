import java.util.List;
import java.util.ArrayList;
class Playlist implements IterableCollection<String>{
    private List<String> songs = new ArrayList<>();
    @Override
    public Iterator<String> createIterator(){
        return new PlaylistIterator(this);
    }
    public void addSong(String song){
        songs.add(song);
    }
    public int getSize(){
        return songs.size();
    }
    public String getSongAt(int index){
        return songs.get(index);
    }
}