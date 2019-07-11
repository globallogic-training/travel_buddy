package com.challenge.travel_buddy;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.challenge.travel_buddy.train.view.ui.StationListActivity;
import com.challenge.travel_buddy.train.viewmodal.StationListViewModal;

import org.junit.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;

@Test
public void updateUI(){
//    StationListViewModal viewModel = ViewModelProviders.of(this, viewModelFactory)
//            .get(StationListViewModal.class);
//    // Trigger
//    viewModel.searchStation("ngp");
    // Validation
//    Mockito.verify(viewModel.searchStationModelLiveData).set("dummy text");
}
}