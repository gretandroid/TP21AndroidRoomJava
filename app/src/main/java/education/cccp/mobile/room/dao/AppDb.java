package education.cccp.mobile.room.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import education.cccp.mobile.room.model.User;

@Database(
        entities = {User.class},
        version = 1
)
@TypeConverters(
        value = {DateConverter.class}
)
public abstract class AppDb extends RoomDatabase {
    public static final String DB_NAME = "application.db";
    private static volatile AppDb instance;
    private static final Object LOCK = new Object();

    public abstract UserDao userDao();

    public static AppDb getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDb.class,
                            DB_NAME).build();

                }
            }
        }
        return instance;
    }
}