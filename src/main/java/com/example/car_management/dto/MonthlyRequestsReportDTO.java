package com.example.car_management.dto;

import java.time.YearMonth;

public class MonthlyRequestsReportDTO {
    private YearMonth yearMonth;
    private int request;

    public MonthlyRequestsReportDTO(YearMonth yearMonth, int request) {
        this.yearMonth = yearMonth;
        this.request = request;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }
}
