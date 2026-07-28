package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.DocumentType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storePsychologueDocument(MultipartFile file, Integer psychologueId, DocumentType documentType);

    Resource load(String storageKey);

    void delete(String storageKey);
}
