package com.challenge.travel_buddy;

import android.content.Context;

import com.challenge.travel_buddy.bus.view.ui.BusPointListActivity;
import com.challenge.travel_buddy.train.trainsearch.view.ui.helper.Helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//


    private static final String FAKE_STRING = "Login was successful";

    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {

        Helper myObjectUnderTest =
                new Helper();

        BusPointListActivity busPointListActivity = new BusPointListActivity();
        String result = myObjectUnderTest.getStationCode("Nagpur (NGP)");


        assertThat(result, is("NGP"));

        String result1 = myObjectUnderTest.getStationCode("Mumbai - All stations");


        assertThat(result1, is("Mumbai-All"));


        String result2 = myObjectUnderTest.getStationCode("New Delhi - All stations");


        assertThat(result2, is("New Delhi-All"));
    }
}