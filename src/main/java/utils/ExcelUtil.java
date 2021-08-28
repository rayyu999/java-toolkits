package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ExcelUtil {

    static final String fileName = "";

    static final String dir = "D:\\SystemFiles\\Desktop\\";

    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(dir+fileName+FileSuffix.XLSX);
        try {
            System.out.println(getColumn(inputStream, 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    enum FileSuffix {
        XLSX(".xlsx");

        String val;
        FileSuffix(String val) {
            this.val = val;
        }
    }

    /**
     * 读取Excel文件指定列的数据
     * @param inputStream 文件InputStream
     * @param columnIndex 读第几列（0开始）
     * @return 对应列的所有值（逗号分割）
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static String getColumn(InputStream inputStream, int columnIndex) throws IOException, InvalidFormatException {
        StringBuilder sb = new StringBuilder();
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (int runNum = 1; runNum <= sheet.getLastRowNum(); ++runNum) {
            Row row = sheet.getRow(runNum);
            int minColIx = row.getFirstCellNum();
            int maxColIx = row.getLastCellNum();
            for (int colIx = minColIx; colIx < maxColIx; ++colIx) {
                Cell cell = row.getCell(colIx);
                if (cell.getColumnIndex() == columnIndex) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    sb.append(cell.getStringCellValue());
                    sb.append(',');
                }
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

}
