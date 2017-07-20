package com.easytask.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    private static final String local = "resources/static/layout/images/";

    public static String saveFile(String contexto, String foto, MultipartFile imagem) {
        try {
            imagem.transferTo(new File(contexto + local + foto));
            System.out.println(contexto + local);
        }catch (IOException e) {
            throw new RuntimeException("Erro ao salvar foto!", e);
        }
        return foto;
    }

    //public byte[] recovery(String url) { }
}
