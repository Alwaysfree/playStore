package  com.player.dao;

import com.player.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper{
    int checkUsername(String username);
    int checkEmail(String email);
    int checkEmailByUserId(@Param("email")String email,@Param("userId") Integer UserId);
    int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);
    int checkPassword(@Param("password")String password,@Param("userId")String userId);
    int deleteUserById(Integer id);
    int addUser(User user);
    User checkLogin(@Param("username")String username,@Param("password")String password);
    String selectQuestionByUsername(String username);
    int udatePasswordByUsername(@Param("username")String username,@Param("passwordNew")String passwordNew);
    int updateByPrimaryKey(User user);
    User selectById(Integer id);
}