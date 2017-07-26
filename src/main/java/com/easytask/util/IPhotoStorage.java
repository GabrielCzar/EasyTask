package com.easytask.util;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface IPhotoStorage {

    String save(String user, MultipartFile photo);

    String getUrl(String photoName);

    Path load(String photoName);

    Resource loadAsResource(String photoName);
}
