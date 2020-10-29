package com.example.chatapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.databinding.ItemPersonBinding;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private List<Person> personList;
    private ItemPersonBinding personBinding;

    public PersonAdapter() {
       personList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);

        personBinding = ItemPersonBinding.bind(view);

        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personList.get(position);

        personBinding.itemName.setText(person.getName());
        personBinding.itemCity.setText(person.getCity());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public void insertPerson(List<Person> personList){
        this.personList = personList;
        notifyDataSetChanged();
    }
}
