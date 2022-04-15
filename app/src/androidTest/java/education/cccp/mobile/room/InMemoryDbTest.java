package education.cccp.mobile.room;


import static androidx.room.Room.inMemoryDatabaseBuilder;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

import static education.cccp.mobile.room.Data.findAll;

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
public class InMemoryDbTest {
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

    @After
    public void destroy() {
        db.close();
    }

    @Test
    public void canary() {
    }


    @Test
    public void test_save_with_correct_user() {
        assertEquals(0, userDao.count());
        userDao.save(findAll().get(0));
        assertEquals(1, userDao.count());
    }

    @Test
    public void test_save_all() {
        assertEquals(findAll().size(), 5);
        assertEquals(0, userDao.count());
        userDao.saveAll(findAll());
        System.out.println(userDao.count());
        assertEquals(findAll().size(), userDao.count());
    }

    @Test
    public void test_delete() {
        assertEquals(0, userDao.count());
        userDao.saveAll(findAll());
        assertEquals(findAll().size(), userDao.count());
        userDao.delete(findAll().get(0));
        assertEquals(findAll().size() - 1, userDao.count());
    }


    @Test
    public void test_delete_all() {
        assertEquals(0, userDao.count());
        userDao.saveAll(findAll());
        assertEquals(findAll().size(), userDao.count());
        userDao.deleteAll();
        assertEquals(0, userDao.count());
    }

    @Test
    public void test_find_all() {
        assertEquals(0, userDao.count());
        userDao.saveAll(findAll());
        assertEquals(findAll().size(), userDao.count());
        List<User> resultList = userDao.findAll();
        for (int i = 0; i < findAll().size(); i++)
            assertEquals(resultList.get(i), findAll().get(i));
    }
}
