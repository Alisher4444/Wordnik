package creatingnew.kz.wordnik;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordFragment extends Fragment {

    private static final String ARG_WORD = "word";


    // TODO: Rename and change types of parameters
    private String word;



    public WordFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static WordFragment newInstance(String word) {
        WordFragment fragment = new WordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_WORD, word);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            word = getArguments().getString(ARG_WORD);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_word, container, false);

        TextView wordTextView = (TextView) v.findViewById(R.id.wordTextView);
        wordTextView.setText(word);


        return v;
    }

}
