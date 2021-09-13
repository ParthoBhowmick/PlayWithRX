package com.example.rxisfun;

import android.os.Bundle;

import com.example.rxisfun.Introduction.CommonTask;
import com.example.rxisfun.Introduction.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rxisfun.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CommonTask.createTasksList();

        Observable<Task> taskObservable = Observable // create a new Observable object
                .fromIterable(CommonTask.getTaskList()) // apply 'fromIterable' operator
                .subscribeOn(Schedulers.io()) // designate worker thread (background)
                .observeOn(AndroidSchedulers.mainThread()); // designate observer thread (main thread)


        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("subscription", "got it");
            }
            @Override
            public void onNext(Task task) { // run on main thread
                Log.e("observing task", "onNext : " + task.getDescription());
            }
            @Override
            public void onError(Throwable e) {
                Log.e("observing task", "onError : " + e.toString());
            }
            @Override
            public void onComplete() {
                Log.e("observing task complete", " true");
            }
        });

    }


}