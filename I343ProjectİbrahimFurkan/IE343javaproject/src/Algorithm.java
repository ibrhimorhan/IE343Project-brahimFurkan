 import java.util.ArrayList;

public class Algorithm {
    private ArrayList<Track> tracks;
    private int albumLengthLimit;
    public double totalValue;

    public Algorithm(ArrayList<Track> tracks, int albumLengthLimit) {
        this.tracks = tracks;
        this.albumLengthLimit = albumLengthLimit;
    }

    public ArrayList<Track> solve() {
        ArrayList<Track> selectedTracks = new ArrayList<>();
        this.totalValue = 0;
        int remainingCapacity = albumLengthLimit;

        // Ýlk önce, maksimum value'ye sahip þarkýlarý sýralayalým.
        tracks.sort((t1, t2) -> t2.track_individual_value - t1.track_individual_value);

        // Þimdi, albumLengthLimit'i aþmayacak þekilde þarkýlarý seçelim.
        for (Track track : tracks) {
            if (track.track_duration <= remainingCapacity) {
                // Albüme þarký ekleyebiliriz.
                selectedTracks.add(track);
                this.totalValue += track.track_individual_value;
                remainingCapacity -= track.track_duration;
            }
        }

        // Eðer albüm hala boþ kapasiteye sahipse, toplam value'yý saniye baþýna 0.02
        // deðerinde düþürelim.
        if (remainingCapacity > 0) {
            this.totalValue -= remainingCapacity * 0.00002;
        }

        return selectedTracks;
    }
}
