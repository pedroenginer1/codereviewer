package com.projetoia.codereviewer;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ChatClient chatClient;

    // Construtor: O Spring vai injetar o motor do Gemini aqui automaticamente
    public ReviewService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String analisarCodigo(String codigoUsuario) {
        // Criamos o comando (Prompt) detalhado para a IA agir como um Engenheiro Sênior
        String comandoPrompt = """
            Você é um Engenheiro de Software Sênior especialista em Code Review.
            Analise o código abaixo e forneça um relatório detalhado contendo:
            1. Erros ou potenciais bugs encontrados.
            2. Problemas de performance ou segurança.
            3. Uma versão corrigida e refatorada do código seguindo as melhores práticas de Clean Code.
            
            Aqui está o código que você deve analisar:
            
            """ + codigoUsuario;

        // Enviamos o prompt para o Gemini e aguardamos o texto de resposta
        return this.chatClient.prompt()
                .user(comandoPrompt)
                .call()
                .content();
    }
}