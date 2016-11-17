package creatingnew.kz.wordnik;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Алишер on 21.06.2016.
 */
public class PageFragment extends Fragment {


    private static final String TAG = "MainActivity";
    private EditText wordEditTextView;
    private Button findButton;
    private ViewPager viewPaper;
    private ViewPager viewPaper2;
    private ArrayList<String> words;
    private static final int NUM_PAGES=5;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private FragmentActivity context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment,container,false);

        wordEditTextView = (EditText) rootView.findViewById(R.id.wordEditText);
        findButton = (Button) rootView.findViewById(R.id.findButton);
        viewPaper = (ViewPager) rootView.findViewById(R.id.viewPaper);
        viewPaper2 = (ViewPager) rootView.findViewById(R.id.viewPaper2);


        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFindButtonClick();
            }
        });



        return rootView;

    }
    private void onFindButtonClick() {
        //http://api.wordnik.com:80/v4/word.json/intellection/relatedWords?useCanonical=false&relationshipTypes=synonym&limitPerRelationshipType=10&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5
        String word = wordEditTextView.getText().toString();
        String url = "http://api.wordnik.com:80/v4/word.json/" + word + "/relatedWords?useCanonical=false&relationshipTypes=synonym&limitPerRelationshipType=10&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5";

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                if(response.length()>0){
                    try {
                        JSONObject jsonObject = response.getJSONObject(0);
                        JSONArray jsonWords = jsonObject.getJSONArray("words");

                        words = new ArrayList<>();

                        for(int i = 0; i<jsonWords.length(); i++){
                            String word = jsonWords.getString(i);
                            words.add(word);
                        }
                        Log.d(TAG, "words are " + words);

                        displayWords();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.getMessage());
            }
        });
        queue.add(request);
    }

    private void displayWords() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        for(String word : words){
            fragments.add(WordFragment.newInstance(word));


        }



        WordsAdapter adapter = new WordsAdapter(getActivity().getSupportFragmentManager(),fragments);
        viewPaper.setAdapter(adapter);

    }
}
