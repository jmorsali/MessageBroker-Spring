package com.blueboders.productcodebroker.service;

import com.blueboders.productcodebroker.dtos.DailyReportDto;

import java.util.Date;
import java.util.List;

public interface ReportService {
     List<DailyReportDto> getDailyReport(Date reportDate);
}
