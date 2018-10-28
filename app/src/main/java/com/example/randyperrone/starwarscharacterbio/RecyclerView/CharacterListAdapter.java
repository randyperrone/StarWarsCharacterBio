package com.example.randyperrone.starwarscharacterbio.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randyperrone.starwarscharacterbio.CharacterDataFragment;
import com.example.randyperrone.starwarscharacterbio.MainActivity;
import com.example.randyperrone.starwarscharacterbio.Model.CharacterData;
import com.example.randyperrone.starwarscharacterbio.R;

import java.util.List;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListViewHolder>{
    private List<CharacterData> characterDataList;

    public CharacterListAdapter(List<CharacterData> characterDataList) {
        this.characterDataList = characterDataList;
    }

    @NonNull
    @Override
    public CharacterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_name_cardview, parent, false);
        return new CharacterListViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterListViewHolder holder, int position) {
        final CharacterData characterData = characterDataList.get(position);
        String characterName = "";
        if(characterData != null){
            characterName = characterData.getName();
        }
        if(characterName != null){
            holder.setCharacterName(characterName);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharacterDataFragment dataFragment = new CharacterDataFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("CharacterData", characterData);
                dataFragment.setArguments(bundle);
                ((MainActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, dataFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return characterDataList.size();
    }
}
