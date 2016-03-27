package com.qiktone.entity.vo;

import com.qiktone.entity.Company;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by tom on 2016/3/27.
 */
public class CompanyQuery extends Company {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDateEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date availableDateEnd;



    public Date getStartDateEnd() {
        return startDateEnd;
    }

    public void setStartDateEnd(Date startDateEnd) {
        this.startDateEnd = startDateEnd;
    }

    public Date getAvailableDateEnd() {
        return availableDateEnd;
    }

    public void setAvailableDateEnd(Date availableDateEnd) {
        this.availableDateEnd = availableDateEnd;
    }

    public boolean isEmpty(){
        if(getName()==null&&getContact()==null&&getType()==null && getAvailableDateEnd()==null&&getAvailableDate()==null&&getStartDateEnd()==null&&getStartDate()==null){
            return true;
        }else{
            return false;
        }
    }
}
