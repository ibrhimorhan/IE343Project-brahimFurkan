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

        // �lk �nce, maksimum value'ye sahip �ark�lar� s�ralayal�m.
        tracks.sort((t1, t2) -> t2.track_individual_value - t1.track_individual_value);

        // �imdi, albumLengthLimit'i a�mayacak �ekilde �ark�lar� se�elim.
        for (Track track : tracks) {
            if (track.track_duration <= remainingCapacity) {
                // Alb�me �ark� ekleyebiliriz.
                selectedTracks.add(track);
                this.totalValue += track.track_individual_value;
                remainingCapacity -= track.track_duration;
            }
        }

        // E�er alb�m hala bo� kapasiteye sahipse, toplam value'y� saniye ba��na 0.02
        // de�erinde d���relim.
        if (remainingCapacity > 0) {
            this.totalValue -= remainingCapacity * 0.00002;
        }

        return selectedTracks;
    }
}
