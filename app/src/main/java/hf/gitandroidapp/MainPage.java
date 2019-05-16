//Author: AmineLz

package hf.gitandroidapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    private TextView mTextMessage;
    ListView listView;
    int count=0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        //Actions to be triggered an item from the navigation is chosen
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Button btn = (Button)findViewById(R.id.Button1);
            switch (item.getItemId()) {
                case R.id.navigation_home: //case where we click on the Trending navigation item
                    if(count!=0){ //if we already clicked on the list repos button --> Hide Button and Messafe and Show list of repos
                        mTextMessage.setVisibility(View.GONE);
                        btn.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);}
                    else if(count==0){ //if we still didnt click on the list repos button --> Show message and button
                        mTextMessage.setText(R.string.home_message);
                        mTextMessage.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.VISIBLE);
                    }
                    return true;
                case R.id.navigation_setting: //case where we click on the Settings navigation item --> Hide button and welcoming message
                    listView.setVisibility(View.GONE); //settings features to be added
                    mTextMessage.setVisibility(View.GONE);
                    btn.setVisibility(View.GONE);
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
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FetchedData fetch = new FetchedData();
        fetch.execute();
        CustomListAdapter cstmList = new CustomListAdapter(this, fetch.usernames, fetch.titles, fetch.desc, fetch.ratings, fetch.avatars);
        listView = (ListView) findViewById(R.id.lv_repo);
        listView.setAdapter(cstmList);

    }

    public void listRepos(View view){ //Function called when user click on the list button --> hides button and message from view and displays the list of repos
        count +=1;

        Button btn = (Button)findViewById(R.id.Button1);
        btn.setVisibility(View.GONE);
        mTextMessage.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);


    }

}
