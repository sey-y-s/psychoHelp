package org.psychohelp.psychohelp.serviceImpl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.entity.DocumentType;
import org.psychohelp.psychohelp.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class LocalFileStorageService implements FileStorageService {

    @Value("${app.storage.location}")
    private String storageLocation;

    private Path storageRoot;

    private static final long MAX_SIZE = 10 * 1024 * 1024;/**
     * Initialise le dossier de stockage au démarrage de l'application.
     * Si le dossier n'existe pas, il est créé automatiquement.
     */
    @PostConstruct
    public void init() {
        try {
            storageRoot = Paths.get(storageLocation)
                    .toAbsolutePath()
                    .normalize();
            Files.createDirectories(storageRoot);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de créer le dossier de stockage.", e);
        }
    }

    /**
     * Enregistre un document (CV ou diplôme) dans le dossier du psychologue.
     *
     * @param file fichier envoyé par le client
     * @param psychologueId identifiant du psychologue
     * @param documentType type de document (CV ou DIPLOME)
     * @return chemin relatif enregistré dans la base de données
     */
    @Override
    public String storePsychologueDocument(MultipartFile file, Integer psychologueId, DocumentType documentType) {
        validate(file, documentType);
        String extension = getExtension(file.getContentType());
        String generatedName = documentType.name().toLowerCase() + "-" + UUID.randomUUID() + extension;
        String storageKey = "psychologues/" + psychologueId + "/" + generatedName;
        Path destination = storageRoot.resolve(storageKey).normalize();
        if (!destination.startsWith(storageRoot)) {
            throw new IllegalArgumentException(
                    "Chemin invalide."
            );
        }
        try {
            Files.createDirectories(destination.getParent());
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return storageKey;
        } catch (IOException e) {
            throw new RuntimeException("Impossible d'enregistrer le document.", e);
        }
    }

    /**
     * Charge un document à partir de son chemin relatif.
     *
     * @param storageKey chemin enregistré en base
     * @return le document sous forme de Resource
     */
    @Override
    public Resource load(String storageKey) {
        try {
            Path file = storageRoot.resolve(storageKey).normalize();
            if (!file.startsWith(storageRoot)) {
                throw new IllegalArgumentException("Chemin invalide.");
            }
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("Document introuvable.");
            }
            return resource;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement du document.", e);
        }
    }

    /**
     * Supprime un document du disque.
     *
     * @param storageKey chemin enregistré en base
     */
    @Override
    public void delete(String storageKey) {
        try {
            Path file = storageRoot.resolve(storageKey).normalize();
            if (file.startsWith(storageRoot)) {
                Files.deleteIfExists(file);
            }
        } catch (IOException e) {
            throw new RuntimeException("Impossible de supprimer le document.", e);
        }

    }

    /**
     * Vérifie que le fichier est valide :
     * - non vide
     * - type autorisé (PDF, JPG ou PNG)
     * - taille inférieure à 10 Mo
     *
     * @param file fichier à vérifier
     */
    private void validate(MultipartFile file, DocumentType documentType) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException(
                    "Le document est obligatoire."
            );
        }
        // Taille maximale : 3 Mo
        if (file.getSize() > MAX_SIZE) {
            throw new IllegalArgumentException(
                    "Le fichier ne doit pas dépasser 3 Mo."
            );
        }
        String contentType = file.getContentType();
        if (documentType == DocumentType.CV) {
            if (!"application/pdf".equals(contentType)) {
                throw new IllegalArgumentException(
                        "Le CV doit être au format PDF."
                );
            }
        }
        if (documentType == DocumentType.DIPLOME) {
            Set<String> typesAutorises = Set.of(
                    "application/pdf",
                    "image/jpeg",
                    "image/png"
            );
            if (!typesAutorises.contains(contentType)) {
                throw new IllegalArgumentException(
                        "Le diplôme doit être au format PDF, JPG ou PNG."
                );
            }
        }
    }

    /**
     * Détermine l'extension du fichier en fonction de son Content-Type.
     *
     * @param contentType type MIME du fichier
     * @return extension correspondante (.pdf, .jpg ou .png)
     */
    private String getExtension(String contentType) {
        return switch (contentType) {
            case "application/pdf" -> ".pdf";
            case "image/jpeg" -> ".jpg";
            case "image/png" -> ".png";
            default -> throw new IllegalArgumentException("Format non supporté.");
        };

    }
}
