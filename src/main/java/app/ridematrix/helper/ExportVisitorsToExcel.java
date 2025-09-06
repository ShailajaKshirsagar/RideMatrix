package app.ridematrix.helper;

import app.ridematrix.dto.VisitorExcelDataDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ExportVisitorsToExcel
{
    public static void visitorsToExcel(List<VisitorExcelDataDTO> visitors, FileOutputStream fos) throws IOException{

        String coumns[] = {"Visitor Name", "Vehicle Number", "Visitor Type","Phone Num","Vehicle Name","Visit Purpose",
                "Time In", "Time out ", "Visit Duration","Flat No", "Resident Name"};

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Visitors_History_log_" + LocalDate.now());

            // Header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < coumns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(coumns[i]);
            }


            //Now add data in rows
            int rowIdx = 1;
            for(VisitorExcelDataDTO visitor : visitors ) {

                Row row1 = sheet.createRow(rowIdx++);
                row1.createCell(0).setCellValue(visitor.getVisitorName());
                row1.createCell(1).setCellValue(visitor.getVehicleRegNum());
                row1.createCell(2).setCellValue(visitor.getVisitorType().ordinal());
                row1.createCell(3).setCellValue(visitor.getPhoneNum());
                row1.createCell(4).setCellValue(visitor.getVehicleName());
                row1.createCell(5).setCellValue(visitor.getVisitPurpose());
                row1.createCell(6).setCellValue(visitor.getTimeIn());
                row1.createCell(7).setCellValue(visitor.getTimeOut());
                row1.createCell(8).setCellValue(visitor.getVisitDuration());
                row1.createCell(9).setCellValue(visitor.getFlatNo());
                row1.createCell(10).setCellValue(visitor.getResidentName());
            }
                //write data in output stream
                workbook.write(fos);

            } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
