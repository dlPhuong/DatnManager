package com.phuong.datn.service;


import com.phuong.datn.domain.Student;
import com.phuong.datn.domain.Teacher;
import com.phuong.datn.repository.StudentRepository;
import com.phuong.datn.repository.TeacherRepository;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public List<Teacher> getListFromExcel(MultipartFile file) throws IOException {
        List<Teacher> tempStudentList = new ArrayList<Teacher>();

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Teacher tempStudent = new Teacher();
            XSSFRow row = worksheet.getRow(i);

            String name = row.getCell(0).getStringCellValue();
            Date birth = row.getCell(1).getDateCellValue();
            String address = row.getCell(2).getStringCellValue();
            int phone = (int) row.getCell(3).getNumericCellValue();
            int idclass = (int) row.getCell(4).getNumericCellValue();

            tempStudent.setNameTeacher("" + name);
            tempStudent.setBirthdate(convertDateToString(birth));
            tempStudent.setAddress(address);
            tempStudent.setPhone("+84" + phone);
            tempStudent.setIdClass(idclass + "");
            tempStudent.setCreatedBy("admin");

            if (!name.isEmpty()) {
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

    public void removeStudent(List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            teacherRepository.delete(teacher);
        }
    }

}
