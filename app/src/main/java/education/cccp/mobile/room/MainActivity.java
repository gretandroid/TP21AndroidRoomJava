package education.cccp.mobile.room;

import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static education.cccp.mobile.room.Data.findAll;
import static education.cccp.mobile.room.R.layout.activity_main;
import static education.cccp.mobile.room.dao.AppDb.getInstance;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executor;

import education.cccp.mobile.room.dao.AppDb;
import education.cccp.mobile.room.dao.UserDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        AppDb db = getInstance(this);
        UserDao userDao = db.userDao();
        Executor executor = newSingleThreadExecutor();
        executor.execute(() -> userDao.saveAll(findAll()));
        executor.execute(() ->
                Log.d(this.getClass().getSimpleName(),
                        userDao.findAll().toString()));
    }
}