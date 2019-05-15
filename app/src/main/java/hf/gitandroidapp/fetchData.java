package hf.gitandroidapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class fetchData extends AsyncTask<Void, Void, String> {

    String resultJson ="";
    ArrayList<String> usernames = new ArrayList<String>();
    ArrayList<String> titles = new ArrayList<String>();
    ArrayList<String> desc = new ArrayList<String>();
    ArrayList<String> ratings = new ArrayList<String>();
    ArrayList<String> avatars = new ArrayList<String>();

    @Override
    protected String doInBackground(Void... voids) {

        try {
            URL url = new URL("https://api.github.com/search/repositories?q=created:>2017-10-22&sort=stars&order=desc&page=2");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer();

            String line;
            while((line = bufferedReader.readLine()) != null){
                buffer.append(line);
            }

            resultJson = buffer.toString();
            Log.d("For_LOG",resultJson);


        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String resultJson) {
        super.onPostExecute(resultJson);

        try {
            JSONObject jsonObject = new JSONObject(resultJson);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            Log.d("For_LOG", jsonArray.toString());

            for(int i=0 ; i < jsonArray.length() ; i++){
                JSONObject obj1 = jsonArray.getJSONObject(i);
                titles.add(obj1.getString("name"));
                ratings.add(obj1.getString("score"));
                desc.add(obj1.getString("description"));

                JSONObject obj2 = obj1.getJSONObject("owner");
                usernames.add(obj2.getString("login"));
                avatars.add(obj2.getString("avatar_url"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }





    }

}
