package com.alura.literalura.menu;

public class UserMenu {

    private boolean usuarioEscolheuRequesicao;
    private boolean usuarioEscolheuSair;

    public void mostrarOpcoes() {
        resetarOpcoes();
        System.out.print(
                "### Seja Bem Vindo(a) ao Literalura ###\n" +
                "1 - Fazer Requesição\n" +
                "2 - Sair\n" +
                "Digite o Número: "
        );
    }

    public void setUsuarioEscolheuRequesicao(boolean usuarioEscolheuRequesicao) {
        this.usuarioEscolheuRequesicao = usuarioEscolheuRequesicao;
    }
    public boolean isUsuarioEscolheuRequesicao() {return usuarioEscolheuRequesicao;}


    public void setUsuarioEscolheuSair(boolean usuarioEscolheuSair) {
        this.usuarioEscolheuSair = usuarioEscolheuSair;
    }
    public boolean isUsuarioEscolheuSair() {return usuarioEscolheuSair;}

    public void receberOpcao(int opcao) {
        if (opcao==1) {usuarioEscolheuRequesicao = true;}
        else if(opcao==2) {usuarioEscolheuSair = true;}
        System.out.println("Processando. . .");
    }

    private void resetarOpcoes() {
        usuarioEscolheuRequesicao = false;
        usuarioEscolheuSair = false;
    }
}
