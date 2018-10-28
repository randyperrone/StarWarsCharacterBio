package com.example.randyperrone.starwarscharacterbio.Model;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import static com.example.randyperrone.starwarscharacterbio.Model.Consts.API_KEYWORD_BIRTHYEAR;
import static com.example.randyperrone.starwarscharacterbio.Model.Consts.API_KEYWORD_EYECOLOR;
import static com.example.randyperrone.starwarscharacterbio.Model.Consts.API_KEYWORD_GENDER;
import static com.example.randyperrone.starwarscharacterbio.Model.Consts.API_KEYWORD_HAIRCOLOR;
import static com.example.randyperrone.starwarscharacterbio.Model.Consts.API_KEYWORD_HEIGHT;
import static com.example.randyperrone.starwarscharacterbio.Model.Consts.API_KEYWORD_MASS;
import static com.example.randyperrone.starwarscharacterbio.Model.Consts.API_KEYWORD_NAME;
import static com.example.randyperrone.starwarscharacterbio.Model.Consts.API_KEYWORD_NEXT;
import static com.example.randyperrone.starwarscharacterbio.Model.Consts.API_KEYWORD_RESULTS;
import static com.example.randyperrone.starwarscharacterbio.Model.Consts.API_KEYWORD_SKINCOLOR;

public class CharacterDataService{
    private static final String TAG = "CharacterDataService";
    private List<CharacterData> characterDataList;
    private RequestQueue requestQueue;
    private Boolean flagForNextAPIPage;

    public CharacterDataService(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        flagForNextAPIPage = false;
    }

    public void downloadCharacterData(String URL, final VolleyCallBack callBack){
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                characterDataList = new ArrayList<>();

                if(response != null){
                    try {
                        String isNullValue = response.getString(API_KEYWORD_NEXT);
                        if(isNullValue == null){
                            flagForNextAPIPage = true;
                        }
                        JSONArray array = response.getJSONArray(API_KEYWORD_RESULTS);
                        if(array != null){
                            int i;
                            for(i = 0; i < array.length(); i++){
                                JSONObject jsonObject = array.getJSONObject(i);
                                if(jsonObject != null){
                                    String name = jsonObject.getString(API_KEYWORD_NAME);
                                    String gender = jsonObject.getString(API_KEYWORD_GENDER);
                                    String height = jsonObject.getString(API_KEYWORD_HEIGHT);
                                    String mass = jsonObject.getString(API_KEYWORD_MASS);
                                    String birth = jsonObject.getString(API_KEYWORD_BIRTHYEAR);
                                    String eye = jsonObject.getString(API_KEYWORD_EYECOLOR);
                                    String hair = jsonObject.getString(API_KEYWORD_HAIRCOLOR);
                                    String skin = jsonObject.getString(API_KEYWORD_SKINCOLOR);
                                    if(name != null && gender != null && height != null && mass != null && birth != null && eye != null && hair != null && skin != null){
                                        CharacterData characterData = new CharacterData(name, gender, height, mass, birth, eye, hair, skin);
                                        characterDataList.add(characterData);
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception e){
                        Log.e(TAG, "error with Volley: " + e.toString());
                    }
                    Log.i(TAG, "Volley Success");
                    callBack.onSuccess(characterDataList, flagForNextAPIPage);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "errorDetected: " + error);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public interface VolleyCallBack{
        void onSuccess(List<CharacterData> characterList, Boolean flagForLastAPIPage);
    }
}
