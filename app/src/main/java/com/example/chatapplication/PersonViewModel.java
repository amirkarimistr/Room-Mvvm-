package com.example.chatapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {
    private PersonRepository repository;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        repository = new PersonRepository(application);
    }

    public void insertPerson(Person person){
        repository.insertPerson(person);
    }

    public void deletePerson(Person person){
        repository.deletePerson(person);
    }

    public void updatePerson(Person person){
        repository.updatePerson(person);
    }

    public LiveData<List<Person>> getPersonList(){
        return repository.getPersonList();
    }
}
