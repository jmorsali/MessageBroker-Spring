package RabbirMQ.example.RabbirMQDemo.Service;

import RabbirMQ.example.RabbirMQDemo.DTOs.ProductCodeDto;
import RabbirMQ.example.RabbirMQDemo.Repository.ProductCodeRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.List;

@Service
public class CSVFileServiceImpel implements CSVFileService {

   final ProductCodeRepository productCodeRepository;

    public CSVFileServiceImpel(ProductCodeRepository productCodeRepository) {
        this.productCodeRepository = productCodeRepository;
    }

    @Override
    public List<ProductCodeDto> processFile(String csvData) {

        List<ProductCodeDto> codesDtoItem = new CsvToBeanBuilder(new StringReader(csvData))
                .withType(ProductCodeDto.class)
                .build()
                .parse();
        return codesDtoItem;
    }
}
