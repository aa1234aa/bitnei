package test.course.model;

import lombok.Data;

/**
 * xx模块
 */
@Data
public class LoginCase {
    private int id;
    private  String username;
    private  String password;
    private  String expected;
}
