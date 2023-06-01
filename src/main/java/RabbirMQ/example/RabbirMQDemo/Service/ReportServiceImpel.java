package RabbirMQ.example.RabbirMQDemo.Service;

import RabbirMQ.example.RabbirMQDemo.DTOs.DailyReportDto;
import RabbirMQ.example.RabbirMQDemo.Repository.DailyReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpel  implements ReportService{

   final DailyReportRepository  dailyReportRepository;

   @Autowired
    public ReportServiceImpel(DailyReportRepository reportReportService) {
       this.dailyReportRepository = reportReportService;
    }

    @Override
    public List<DailyReportDto> getDailyReport(String reportDate) {
       var reportList= dailyReportRepository.getDailyReport(reportDate);
       return DailyReportDto.ToDto(reportList);
    }
}
