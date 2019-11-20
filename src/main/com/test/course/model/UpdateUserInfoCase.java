package test.course.model;

import lombok.Data;

/**
 * xx模块
 */
@Data
public class UpdateUserInfoCase {
    private int id;
    private int userid;
    private String username;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    private String expected;

}
