package com.deusley.resistor_color;

import java.util.HashMap;
import java.util.Map;

public class ResistorColor {

    // Mapeamento das cores para os dígitos
    private static final Map<Integer, String> corDigito = new HashMap<>();

    static {
        corDigito.put(0, "preto");
        corDigito.put(1, "marrom");
        corDigito.put(2, "vermelho");
        corDigito.put(3, "laranja");
        corDigito.put(4, "amarelo");
        corDigito.put(5, "verde");
        corDigito.put(6, "azul");
        corDigito.put(7, "violeta");
        corDigito.put(8, "cinza");
        corDigito.put(9, "branco");
    }

    public static String resistorOhms(String entrada) {
        // Separar o valor da unidade (ohms)
        String[] partes = entrada.split(" ");
        String valor = partes[0].toLowerCase();
        String tolerancia = "dourado";  // Tolerância padrão de 5%
        double valorResistor;

        // Tratar k (kilo) e M (mega)
        if (valor.contains("k")) {
            valor = valor.replace("k", "");
            valorResistor = Double.parseDouble(valor) * 1000;
        } else if (valor.contains("M")) {
            valor = valor.replace("M", "");
            valorResistor = Double.parseDouble(valor) * 1000000;
        } else {
            valorResistor = Double.parseDouble(valor);
        }

        return calcularCores(valorResistor, tolerancia);
    }

    // Função auxiliar para calcular as cores com base no valor do resistor
    private static String calcularCores(double valorResistor, String tolerancia) {
        int multiplicador = 0;

        // Defino uma  faixa de multiplicador
        while (valorResistor >= 100) {
            valorResistor /= 10;
            multiplicador++;
        }

        // Pego  os dois primeiros dígitos
        int primeiraFaixa = (int) valorResistor / 10;
        int segundaFaixa = (int) valorResistor % 10;

        //  Obtetenho as  cores correspondentes
        String primeiraCor = corDigito.get(primeiraFaixa);
        String segundaCor = corDigito.get(segundaFaixa);
        String terceiraCor = corDigito.get(multiplicador);

        return String.format("%s %s %s %s", primeiraCor, segundaCor, terceiraCor, tolerancia);
    }

    public static void main(String[] args) {
                                                                    // impressão  dos valores/cores

        System.out.println(resistorOhms("47 ohms"));      // amarelo violeta preto dourado
        System.out.println(resistorOhms("4.7k ohms"));    // amarelo violeta vermelho dourado
        System.out.println(resistorOhms("1M ohms"));      // marrom preto verde dourado
        System.out.println(resistorOhms("100 ohms"));     // marrom preto marrom dourado
        System.out.println(resistorOhms("2M ohms"));      // vermelho preto verde dourado
    }
}
