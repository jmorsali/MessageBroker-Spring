package RabbirMQ.example.RabbirMQDemo.Entity;

import RabbirMQ.example.RabbirMQDemo.DTOs.DailyReportDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class DailyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String reportDate;
    String codeListCode;
    Integer TotalItemCount;

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
