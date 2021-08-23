package br.com.zupacademy.romulo.ecommerce.imagem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Imagem {


    @Size(min = 1)
    @NotNull
    @JsonProperty
    private List<MultipartFile> imagens = new ArrayList<>();

    @JsonCreator
    public Imagem(@Size(min = 1) @NotNull List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
