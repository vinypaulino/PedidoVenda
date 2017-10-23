package com.naja.controller;

import com.naja.util.Controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lemoel Marques lemoel@gmail.com NajaSoftware
 */
@Named
@ViewScoped
public class FileUpLoaderController extends Controller implements Serializable {

    @PostConstruct
    public void init() {

    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile uf = event.getFile();
        String nomeArq = uf.getFileName();

        String path = "";
        // aqui verifico se é linux ou windows
        if (System.getProperties().get("os.name").toString().trim().equalsIgnoreCase("Linux")) {
            path = "/home/workspace/gca/WebContent/resources/files/";
        } else {
            path = "c://files//";
        }

        File f = new File(path + nomeArq);
        OutputStream os = null;
        InputStream is = null;
        try {
            is = uf.getInputstream();
            byte[] b = new byte[is.available()];
            os = new FileOutputStream(f);
            while (is.read(b) > 0) {
                os.write(b);
            }
            // aqui você pode colcar a gravação do path no BD

            info("O arquivo foi enviado corretamente, clique em enviar para concluir a operação.");
        } catch (IOException ex) {
            error("Erro");
        } finally {
            try {
                os.flush();
                os.close();
                is.close();
            } catch (IOException ex) {
                error("Erro no envio");
            }
        }
    }

}
