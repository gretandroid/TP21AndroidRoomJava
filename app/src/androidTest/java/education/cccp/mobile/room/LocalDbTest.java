//package education.cccp.mobile.room;
//
//
//import static androidx.room.Room.inMemoryDatabaseBuilder;
//import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
//import static org.junit.Assert.assertEquals;
//
//import android.content.Context;
//
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.List;
//
//import education.cccp.mobile.room.dao.AppDb;
//import education.cccp.mobile.room.dao.UserDao;
//import education.cccp.mobile.room.model.User;
//
//@RunWith(AndroidJUnit4.class)
//public class LocalDbTest {
//    private AppDb db;
//    private UserDao userDao;
//
//    @Before
//    public void setUp() {
//        Context context = getInstrumentation()
//                .getTargetContext();
//        db = inMemoryDatabaseBuilder(context,
//                AppDb.class)
//                .build();
//        userDao = db.userDao();
//    }
//
//    @Test
//    public void canary() {
//    }
//
//
//    @Test
//    public void test_save_with_correct_user() {
//        assertEquals(0, userDao.count());
//        userDao.save(Data.findAll().get(0));
//        assertEquals(1, userDao.count());
//    }
//
//    @Test
//    public void test_save_all() {
//        assertEquals(Data.findAll().size(), 5);
//        assertEquals(0, userDao.count());
//        userDao.saveAll(Data.findAll());
//        System.out.println(userDao.count());
//        assertEquals(Data.findAll().size(), userDao.count());
//    }
//
//    @Test
//    public void test_delete() {
//        assertEquals(0, userDao.count());
//        userDao.saveAll(Data.findAll());
//        assertEquals(Data.findAll().size(), userDao.count());
//        userDao.delete(Data.findAll().get(0));
//        assertEquals(Data.findAll().size() - 1, userDao.count());
//    }
//
//
//    @Test
//    public void test_delete_all() {
//        assertEquals(0, userDao.count());
//        userDao.saveAll(Data.findAll());
//        assertEquals(Data.findAll().size(), userDao.count());
//        userDao.deleteAll();
//        assertEquals(0, userDao.count());
//    }
//
//    @Test
//    public void test_find_all() {
//        assertEquals(0, userDao.count());
//        userDao.saveAll(Data.findAll());
//        assertEquals(Data.findAll().size(), userDao.count());
//        List<User> resuList = userDao.findAll();
//        assertEquals(resuList.get(0), Data.findAll().get(0));
//        assertEquals(resuList.get(1), Data.findAll().get(1));
//        assertEquals(resuList.get(2), Data.findAll().get(2));
//        assertEquals(resuList.get(3), Data.findAll().get(3));
//        assertEquals(resuList.get(4), Data.findAll().get(4));
//    }
//
//
//    @After
//    public void destroy() {
//        db.close();
//    }
//}
