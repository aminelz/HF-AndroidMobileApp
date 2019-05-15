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
//    String[] usernames={"test1", "test1", "test1", "test1", "test1", };
//    String[] titles={"test1", "test1", "test1", "test1", "test1", };
//    String[] desc={"test1", "test1", "test1", "test1", "test1", };
//    String[] ratings={"test1", "test1", "test1", "test1", "test1", };
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

        fetchData fetch = new fetchData();
        fetch.execute();

        CustomListAdapter cstmList = new CustomListAdapter(this, fetch.usernames, fetch.titles, fetch.desc, fetch.ratings, fetch.avatars);
        listView = (ListView) findViewById(R.id.lv_repo);
        listView.setAdapter(cstmList);
    }

}
