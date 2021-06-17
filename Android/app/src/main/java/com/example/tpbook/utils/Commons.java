package com.example.tpbook.utils;

import com.example.tpbook.model.data.Report;
import com.example.tpbook.model.data.Student;
import com.example.tpbook.model.data.Teacher;
import com.example.tpbook.model.data.Topic;
import com.example.tpbook.model.data.User;

import java.util.List;

public class Commons {
    public static String auth ="";
    public static final String API_BASE_URL = "http://172.30.128.1:8080/";

    public static Teacher teacher = null;
    public static Student student = null;
    public static Topic topic = null;

    public static List<Topic> topicList = null;

    public static Report report = null;

    public static User user = null;

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String TEACHER = "ROLE_TEACHER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
}
