package education.cccp.mobile.room.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import education.cccp.mobile.room.model.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user u WHERE lower(u.login)=lower(:login)")
    LiveData<List<User>> findAllByLogin(String login);

    @Query("SELECT * FROM user WHERE LOWER(email) = LOWER(:email)")
    User findOneByEmailIgnoreCase(String email);

    @Insert(onConflict = REPLACE)
    void save(User user);

    @Insert(onConflict = REPLACE)
    void saveAll(List<User> users);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user WHERE user_id = :userId")
    void deleteById(Long userId);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT count(*) FROM user")
    int count();

    @Query("SELECT * FROM user order by user_id ASC")
    LiveData<List<User>> findAll();
}
