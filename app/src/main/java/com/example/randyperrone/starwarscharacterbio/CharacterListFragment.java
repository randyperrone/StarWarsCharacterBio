package com.example.randyperrone.starwarscharacterbio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randyperrone.starwarscharacterbio.Model.CharacterData;
import com.example.randyperrone.starwarscharacterbio.Model.CharacterDataService;
import com.example.randyperrone.starwarscharacterbio.RecyclerView.CharacterListAdapter;
import com.example.randyperrone.starwarscharacterbio.RecyclerView.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CharacterListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CharacterListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CharacterListFragment extends Fragment {
    private static final String TAG = "CharacterListFragment";
    private View layoutView;
    private List<CharacterData> characterDataList;
    private RecyclerView recyclerView;
    private CharacterListAdapter mAdapter;
    private CharacterDataService downloadCharacterDataService;
    private EndlessRecyclerViewScrollListener scrollListener;
    private GridLayoutManager gridLayoutManager;
    private Handler handler;

    private final String BASE_URL = "https://swapi.co/api/people/?page=";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public CharacterListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CharacterListFragment.
     */
    public static CharacterListFragment newInstance(String param1, String param2) {
        CharacterListFragment fragment = new CharacterListFragment();
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
        loadInitialData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initialization
        layoutView = inflater.inflate(R.layout.fragment_character_list, container, false);
        handler = new Handler();
        characterDataList = new ArrayList<>();
        recyclerView = (RecyclerView)layoutView.findViewById(R.id.character_list_recyclerview);
        mAdapter = new CharacterListAdapter(characterDataList);
        recyclerView.setAdapter(mAdapter);
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        gridLayoutManager = new GridLayoutManager(getActivity(), gridColumnCount);
        recyclerView.setLayoutManager(gridLayoutManager);

        //Endless Scrolling
        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                String URL = buildURL(Integer.toString(page));
                downloadCharacterDataService.downloadCharacterData(URL, new CharacterDataService.VolleyCallBack() {
                    @Override
                    public void onSuccess(List<CharacterData> characterList, Boolean flagForLastAPIPage) {
                        Log.i(TAG, "downloadData onSuccess");
                        characterDataList.addAll(characterList);
                        final int curSize = mAdapter.getItemCount();
                        mAdapter.notifyItemRangeInserted(curSize, characterDataList.size() - 1);
                        if(flagForLastAPIPage){
                            scrollListener.resetState();
                        }
                    }
                });
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
        return layoutView;
    }

    private void loadInitialData(){
        downloadCharacterDataService = new CharacterDataService(getActivity().getApplicationContext());
        String URL = buildURL("1");
        downloadCharacterDataService.downloadCharacterData(URL, new CharacterDataService.VolleyCallBack() {
            @Override
            public void onSuccess(final List<CharacterData> characterList, Boolean flagForLastAPIPage) {
                Log.i(TAG, "downloadData onSuccess");
                characterDataList.addAll(characterList);
                final int curSize = mAdapter.getItemCount();
                mAdapter.notifyItemRangeInserted(curSize, characterDataList.size() - 1);

            }
        });
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

    private String buildURL(String pageNum){
        return BASE_URL + pageNum;
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
