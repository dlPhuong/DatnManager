package com.phuong.datn.service;


import com.phuong.datn.domain.Authority;
import com.phuong.datn.domain.Student;
import com.phuong.datn.domain.User;
import com.phuong.datn.repository.AuthorityRepository;
import com.phuong.datn.repository.StudentRepository;
import com.phuong.datn.repository.UserRepository;
import com.phuong.datn.security.AuthoritiesConstants;
import io.github.jhipster.security.RandomUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    public List<Student> getListFromExcel(MultipartFile file) throws IOException {
        List<Student> tempStudentList = new ArrayList<Student>();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Student tempStudent = new Student();
            XSSFRow row = worksheet.getRow(i);

            int masv = (int) row.getCell(0).getNumericCellValue();
            Date birth = row.getCell(2).getDateCellValue();
            int phone = (int) row.getCell(4).getNumericCellValue();
            int idclass = (int) row.getCell(5).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            String address = row.getCell(3).getStringCellValue();
            tempStudent.setMaSinhVien("" + masv);
            tempStudent.setName(name);
            tempStudent.setBirthDay(convertDateToString(birth));
            tempStudent.setAddress(address);
            tempStudent.setPhone("+84" + phone);
            tempStudent.setIdClass(idclass + "");
            tempStudent.setCreatedBy("admin");
            if (masv != 0) {
                tempStudentList.add(tempStudent);
            }
        }
        return tempStudentList;
    }

    public String convertDateToString(Date date) {
        if (date != null) {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String dateToString = df.format(date);
            return (dateToString);
        }
        return "";
    }

    public void removeStudent(List<Student> studentList) {
        for (Student student : studentList) {
            student.setStatus("không cho phép bảo vệ");
            studentRepository.save(student);
        }
    }

    public void saveStudentAuth(Student student) {
        User newUser = new User();
        String userName = VNCharacterUtils.removeAccent(student.getName()).replaceAll("\\s", "");
        String encryptedPassword = passwordEncoder.encode(userName);
        newUser.setLogin(userName);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setLastName(student.getName());
        newUser.setImageUrl(student.getImage());
        newUser.setLangKey("en");
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
    }

}
