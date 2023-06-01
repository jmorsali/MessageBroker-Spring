package RabbirMQ.example.RabbirMQDemo.Service;

import RabbirMQ.example.RabbirMQDemo.DTOs.DailyReportDto;

import java.util.Date;
import java.util.List;

public interface ReportService {
     List<DailyReportDto> getDailyReport(String reportDate);
}
