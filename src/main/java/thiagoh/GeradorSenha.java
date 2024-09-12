package thiagoh;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeradorSenha {

    // Enum para representar os diferentes tipos de caracteres que podem ser usados
    // na senha
    public enum TipoCaractere {
        MAIUSCULAS("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        MINUSCULAS("abcdefghijklmnopqrstuvwxyz"),
        NUMEROS("0123456789"),
        SIMBOLOS_ESPECIAIS("!@#$%^&*()-_=+[]{};:,.<>/?");

        private final String caracteres;

        TipoCaractere(String caracteres) {
            this.caracteres = caracteres;
        }

        public String getCaracteres() {
            return caracteres;
        }
    }

    private final SecureRandom geradorAleatorio;

    public GeradorSenha() {
        this.geradorAleatorio = new SecureRandom();
    }

    public String gerarSenha(int tamanho, boolean incluirMaiusculas, boolean incluirMinusculas,
            boolean incluirNumeros, boolean incluirSimbolosEspeciais) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho da senha deve ser maior que zero.");
        }

        List<TipoCaractere> tiposDeCaracteres = new ArrayList<>();
        if (incluirMaiusculas)
            tiposDeCaracteres.add(TipoCaractere.MAIUSCULAS);
        if (incluirMinusculas)
            tiposDeCaracteres.add(TipoCaractere.MINUSCULAS);
        if (incluirNumeros)
            tiposDeCaracteres.add(TipoCaractere.NUMEROS);
        if (incluirSimbolosEspeciais)
            tiposDeCaracteres.add(TipoCaractere.SIMBOLOS_ESPECIAIS);

        if (tiposDeCaracteres.isEmpty()) {
            throw new IllegalArgumentException("Pelo menos um tipo de caractere deve ser selecionado.");
        }

        String caracteresValidos = tiposDeCaracteres.stream()
                .map(TipoCaractere::getCaracteres)
                .collect(Collectors.joining());

        StringBuilder senhaBuilder = new StringBuilder(tamanho);
        for (int i = 0; i < tamanho; i++) {
            int indiceAleatorio = geradorAleatorio.nextInt(caracteresValidos.length());
            senhaBuilder.append(caracteresValidos.charAt(indiceAleatorio));
        }

        return senhaBuilder.toString();
    }

    public static void main(String[] args) {
        GeradorSenha gerador = new GeradorSenha();
        String senha = gerador.gerarSenha(10, true, true, true, true);
        System.out.println("Senha Gerada: " + senha);
    }
}
