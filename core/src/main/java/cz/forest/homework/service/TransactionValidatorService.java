package cz.forest.homework.service;

import cz.forest.homework.model.TransactionCsvLine;

import java.util.List;

public interface TransactionValidatorService {

    List<String> validate(TransactionCsvLine dto);
}
