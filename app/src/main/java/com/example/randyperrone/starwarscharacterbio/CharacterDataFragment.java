package com.example.randyperrone.starwarscharacterbio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.randyperrone.starwarscharacterbio.Model.CharacterData;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CharacterDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CharacterDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterDataFragment extends Fragment {
    private View layoutView;
    private CharacterData characterData;
    private TextView name, gender, height, mass, birthYear, eyeColor, hairColor, skinColor;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public CharacterDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CharacterDataFragment.
     */
    public static CharacterDataFragment newInstance(String param1, String param2) {
        CharacterDataFragment fragment = new CharacterDataFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutView = inflater.inflate(R.layout.fragment_character_data, container, false);
        name = (TextView)layoutView.findViewById(R.id.char_name);
        gender = (TextView)layoutView.findViewById(R.id.char_gender);
        height= (TextView)layoutView.findViewById(R.id.char_height);
        mass = (TextView)layoutView.findViewById(R.id.char_mass);
        birthYear = (TextView)layoutView.findViewById(R.id.char_birthdate);
        eyeColor = (TextView)layoutView.findViewById(R.id.char_eyecolor);
        hairColor = (TextView)layoutView.findViewById(R.id.char_haircolor);
        skinColor = (TextView)layoutView.findViewById(R.id.char_skincolor);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            characterData = bundle.getParcelable("CharacterData");
        }
        if(characterData != null){
            if(characterData.getName() != null){
                name.setText(characterData.getName());
            }
            if(characterData.getGender() != null){
                gender.setText(characterData.getGender());
            }
            if(characterData.getHeight() != null){
                height.setText(characterData.getHeight() + "cm");
            }
            if(characterData.getMass() != null){
                mass.setText(characterData.getMass() + "kg");
            }
            if(characterData.getBirthYear() != null){
                birthYear.setText(characterData.getBirthYear());
            }
            if(characterData.getEyeColor() != null){
                eyeColor.setText(characterData.getEyeColor());
            }
            if(characterData.getHairColor() != null){
                hairColor.setText(characterData.getHairColor());
            }
            if(characterData.getSkinColor() != null){
                skinColor.setText(characterData.getSkinColor());
            }
        }
        return layoutView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
