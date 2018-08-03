package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.exceptions.*;

@Slf4j
public class BankStatementFile {
    private final String path;
    private final String fileName;

    public BankStatementFile(String path, LocalDate importDate) {
        this.path = path;
        this.fileName = constructFileNameFromDate(importDate);
    }
    
    boolean exists() {
        return new File(fullPath()).exists();
    }
 
    List<BankStatement> read() {
        try (Reader reader = new FileReader(fullPath())) {
            List<BankStatement> statements = new ArrayList<>();
            Iterable<CSVRecord> records = CSVFormat
                    .RFC4180
                    .withFirstRecordAsHeader()
                    .parse(reader);
            records.forEach(row -> statements.add(readRow(row)));
            return statements;
        } catch (FileNotFoundException ex) {
            log.error("Bank statement file not found. Looking for  " + path , ex);
            throw new BankStatementsFileNotFound(ex);
        } catch (IOException ex) {
            log.error("Error while processing file " + path , ex);
            throw new BankStatementsFileReadingError(ex);
        }
    }
    
    void markProcessed() {
        new File(fullPath()).renameTo(new File(processedFullPath()));
    }
    
    private BankStatement readRow(CSVRecord row) {
        String accountingDate = row.get(2);
        String accountNumber = row.get(3);
        String amountAsString = row.get(4);
        return new BankStatement(accountNumber, amountAsString, accountingDate);
    }
    
    private String constructFileNameFromDate(LocalDate importDate) {
        return String.format("bankStatements_%d_%d_%d.csv", importDate.getYear(), importDate.getMonthValue(), importDate.getDayOfMonth());
    }
    
    private String fullPath() {
        return path + File.separator + fileName;
    }
    
    private String processedFullPath() {
        return path + File.separator + "_processed_"+ fileName;
    }
    
    @Getter
    class BankStatement {
        private final String accountNumber;
        private final BigDecimal amount;
        private final LocalDate accountingDate;
        
        public BankStatement(String accountNumber, String amountAsString, String accountingDateAsIsoDateString) {
            this.accountNumber = accountNumber;
            this.amount = new BigDecimal(amountAsString);
            this.accountingDate = LocalDate.parse(accountingDateAsIsoDateString, DateTimeFormatter.ISO_DATE);
        }
        
        
    }
}
