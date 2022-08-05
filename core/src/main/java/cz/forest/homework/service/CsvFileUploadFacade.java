package cz.forest.homework.service;

import cz.forest.homework.model.TransactionDto;

import java.util.List;

public interface CsvFileUploadFacade {

    List<TransactionDto> processCsvUpload(byte[] bytes, String fileName);
}
