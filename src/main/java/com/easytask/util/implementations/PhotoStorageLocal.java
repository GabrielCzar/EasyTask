package com.easytask.util.implementations;

import com.easytask.util.IPhotoStorage;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.FileSystems.getDefault;

@Service
public class PhotoStorageLocal implements IPhotoStorage {
    private static final String URL = "http://localhost:8080/user/fotos/";
    private static final String LOCAL = "src/main/resources/static/layout/images/usuario/";

    private final Path local;

    public PhotoStorageLocal() {
        this.local = getDefault()
                .getPath(new File(getDefault()
                        .getPath(LOCAL).toString())
                            .getAbsolutePath());
    }

    @Override
    public String save(String user, MultipartFile photo) {
        String namePhoto = user + "-" + photo.getOriginalFilename();
        try {
            if (photo.isEmpty())
                throw new RuntimeException("Failed to store empty file " + photo.getOriginalFilename());
            Files.copy(photo.getInputStream(), this.local.resolve(namePhoto));
        } catch (IOException e) {
            throw new RuntimeException("Erro salvando a foto " + namePhoto, e);
        }
        return namePhoto;
    }

    @Override
    public String getUrl(String namePhoto) {
        return URL + namePhoto;
    }

    @Override
    public Path load(String photoName) {
        return local.resolve(photoName);
    }

    @Override
    public Resource loadAsResource(String photoName) {
        try {
            Path path = load(photoName);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable())
                return resource;
            else
                throw new PhotoStorageNotFoundException("Não foi possivel ler o arquivo: " + photoName);
        } catch (MalformedURLException e) {
            throw new PhotoStorageNotFoundException("Não foi possivel ler o arquivo: " + photoName, e);
        }
    }

}
