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


public class fetchData extends AsyncTask<Void, Void, Void> {

    String resultJson ="";
    String[] usernametest;
    String[] titlestest;
    String[] desctest;
    String[] ratingtest;
    String[] avatartest;

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("https://api.github.com/search/repositories?q=created:>2017-10-22&sort=stars&order=desc");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer();

            String line;
            while((line = bufferedReader.readLine()) != null){
                buffer.append(line);
            }

            resultJson = buffer.toString();
            Log.d("For_LOG",resultJson);

            JSONObject jsonObject = new JSONObject(resultJson);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            
            for(int i=0 ; i <= 10 ; i++){
                JSONObject repoitem = jsonArray.getJSONObject(i);
                titlestest[i] = (String) repoitem.get("name");
                ratingtest[i] = (String) repoitem.get("score");
                desctest[i] = (String) repoitem.get("description");

                JSONObject obj2 = repoitem.getJSONObject("owner");
                usernametest[i] = (String) obj2.get("login");
                avatartest[i] = (String) obj2.get("avatar-url");
            }


        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
