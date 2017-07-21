package com.easytask.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import static java.nio.file.FileSystems.getDefault;

public class FileUtil {

    private static final String local = "resources/static/layout/images/usuario/";

    public static String saveFile(String contexto, String foto, MultipartFile imagem) {
        try {
            File file = new File(contexto + getDefault().getPath(local) + getDefault().getSeparator() + foto + "-" + imagem.getOriginalFilename());
            FileUtils.writeByteArrayToFile(file, imagem.getBytes());
        }catch (IOException e) {
            throw new RuntimeException("Erro ao salvar foto!", e);
        }
        return foto + "-" + imagem.getOriginalFilename();
    }

    //public byte[] recovery(String url) { }
}
