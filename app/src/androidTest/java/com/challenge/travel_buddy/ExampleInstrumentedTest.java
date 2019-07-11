package com.challenge.travel_buddy;

import androidx.test.rule.ActivityTestRule;

import com.challenge.travel_buddy.train.view.ui.SearchActivity;
import com.schibsted.spain.barista.rule.BaristaRule;

import org.junit.Rule;
import org.junit.Test;

import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;
import static com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo;
import static com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

//    private Player player1 = new Player("Husayn", "x");
//    private Player player2 = new Player("Yasin", "o");

    @Test
    public void end_game_when_one_player_wins() {
//        writeTo(R.id.et_player1, player1.name);
//        writeTo(R.id.et_player2, player2.name);
//        clickDialogPositiveButton();
//
        clickOn(R.id.train_btn);
        clickOn(R.id.fromStationValue1);
        writeTo(R.id.station_edittext,"Ngp");
        sleep(10,SECONDS);
//        BaristaRule<SearchActivity> baristaRule = BaristaRule.create(SearchActivity.class);
//        baristaRule.launchActivity();
//        clickOn(R.id.cell_10);
//        clickOn(R.id.cell_01);
//        clickOn(R.id.cell_11);
//        clickOn(R.id.cell_22);
//        clickOn(R.id.cell_12);
//
//        assertDisplayed(R.id.tv_winner);
//        assertDisplayed(player2.name);
    }
}
