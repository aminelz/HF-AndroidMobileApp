package hf.gitandroidapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    private TextView mTextMessage;
    String[] usernametest = {"User1","User2", "User3", "User4", "User5" };
    String[] titlestest = {"Sentiment Analyzer","Apriori Algorithm", "Linear Regression", "Java Address Book", "FlynBetween 2D Game" };
    String[] desctest = {"This a program that runs through tweets and gives their sentiment" , "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy ", "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy ", "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy ", "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy "};
    String[] ratingtest = {"5.9k", "4.3k", "5.0k", "5.7k", "3.5k"};
    ListView listView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    listView.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_setting:
                    mTextMessage.setText(R.string.title_setting);
                    listView.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CustomListAdapter cstmList = new CustomListAdapter(this, usernametest, titlestest, desctest, ratingtest);
        listView = (ListView) findViewById(R.id.lv_repo);
        listView.setAdapter(cstmList);
    }

}
