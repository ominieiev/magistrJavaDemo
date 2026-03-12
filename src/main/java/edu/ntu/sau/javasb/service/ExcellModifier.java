package edu.ntu.sau.javasb.service;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcellModifier {

    public byte[] modifyExcel(InputStream input) throws IOException {


        Workbook inputWorkbook = WorkbookFactory.create(input);

        Sheet sheet = inputWorkbook.getSheetAt(0);

        List<String> studentNames = new ArrayList<>();
        for (Row row : sheet) {
            studentNames.add(row.getCell(0).getStringCellValue());
        }
        inputWorkbook.close();

        Workbook resultWorkbook = new XSSFWorkbook();
        Sheet resultSheet = resultWorkbook.createSheet("Result");
        int i = 0;

        Row resultRow = resultSheet.createRow(0);
        for (String studentName : studentNames) {
            resultRow.createCell(i).setCellValue(studentName);
            i++;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        resultWorkbook.write(byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

}
