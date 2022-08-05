package cz.forest.homework.service;

import cz.forest.homework.model.TransactionDto;

import java.util.List;

public interface TransactionCSVReaderService {

    List<TransactionDto> readCsvFile(byte[] bytes);
}
