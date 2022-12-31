 import java.util.ArrayList;
import java.util.List;

public class AlgorithmTwo {
    private List<Track> tracks;
    private List<List<String>> sequentialData;

    // Constructor to initialize the attributes of the class
    public AlgorithmTwo(List<Track> tracks, List<List<String>> sequentialData) {
        this.tracks = tracks;
        this.sequentialData = sequentialData;
    }

    // Method to solve the problem and return the list of ordered track IDs
    public List<Integer> solve() {
        // Initialize the list of ordered track IDs
        List<Integer> orderedTracks = new ArrayList<>();

        // Find the track with the highest value and add it to the list
        int highestValueTrackId = -1;
        int highestValue = -1;
        for (Track track : tracks) {
            if (track.track_individual_value > highestValue) {
                highestValue = track.track_individual_value;
                highestValueTrackId = track.getId();
            }
        }
        orderedTracks.add(highestValueTrackId);

     // Find the track with the second highest value and add it to the list
        int secondHighestValueTrackId = -1;
        int secondHighestValue = -1;
        for (Track track : tracks) {
            if (track.track_individual_value > secondHighestValue && track.getId() != highestValueTrackId) {
                secondHighestValue = track.track_individual_value;
                secondHighestValueTrackId = track.getId();
            }
        }
        orderedTracks.add(secondHighestValueTrackId);

        // Iterate through the remaining tracks and add them to the list in the order
        // determined by the sequential data
        for (Track track : tracks) {
            if (track.getId() == highestValueTrackId || track.getId() == secondHighestValueTrackId) {
                continue;
            }

            // Find the index of the current track in the sequential data
            int trackIndex = -1;
            for (int i = 0; i < sequentialData.size(); i++) {
                if (Double.parseDouble(sequentialData.get(i).get(0)) == track.getId()) {
                    trackIndex = i;
                    break;
                }
                
                else {
                	continue;
                }
            }

            // Find the maximum value for the current track among its previous and next tracks
            double maxValue = -1;
            for (int i = 1; i < sequentialData.get(trackIndex).size(); i++) {
                double value = Double.parseDouble(sequentialData.get(trackIndex).get(i));
                if (value > maxValue) {
                    maxValue = value;
                }
            }

            // Find the index of the track with the maximum value
            double maxValueTrackIndex = -1;
            for (int i = 0; i < sequentialData.size(); i++) {
                if (Double.parseDouble(sequentialData.get(i).get(0)) == maxValue) {
                    maxValueTrackIndex = i;
                    break;
                }
            }

            // Add the current track to the list after the track with the maximum value
            orderedTracks.add(track.getId());
        }

        return orderedTracks;
}
}