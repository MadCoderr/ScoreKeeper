package com.example.farooqi.scorekeeper;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static android.R.attr.id;
import static android.R.attr.multiArch;
import static com.example.farooqi.scorekeeper.R.string.count;

public class MainActivity extends AppCompatActivity {

     static final String TEAM_ONE_SCORE = "score_one";
     static final String TEAM_Two_SCORE = "score_two";

    private TextView teamOneCount;
    private TextView teamTwoCount;

    int countOne;
    int countTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamOneCount = (TextView) findViewById(R.id.lbl_team_one_count);
        teamTwoCount = (TextView) findViewById(R.id.lbl_team_two_count);

    }

    public void decreaseScore(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.imb_team_one_minus:
                if (countOne > 0) {
                    countOne--;
                    teamOneCount.setText(String.valueOf(countOne));
                }
                break;

            case R.id.imb_team_two_minus:
                if (countTwo > 0) {
                    countTwo--;
                    teamTwoCount.setText(String.valueOf(countTwo));
                }
                break;
        }
    }

    public void increaseScore(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.imb_team_one_plus:
                countOne++;
                teamOneCount.setText(String.valueOf(countOne));
                break;

            case R.id.imb_team_two_plus:
                countTwo++;
                teamTwoCount.setText(String.valueOf(countTwo));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        //Change the label of the menu based on the state of the app
        int nightMode = AppCompatDelegate.getDefaultNightMode();

        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            //Get the night mode state of the app
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

            recreate();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(TEAM_ONE_SCORE, countOne);
        outState.putInt(TEAM_Two_SCORE, countTwo);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            countOne = savedInstanceState.getInt(TEAM_ONE_SCORE);
            countTwo = savedInstanceState.getInt(TEAM_Two_SCORE);

            teamOneCount.setText(String.valueOf(countOne));
            teamTwoCount.setText(String.valueOf(countTwo));
        }

    }

}

