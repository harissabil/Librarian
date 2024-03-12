package com.harissabil.librarian.core.preferences;

import static com.harissabil.librarian.core.util.Constant.PREF_LIBRARIAN;
import static com.harissabil.librarian.core.util.Constant.PREF_SORTING_DELAY_DURATION;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesRepository {
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    public PreferencesRepository(Context context) {
        this.pref = context.getSharedPreferences(PREF_LIBRARIAN, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setSortingDelayDuration(long duration) {
        editor.putLong(PREF_SORTING_DELAY_DURATION, duration).apply();
    }

    public long getSortingDelayDuration() {
        return pref.getLong(PREF_SORTING_DELAY_DURATION, 500L);
    }
}
