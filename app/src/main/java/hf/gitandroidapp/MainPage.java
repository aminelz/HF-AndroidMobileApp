package hf.gitandroidapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    private TextView mTextMessage;
    ListView listView;
   // int count;
    int output;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        //Actions to be triggered an item from the navigation is chosen
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home: //case where we click on the Trending navigation item
                    mTextMessage.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_setting: //case where we click on the Settings navigation item
                    output = R.string.title_setting; //+ " "+ String.valueOf(count);
                    mTextMessage.setText(output);
                    listView.setVisibility(View.GONE);
                    mTextMessage.setVisibility(View.VISIBLE);
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
        mTextMessage.setVisibility(View.GONE);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fetchData fetch = new fetchData();
        fetch.execute();

        CustomListAdapter cstmList = new CustomListAdapter(this, fetch.usernames, fetch.titles, fetch.desc, fetch.ratings, fetch.avatars);
        listView = (ListView) findViewById(R.id.lv_repo);
        listView.setAdapter(cstmList);
        //count = listView.getAdapter().getCount();


    }

}
