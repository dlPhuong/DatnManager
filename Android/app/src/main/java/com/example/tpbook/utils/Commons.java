package com.example.tpbook.utils;

import com.example.tpbook.model.data.Student;
import com.example.tpbook.model.data.Teacher;
import com.example.tpbook.model.data.Topic;
import com.example.tpbook.model.data.User;

public class Commons {
    public static String auth ="";

    public static Teacher teacher = null;
    public static Student student = null;
    public static Topic topic = null;

    public static User user = null;

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String TEACHER = "ROLE_TEACHER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
}
