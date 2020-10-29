package com.example.chatapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonRepository {
    private PersonDao personDao;
    private LiveData<List<Person>> personList;

    public PersonRepository(Application context){
        PersonDatabase db = PersonDatabase.getInstance(context);
        personDao = db.personDao();
        personList = personDao.getPersonList();

    }

    public void insertPerson(Person person){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Thread t = new Thread(() -> {
            personDao.insertPerson(person);
        });
        executorService.execute(t);
        executorService.shutdown();
    }

    public void deletePerson(Person person){
        personDao.deletePerson(person);
    }

    public void updatePerson(Person person){
        personDao.updatePerson(person);
    }

    public LiveData<List<Person>> getPersonList(){
        return personDao.getPersonList();
    }
}
