package creatingnew.kz.wordnik;

import android.content.SharedPreferences;
import android.graphics.pdf.PdfDocument;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";
    private EditText wordEditTextView;
    private Button findButton;
    private ViewPager viewPaper;
    private ArrayList<String> words;
    private static final int NUM_PAGES=5;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // wordEditTextView = (EditText)findViewById(R.id.wordEditText);
       // findButton = (Button)findViewById(R.id.findButton);
       // viewPaper = (ViewPager) findViewById(R.id.viewPaper);

       // findButton.setOnClickListener(new View.OnClickListener() {
         //   @Override
        //    public void onClick(View v) {
        //        onFindButtonClick();
        //    }
       // });

        /*******/
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

    }
/*
    private void onFindButtonClick() {
        //http://api.wordnik.com:80/v4/word.json/intellection/relatedWords?useCanonical=false&relationshipTypes=synonym&limitPerRelationshipType=10&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5
        String word = wordEditTextView.getText().toString();
        String url = "http://api.wordnik.com:80/v4/word.json/" + word + "/relatedWords?useCanonical=false&relationshipTypes=synonym&limitPerRelationshipType=10&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5";

        RequestQueue queue = Volley.newRequestQueue(this);

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

        WordsAdapter adapter = new WordsAdapter(getSupportFragmentManager(),fragments);
        viewPaper.setAdapter(adapter);
    }
*/
    @Override
    public void onBackPressed() {
        if(pager.getCurrentItem() == 0) {
            super.onBackPressed();
        }else{
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new PageFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
        public CharSequence getPageTitle(int position) {
            return "Title " + position;
        }
    }
}
