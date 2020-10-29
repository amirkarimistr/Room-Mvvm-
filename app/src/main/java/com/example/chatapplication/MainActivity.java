package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;


import com.example.chatapplication.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private PersonAdapter personAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        personAdapter = new PersonAdapter();
        mBinding.rv.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rv.setAdapter(personAdapter);

        PersonViewModel viewModel =
                 ViewModelProviders.of(MainActivity.this).get(PersonViewModel.class);

        viewModel.getPersonList().observe(this, people -> {
            System.out.printf("PersonList size is %d%n", people.size());

            personAdapter.insertPerson(people);
            if (people.size() > 0)
                mBinding.rv.smoothScrollToPosition(personAdapter.getItemCount() -1);

        });

        mBinding.btn.setOnClickListener(v -> {
            if (mBinding.nameEdit.getText().length() == 0 || mBinding.cityEdit.getText().length() == 0){
                mBinding.nameLayout.setError("Enter your name");
                mBinding.cityLayout.setError("Enter your city");
            }else {
                mBinding.cityLayout.setError(null);
                mBinding.nameLayout.setError(null);

                String name, city;
                name = mBinding.nameEdit.getText().toString().trim();
                city = mBinding.cityEdit.getText().toString().trim();

                Person person = new Person(name, city);
                viewModel.insertPerson(person);
            }
        });
    }
}