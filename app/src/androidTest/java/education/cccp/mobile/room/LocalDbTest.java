package education.cccp.mobile.room;


import static androidx.room.Room.inMemoryDatabaseBuilder;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static education.cccp.mobile.room.TestData.users;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import education.cccp.mobile.room.dao.AppDb;
import education.cccp.mobile.room.dao.UserDao;
import education.cccp.mobile.room.model.User;

@RunWith(AndroidJUnit4.class)
public class LocalDbTest {
    private AppDb db;
    private UserDao userDao;

    @Before
    public void setUp() {
        Context context = getInstrumentation()
                .getTargetContext();
        db = inMemoryDatabaseBuilder(context,
                AppDb.class)
                .build();
        userDao = db.userDao();
    }

    @Test
    public void canary() {
    }


    @Test
    public void test_save_with_correct_user() {
        assertEquals(0, userDao.count());
        userDao.save(users.get(0));
        assertEquals(1, userDao.count());
    }

    @Test
    public void test_save_all() {
        assertEquals(users.size(), 5);
        assertEquals(0, userDao.count());
        userDao.saveAll(users);
        System.out.println(userDao.count());
        assertEquals(users.size(), userDao.count());
    }

    @Test
    public void test_delete() {
        assertEquals(0, userDao.count());
        userDao.saveAll(users);
        assertEquals(users.size(), userDao.count());
        userDao.delete(users.get(0));
        assertEquals(users.size() - 1, userDao.count());
    }


    @Test
    public void test_delete_all() {
        assertEquals(0, userDao.count());
        userDao.saveAll(users);
        assertEquals(users.size(), userDao.count());
        userDao.deleteAll();
        assertEquals(0, userDao.count());
    }

    @Test
    public void test_find_all() {
        assertEquals(0, userDao.count());
        userDao.saveAll(users);
        assertEquals(users.size(), userDao.count());
        List<User> resuList = userDao.findAll();
        assertEquals(resuList.get(0), users.get(0));
        assertEquals(resuList.get(1), users.get(1));
        assertEquals(resuList.get(2), users.get(2));
        assertEquals(resuList.get(3), users.get(3));
        assertEquals(resuList.get(4), users.get(4));
    }


    @After
    public void destroy() {
        db.close();
    }
}
