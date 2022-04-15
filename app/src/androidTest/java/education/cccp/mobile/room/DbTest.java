package education.cccp.mobile.room;


import static org.junit.Assert.assertEquals;
import static education.cccp.mobile.room.TestData.users;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import education.cccp.mobile.room.dao.AppDb;
import education.cccp.mobile.room.dao.UserDao;
import education.cccp.mobile.room.model.User;

@RunWith(AndroidJUnit4.class)
public class DbTest {
    private AppDb db;
    private UserDao userDao;
    private User user;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDb.class).build();
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
        assertEquals(users.size(),5);
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
        List<User> resuList = userDao.findAll().getValue();
        for (int i = 0; i < users.size(); i++) {
            assertEquals(resuList.get(i), users.get(i));
        }
    }


    @After
    public void destroy() {
        db.close();
    }
}
