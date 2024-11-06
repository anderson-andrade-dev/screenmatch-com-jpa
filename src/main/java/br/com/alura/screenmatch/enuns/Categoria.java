package br.com.alura.screenmatch.enuns;

/**
 * @author Anderson Andrade Dev
 * @Data de Criação 06/11/2024
 */
public enum Categoria {
    ACAO("Action"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    ROMANCE("Romance"),
    CRIME("Crime");

    private String categoriaOmdb;

    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString(String texto){
        for(Categoria categoria : Categoria.values()){
            if(categoria.categoriaOmdb.equalsIgnoreCase(texto)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria não encontrada para a string");
    }
}
