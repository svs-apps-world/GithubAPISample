package com.example.myapplication.activities;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public final class UsersRepoListActivityTest {
    public static final String STRING_TO_BE_TYPED = "octocat";

    @Rule
    public ActivityScenarioRule<UsersRepoListActivity> activityScenarioRule =
            new ActivityScenarioRule<UsersRepoListActivity>(UsersRepoListActivity.class);

    @Before
    public void intentsInit() {
        // initialize Espresso Intents capturing
        Intents.init();
    }

    @After
    public void intentsTeardown() {
        // release Espresso Intents capturing
        Intents.release();
    }

    @Test
    public void checkIfViewsAreDisplayed() {
        // Type text and then press the button.
        onView(withId(R.id.input_text)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());

        onView(withId(R.id.submit_button)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.user_name)).check(matches(not(withText(""))));

        onView(withId(R.id.user_repo_recycler_view)).check(matches(isDisplayed()));
    }
}