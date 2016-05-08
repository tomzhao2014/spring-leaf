package com.tom.framework.util.excel;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/17 0017
 * Time: 18:29
 */

import org.apache.commons.logging.Log;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XLSUtil
{
    public static final String DATE_FORMATTER = "yyyy-MM-dd";
    private static final Log logger = org.apache.commons.logging.LogFactory.getLog(XLSUtil.class);
    private static Workbook wb;

    public static void parse(InputStream is)
    {
        try
        {
            wb = WorkbookFactory.create(is);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (InvalidFormatException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void parse(String xlsFileName)
    {
        try {
            InputStream is = new FileInputStream(xlsFileName);
            wb = WorkbookFactory.create(is);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (InvalidFormatException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static Workbook getBook() {
        return wb;
    }

    public static int getRowCount(int sheetIndex) {
        return wb.getSheetAt(sheetIndex).getLastRowNum();
    }

    public static List<CellData> getSheetCaption(int sheetIndex)
    {
        List caption_list = new ArrayList();
        Sheet sheet = wb.getSheetAt(sheetIndex);
        if (sheet == null) {
            return caption_list;
        }
        if (sheet.getLastRowNum() <= 0) {
            return caption_list;
        }
        Row caption_row = sheet.getRow(0);
        for (int i = 0; i < caption_row.getLastCellNum(); i++) {
            Cell item = caption_row.getCell(i);
            CellData cellData = new CellData(item.getColumnIndex(), getValue(item));
            caption_list.add(cellData);
        }
        return caption_list;
    }

    public static List<String> getRowDataAt(int sheetIndex, int rowIndex)
    {
        List rowDatas = new ArrayList();
        Sheet sheet = wb.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell eachCell = row.getCell(i);
            String data = getValue(eachCell);
            rowDatas.add(data);
        }
        return rowDatas;
    }

    public static List<RowData> getRowDatas(int sheetIndex, int endRow)
    {
        List rowDatas = new ArrayList();
        Sheet sheet = wb.getSheetAt(sheetIndex);
        for (int index = 1; index <= endRow; index++) {
            Row row = sheet.getRow(index);
            if (row == null)
            {
                continue;
            }
            RowData eachRowData = new RowData();
            eachRowData.setRowIndex(row.getRowNum());
            List rowCellDatas = new ArrayList();
            for (int i = 0; i < row.getLastCellNum(); i++) {
                Cell eachCell = row.getCell(i);
                int columIndex = 0;
                Object data = null;

                if (eachCell == null) {
                    columIndex = i;
                    data = "";
                } else {
                    data = getCellString(eachCell);
                    if (data == null) {
                        data = "";
                    }
                    columIndex = eachCell.getColumnIndex();
                }
                CellData cellData = new CellData(columIndex, data);
                rowCellDatas.add(cellData);
            }
            eachRowData.setCells(rowCellDatas);
            rowDatas.add(eachRowData);
        }
        return rowDatas;
    }

    public static String getCellData(int sheetIndex, int rowIndex, int columIndex)
    {
        Sheet sheet = wb.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(columIndex);
        return getValue(cell);
    }

    public static List getDatasInSheet(int sheetNumber) {
        List result = new ArrayList();

        Sheet sheet = wb.getSheetAt(sheetNumber);

        int rowCount = sheet.getLastRowNum();
        if (rowCount < 1) {
            return result;
        }

        for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++)
        {
            Row row = sheet.getRow(rowIndex);
            if (null != row) {
                List rowData = new ArrayList();

                int cellCount = row.getLastCellNum();

                for (short cellIndex = 0; cellIndex < cellCount; cellIndex = (short)(cellIndex + 1)) {
                    Cell cell = row.getCell(cellIndex);

                    Object cellStr = getCellString(cell);

                    rowData.add(cellStr);
                }
                result.add(rowData);
            }
        }

        return result;
    }

    private static Object getCellString(Cell cell) {
        Object result = null;
        if (cell != null)
        {
            int cellType = cell.getCellType();
            switch (cellType) {
                case 1:
                    result = cell.getRichStringCellValue().getString();
                    break;
                case 0:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date dateValue = cell.getDateCellValue();
                        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
                        result = dataFormat.format(dateValue);
                    } else {
                        result = Double.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case 2:
                    cell.setCellType(1);
                    result = cell.getStringCellValue();
                    if (result == null) break;
                    result = ((String)result).replaceAll("#N/A", "").trim(); break;
                case 4:
                    result = Boolean.valueOf(cell.getBooleanCellValue());
                    break;
                case 3:
                    break;
                case 5:
                    break;
            }

        }

        return result;
    }

    public static int getSheetCount()
    {
        return wb.getNumberOfSheets();
    }

    private static String getValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == 4)
            return String.valueOf(cell.getBooleanCellValue());
        if (cell.getCellType() == 0) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date dateValue = cell.getDateCellValue();
                DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dataFormat.format(dateValue);
            }
            return String.valueOf(cell.getNumericCellValue());
        }

        return String.valueOf(cell.getStringCellValue());
    }
}
