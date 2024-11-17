package counter.model;

import lombok.NonNull;

public interface PreferenceCounter {
    int[] count(@NonNull Player[] players);
}
