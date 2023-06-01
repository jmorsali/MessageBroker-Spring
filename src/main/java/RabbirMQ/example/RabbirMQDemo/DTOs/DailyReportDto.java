package RabbirMQ.example.RabbirMQDemo.DTOs;

import RabbirMQ.example.RabbirMQDemo.Entity.DailyReport;

import java.util.ArrayList;
import java.util.List;

public class DailyReportDto {
    Long id;
    String reportDate;
    String codeListCode;
    Integer TotalItemCount;

    public static List<DailyReportDto> ToDto(List<DailyReport> reportList) {
        var result=new ArrayList<DailyReportDto>();
        for (var report: reportList)
            result.add(ToDto(report));
        return result;
    }

    private static DailyReportDto ToDto(DailyReport report) {
        if(report==null)
            return null;
        var reportDto=new DailyReportDto();
        reportDto.setId(report.getId());
        reportDto.setReportDate(report.getReportDate());
        reportDto.setCodeListCode(report.getCodeListCode());
        reportDto.setTotalItemCount(report.getTotalItemCount());
        return reportDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getCodeListCode() {
        return codeListCode;
    }

    public void setCodeListCode(String codeListCode) {
        this.codeListCode = codeListCode;
    }

    public Integer getTotalItemCount() {
        return TotalItemCount;
    }

    public void setTotalItemCount(Integer totalItemCount) {
        TotalItemCount = totalItemCount;
    }
}
