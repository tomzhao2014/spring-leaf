package com.qiktone.util.excel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/17 0017
 * Time: 18:27
 */
public class RowData
{
    private int rowIndex;
    private List<CellData> cells;

    public RowData()
    {
    }

    public int getRowIndex()
    {
        return this.rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public RowData(List<CellData> cells) {
        this.cells = cells;
    }

    public void setCells(List<CellData> cells) {
        this.cells = cells;
    }

    public List<CellData> getCells() {
        return this.cells;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        for (CellData cell : this.cells) {
            index++;
            sb.append(cell.getIndex());
            sb.append(",");
            sb.append(cell.getValue());
            if (index != this.cells.size()) {
                sb.append(";");
            }
        }
        return sb.toString();
    }
}
