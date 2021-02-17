package com.example.synchronyproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import models.CountryInfo;


public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>
{
    private ArrayList<CountryInfo>  countries;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){mListener = listener;}

    public static class CountryViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name;

        public CountryViewHolder(@NonNull View itemView, OnItemClickListener listener)
        {
            super(itemView);
            name = itemView.findViewById(R.id.countryName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public CountriesAdapter(ArrayList<CountryInfo> countries) {this.countries = countries;}

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_view, parent, false);
        return new CountryViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position)
    {
        CountryInfo countryInfo = countries.get(position);
        holder.name.setText(countryInfo.getName());
    }

    @Override
    public int getItemCount() { return countries.size();}

}

