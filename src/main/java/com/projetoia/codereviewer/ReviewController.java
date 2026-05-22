package com.projetoia.codereviewer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    // Construtor: O Spring conecta o nosso Service criado anteriormente aqui dentro
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public String receberCodigoParaAnalise(@RequestBody String codigoDoUsuario) {
        // Pega o código enviado pela web e repassa para o Service falar com o Gemini
        return reviewService.analisarCodigo(codigoDoUsuario);
    }
}