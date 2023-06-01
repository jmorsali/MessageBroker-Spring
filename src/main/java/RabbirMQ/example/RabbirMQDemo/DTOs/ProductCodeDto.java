package RabbirMQ.example.RabbirMQDemo.DTOs;

import RabbirMQ.example.RabbirMQDemo.Entity.ProductCode;
import RabbirMQ.example.RabbirMQDemo.Repository.ProductCodeRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductCodeDto {
   Long id;
   String source;
   String codeListCode;
   String code;
   String displayValue;
   String longDescription;
   String fromDate;
   String toDate;
   String sortingPriority;

   public static List<ProductCodeDto> ToDto(List<ProductCode> codeItems) {
      var result = new ArrayList<ProductCodeDto>();
      for (var codeItem : codeItems) {
         result.add(ToDto(codeItem));
      }
      return result;
   }

   public static ProductCodeDto ToDto(ProductCode codeItem) {
      if (codeItem == null)
         return null;
      var codeDtoItem = new ProductCodeDto();
      codeDtoItem.setId(codeItem.getId());
      codeDtoItem.setCode(codeItem.getCode());
      codeDtoItem.setDisplayValue(codeItem.getDisplayValue());
      codeDtoItem.setSource(codeItem.getSource());
      codeDtoItem.setFromDate(codeItem.getFromDate());
      codeDtoItem.setToDate(codeItem.getToDate());
      codeDtoItem.setCodeListCode(codeItem.getCodeListCode());
      codeDtoItem.setSortingPriority(codeItem.getSortingPriority());
      codeDtoItem.setLongDescription(codeItem.getLongDescription());
      return codeDtoItem;
   }

   public static List<ProductCode> ToEntity(List<ProductCodeDto> codeDtoItems) {
      var result = new ArrayList<ProductCode>();
      for (var codeDtoItem : codeDtoItems) {
         result.add(ToEntity(codeDtoItem));
      }
      return result;
   }

   public static ProductCode ToEntity(ProductCodeDto codeDtoItem) {
      if (codeDtoItem == null)
         return null;
      var codeItem = new ProductCode();
      codeItem.setId(codeDtoItem.getId());
      codeItem.setCode(codeDtoItem.getCode());
      codeItem.setDisplayValue(codeDtoItem.getDisplayValue());
      codeItem.setSource(codeDtoItem.getSource());
      codeItem.setFromDate(codeDtoItem.getFromDate());
      codeItem.setToDate(codeDtoItem.getToDate());
      codeItem.setCodeListCode(codeDtoItem.getCodeListCode());
      codeItem.setSortingPriority(codeDtoItem.getSortingPriority());
      codeItem.setLongDescription(codeDtoItem.getLongDescription());
      return codeItem;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getSource() {
      return source;
   }

   public void setSource(String source) {
      this.source = source;
   }

   public String getCodeListCode() {
      return codeListCode;
   }

   public void setCodeListCode(String codeListCode) {
      this.codeListCode = codeListCode;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getDisplayValue() {
      return displayValue;
   }

   public void setDisplayValue(String displayValue) {
      this.displayValue = displayValue;
   }

   public String getLongDescription() {
      return longDescription;
   }

   public void setLongDescription(String longDescription) {
      this.longDescription = longDescription;
   }

   public String getFromDate() {
      return fromDate;
   }

   public void setFromDate(String fromDate) {
      this.fromDate = fromDate;
   }

   public String getToDate() {
      return toDate;
   }

   public void setToDate(String toDate) {
      this.toDate = toDate;
   }

   public String getSortingPriority() {
      return sortingPriority;
   }

   public void setSortingPriority(String sortingPriority) {
      this.sortingPriority = sortingPriority;
   }
}
