package com.phuong.datn.service;


import com.phuong.datn.domain.Teacher;
import com.phuong.datn.domain.Topic;
import com.phuong.datn.repository.TeacherRepository;
import com.phuong.datn.repository.TopicRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TopicService {
    @Autowired
    TopicRepository topicRepository;

    public List<Topic> getListFromExcel(MultipartFile file) throws IOException {
        List<Topic> tempStudentList = new ArrayList<Topic>();

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Topic topic = new Topic();
            XSSFRow row = worksheet.getRow(i);

            String topicname = row.getCell(0).getStringCellValue();
            String description = row.getCell(1).getStringCellValue();

            topic.setTopicName(topicname);
            topic.setDescription(description);

            if (!topicname.isEmpty()) {
                tempStudentList.add(topic);
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

}
