package RabbirMQ.example.RabbirMQDemo.Component;

import RabbirMQ.example.RabbirMQDemo.Entity.DailyReport;
import RabbirMQ.example.RabbirMQDemo.Repository.DailyReportRepository;
import RabbirMQ.example.RabbirMQDemo.Repository.ProductCodeRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class DailyReportGenerator {
    final DailyReportRepository dailyReportRepository;
    final ProductCodeRepository productCodeRepository;

    public DailyReportGenerator(DailyReportRepository dailyReportRepository, ProductCodeRepository productCodeRepository) {
        this.dailyReportRepository = dailyReportRepository;
        this.productCodeRepository = productCodeRepository;
    }

    @Async("asyncExecutor")
    public void doAsyncTask() {
        while (true) {
            try {
                var today = Calendar.getInstance();
                List<DailyReport> todayReportItems = dailyReportRepository.getDailyReport(today.toString());

                if (todayReportItems.isEmpty())
                    productCodeRepository.getCodeListCodeReport(today.toString());
            } catch (Exception e) {
               //todo log
            } finally {
                try {
                    Thread.sleep(1000 * 60 * 60);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Async task executed");
        }
    }
}
