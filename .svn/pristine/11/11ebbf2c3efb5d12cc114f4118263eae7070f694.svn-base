package com.naja.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class FileUtils implements Serializable {

    public boolean moveArquivo(String pathOrigem, String pathDestino) {

        // arquivo a ser movido
        File arquivo = new File(pathOrigem);

        // diretorio de destino
        File dir = new File(pathDestino);

        // move o arquivo para o novo diretorio
        boolean ok = arquivo.renameTo(new File(dir, arquivo.getName()));

        return ok;
        
//        // copia os dados
//        InputStream in;
//        // escreve os dados
//        OutputStream out;
//        try {
//            // arquivos que vamos copiar
//            File toFile = new File("toFile.txt");
//            // destino para onde vamos mover o arquivo
//            File fromFile = new File("newfolder/newFile.txt");
//            //verifica se o arquivo existe
//            if (!fromFile.exists()) {
//                //verifica se a pasta existe
//                if (!fromFile.getParentFile().exists()) {
//                    //cria a pasta
//                    fromFile.getParentFile().mkdir();
//                }
//                // cria o arquivo
//                fromFile.createNewFile();
//            }
//            in = new FileInputStream(toFile);
//            out = new FileOutputStream(fromFile);
//            // buffer para transportar os dados
//            byte[] buffer = new byte[1024];
//            int length;
//            // enquanto tiver dados para ler..
//            while ((length = in.read(buffer)) > 0) {
//                // escreve no novo arquivo
//                out.write(buffer, 0, length);
//            }
//
//            in.close();
//            out.close();
//            //apaga o arquivo antigo
//            toFile.delete();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
