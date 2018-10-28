package com.example.randyperrone.starwarscharacterbio;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CharacterListViewHolder extends RecyclerView.ViewHolder{
    private TextView characterNameTV;

    public CharacterListViewHolder(View itemView) {
        super(itemView);
        characterNameTV = (TextView)itemView.findViewById(R.id.character_cardview_name);
    }

    public void setCharacterName(String characterNameTV) {
        this.characterNameTV.setText(characterNameTV);
    }

}
