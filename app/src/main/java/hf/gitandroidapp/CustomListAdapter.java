package hf.gitandroidapp;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    //to store the user names
    private final ArrayList<String>  usernameArray;

    //to store the repo titles
    private final ArrayList<String>  titleArray;

    //to store the repo descriptions
    private final ArrayList<String>  descArray;

    //to store the ratings
    private final ArrayList<Integer>  ratingArray;

    //to store the avatar url
    private final ArrayList<String> avatarArray;

    //custom list adapter constructor that will be used to populate our list view of repoitems
    public CustomListAdapter(Activity cxt, ArrayList<String> usernameArrayParam, ArrayList<String> tilteArrayParam, ArrayList<String> descArrayParam, ArrayList<Integer> ratingArrayParam, ArrayList<String> avatarArrayParam){

        super(cxt,R.layout.repoitem , usernameArrayParam);

        this.context = cxt;
        this.usernameArray = usernameArrayParam;
        this.titleArray = tilteArrayParam;
        this.descArray = descArrayParam;
        this.ratingArray = ratingArrayParam;
        this.avatarArray = avatarArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.repoitem, null,true);

        //this code gets references to objects in the repoitem.xml file
        TextView usernameTextField = (TextView) rowView.findViewById(R.id.repo_author);
        TextView tilteTextField = (TextView) rowView.findViewById(R.id.repo_name);
        TextView descTextField = (TextView) rowView.findViewById(R.id.repo_desc);
        TextView ratingTextField = (TextView) rowView.findViewById(R.id.repo_rating);
        ImageView avatarImageView = (ImageView) rowView.findViewById(R.id.repo_authpic);

        //this code sets the values of the objects to values from the arrays
        for(int i=0; i< usernameArray.size(); i++) {
            usernameTextField.setText(usernameArray.get(position));
            tilteTextField.setText(titleArray.get(position));
            descTextField.setText(descArray.get(position));
            double rating = ratingArray.get(position);
            String srating;
            DecimalFormat f = new DecimalFormat("#.#");
            f.setRoundingMode(RoundingMode.DOWN);
            if (rating >= 999){ srating = f.format(rating/1000) + "k";}
            else{ srating = String.valueOf((int)rating);}
            ratingTextField.setText(srating);
            Picasso.get().load(avatarArray.get(position)).into(avatarImageView);
        }
        return rowView;

    }
}
