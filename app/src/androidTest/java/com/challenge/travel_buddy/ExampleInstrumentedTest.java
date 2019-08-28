package com.challenge.travel_buddy;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem;

import static com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo;
import static com.schibsted.spain.barista.interaction.BaristaPickerInteractions.setDateOnPicker;
import static com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep;
import static com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void end_when_searched_train() {
        clickOn(R.id.train_btn);
        clickOn(R.id.fromStationValue1);
        writeTo(R.id.station_edittext,"Ngp");
        sleep(5,SECONDS);

        clickListItem(R.id.stationRecylerView, 0);
        sleep(2,SECONDS);

        clickOn(R.id.toStationValue1);
        writeTo(R.id.station_edittext,"cstm");
        sleep(5,SECONDS);

        clickListItem(R.id.stationRecylerView, 0);
        sleep(2,SECONDS);

        clickOn(R.id.journey_date_value1);
        setDateOnPicker(2019, 8, 29);
        sleep(2,SECONDS);

        clickOn(R.id.search_btn1);
        sleep(10,SECONDS);

        assertListItemCount(R.id.trainListRV, 8);
        sleep(3,SECONDS);

    }

    @Test
    public void end_when_searched_buses() {
        clickOn(R.id.bus_btn);
        clickOn(R.id.fromStationValue1);
        writeTo(R.id.station_edittext,"Nagpur");
        sleep(5,SECONDS);

        clickListItem(R.id.stationRecylerView, 0);
        sleep(2,SECONDS);

        clickOn(R.id.toStationValue1);
        writeTo(R.id.station_edittext,"hydrebad");
        sleep(5,SECONDS);

        clickListItem(R.id.stationRecylerView, 1);
        sleep(2,SECONDS);

        clickOn(R.id.journey_date_value1);
        setDateOnPicker(2019, 8, 29);
        sleep(2,SECONDS);

        clickOn(R.id.search_btn1);
        sleep(30,SECONDS);

        assertListItemCount(R.id.busListRV, 20);
        sleep(3,SECONDS);

    }

}

