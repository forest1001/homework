package cz.forest.homework.rest;

import cz.forest.homework.exception.HomeworkBaseRuntimeException;
import cz.forest.homework.model.TransactionDto;
import cz.forest.homework.service.CsvFileUploadFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@RestController
public class FileUploadController {

    private final CsvFileUploadFacade uploadFacade;

    public FileUploadController(CsvFileUploadFacade uploadFacade) {
        this.uploadFacade = uploadFacade;
    }

    @PostMapping("/upload")
    public List<TransactionDto> uploadCsvFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            throw new HomeworkBaseRuntimeException("Error opening file" + file.getName(), e);
        }
        return uploadFacade.processCsvUpload(bytes, file.getName());
    }
}
