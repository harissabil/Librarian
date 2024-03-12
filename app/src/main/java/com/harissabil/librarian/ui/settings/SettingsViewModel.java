package com.harissabil.librarian.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.harissabil.librarian.core.preferences.PreferencesRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SettingsViewModel extends ViewModel {
    private final PreferencesRepository preferencesRepository;

    @Inject
    SettingsViewModel(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
        init();
    }

    private void init() {
        _sortingDelayDuration.setValue(getSortingDelayDuration());
    }

    private final MutableLiveData<Long> _sortingDelayDuration = new MutableLiveData<>();
    public LiveData<Long> sortingDelayDuration = _sortingDelayDuration;

    public void setSortingDelayDuration(long duration) {
        preferencesRepository.setSortingDelayDuration(duration);
    }

    private long getSortingDelayDuration() {
        return preferencesRepository.getSortingDelayDuration();
    }
}
