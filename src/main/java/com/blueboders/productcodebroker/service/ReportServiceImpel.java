package com.blueboders.productcodebroker.service;

import com.blueboders.productcodebroker.dtos.DailyReportDto;
import com.blueboders.productcodebroker.repository.DailyReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpel  implements ReportService{

   final DailyReportRepository dailyReportRepository;

   @Autowired
    public ReportServiceImpel(DailyReportRepository reportReportService) {
       this.dailyReportRepository = reportReportService;
    }

    @Override
    public List<DailyReportDto> getDailyReport(Date reportDate) {
       var reportList= dailyReportRepository.getDailyReport(reportDate);
       return DailyReportDto.ToDto(reportList);
    }
}
