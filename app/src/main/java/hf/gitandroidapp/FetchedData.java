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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class FetchedData extends AsyncTask<Void, Void, String> {

    String resultJson ="";
    ArrayList<String> usernames = new ArrayList<String>();
    ArrayList<String> titles = new ArrayList<String>();
    ArrayList<String> desc = new ArrayList<String>();
    ArrayList<Integer> ratings = new ArrayList<Integer>();
    ArrayList<String> avatars = new ArrayList<String>();
    int pagenumber = 1;

    @Override
    protected String doInBackground(Void... voids) {

        try {
            Calendar cal = Calendar.getInstance(); //getting today's date
            cal.add(Calendar.DATE, -30); //removing 30 days to the current day
            Date date = cal.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //defining the format to be used by the URL
            String today = dateFormat.format(date); //putting the date into the wanted format
            Log.d("30 days before today = ", today); //printing the date for debugging purposes

            URL url = new URL("https://api.github.com/search/repositories?q=created:>"+today+"&sort=stars&order=desc&page="+ pagenumber); //creating the url from which data will be fetched
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //opening http connection
            httpURLConnection.setRequestMethod("GET"); //specifying that we will be reading/getting data
            httpURLConnection.connect(); //setting up the connexion
            InputStream inputStream = httpURLConnection.getInputStream(); //creating an input stream
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream)); //creating the buffer we will be using to read the input

            StringBuffer buffer = new StringBuffer(); //String buffer that will contain all the lines from the JSON file

            String line;
            while((line = bufferedReader.readLine()) != null){ //going through all lines of the JSON file we're reading through the URL
                buffer.append(line); //appending each line to our string buffer
            }

            resultJson = buffer.toString(); //converting the string buffer into a string
            Log.d("For_LOG",resultJson); //Printing what we read from the json file for debugging purposes


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
            JSONObject jsonObject = new JSONObject(resultJson); //Creating a JSONObject using the string containing all Json lines
            JSONArray jsonArray = jsonObject.getJSONArray("items"); //Creating a JSONArray and populating it with all items inside the "items" tag
            Log.d("For_LOG", jsonArray.toString()); //Printing the resulting Array for debugging purposes

            for(int i=0 ; i < jsonArray.length() ; i++){ //going through each item in the jsonArray = going through all items present in the Json file we read
                JSONObject obj1 = jsonArray.getJSONObject(i); //creating an object instance for each item within the array
                titles.add(obj1.getString("name")); //populating the arrays that will contain the data needed for our listview by using the tags needed
                ratings.add(obj1.getInt("stargazers_count"));
                desc.add(obj1.getString("description"));
                JSONObject obj2 = obj1.getJSONObject("owner"); //Creating a second object instance out of the first one because we have tags that are parsed within another tag
                usernames.add(obj2.getString("login"));
                avatars.add(obj2.getString("avatar_url"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
