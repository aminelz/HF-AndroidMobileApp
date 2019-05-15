package hf.gitandroidapp;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.*;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ListView;

public class CustomListAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    //to store the user names
    private final String[] usernameArray;

    //to store the repo titles
    private final String[] titleArray;

    //to store the repo descriptions
    private final String[] descArray;

    //to store the ratings
    private final String[] ratingArray;

    public CustomListAdapter(Activity cxt, String[] usernameArrayParam, String[] tilteArrayParam, String[] descArrayParam, String[] ratingArrayParam){

        super(cxt,R.layout.repoitem , usernameArrayParam);

        this.context = cxt;
        this.usernameArray = usernameArrayParam;
        this.titleArray = tilteArrayParam;
        this.descArray = descArrayParam;
        this.ratingArray = ratingArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.repoitem, null,true);

        //this code gets references to objects in the repoitem.xml file
        TextView usernameTextField = (TextView) rowView.findViewById(R.id.repo_author);
        TextView tilteTextField = (TextView) rowView.findViewById(R.id.repo_name);
        TextView descTextField = (TextView) rowView.findViewById(R.id.repo_desc);
        TextView ratingTextField = (TextView) rowView.findViewById(R.id.repo_rating);

        //this code sets the values of the objects to values from the arrays
        usernameTextField.setText(usernameArray[position]);
        tilteTextField.setText(titleArray[position]);
        descTextField.setText(descArray[position]);
        ratingTextField.setText(ratingArray[position]);

        return rowView;

    }
}
