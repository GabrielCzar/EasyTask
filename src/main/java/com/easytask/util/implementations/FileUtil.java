package com.easytask.util.implementations;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import static java.nio.file.FileSystems.getDefault;

public class FileUtil {

    private static final String LOCAL = "src/main/resources/static/layout/images/usuario/";

    public static String saveFile(String contexto, String foto, MultipartFile imagem) {
        try {
            String local = getDefault().getPath(LOCAL) + getDefault().getSeparator() + foto + "-" + imagem.getOriginalFilename();
            FileUtils.writeByteArrayToFile(new File(local), imagem.getBytes());
            FileUtils.writeByteArrayToFile(new File(contexto + local), imagem.getBytes());
        }catch (IOException e) {
            throw new RuntimeException("Erro ao salvar foto!", e);
        }
        return foto + "-" + imagem.getOriginalFilename();
    }
}
