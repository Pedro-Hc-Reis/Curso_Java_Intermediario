package aula65;

@interface InformacaoAula {

    String autor();
    int aulaNumero();
    String blog() default "http://xxxxx.bom.br";
    String site() default "http://xxxxx.bom.br";
}
