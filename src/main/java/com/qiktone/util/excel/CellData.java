package com.qiktone.util.excel;

/**
 * Created with IntelliJ IDEA.
 * Anthor: Tom Zhao
 * Date: 2016/3/17 0017
 * Time: 18:28
 */
public class CellData
{
    private int index;
    private Object value;

    public CellData()
    {
    }

    public CellData(int index, Object value)
    {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
