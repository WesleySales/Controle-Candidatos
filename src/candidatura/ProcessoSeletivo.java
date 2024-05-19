package candidatura;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessoSeletivo {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("PROCESSO SELETIVO.");

        String[] selecionados = selecaoCandidatos();
        ligarSelecionados(selecionados);

    }



    static void entrandoEmContato(String candidato){
        int tentativasRealizadas=1;
        boolean continuarTentando= true;
        boolean atendeu = false;

        do {
            atendeu= atender();
            continuarTentando= !atendeu;
            if (continuarTentando){
                tentativasRealizadas++;
            } else {
                System.out.println("Contato realizado com sucesso.");
            }

        } while (continuarTentando && tentativasRealizadas<3);
        if (atendeu){
            System.out.println("Conseguimos contato com "+candidato+" na "+tentativasRealizadas+" tentativa");
        } else {
            System.out.println("Não conseguimos contato com "+candidato+" no numero maximo de tentativas");
        }
    }
    static boolean atender(){
        return new Random().nextInt(3)==1;
    }

    static String[] selecaoCandidatos(){
        String [] candidatos = {"Ana","João","Lucas","Dora", "Nairana","Ivete", "Theo", "Igor","Maria","Marta","Tiago"};
        String[] paraLigar;
        int candidatosSelecionados=0;
        int candidatoAtual = 0;
        double salarioBase=2000.00;
        int i=0;
        paraLigar = new String[5];

        while (candidatosSelecionados<5 && candidatoAtual < candidatos.length){
            double pretensaoSalarial = valorPretendido();
            String candidato = candidatos[candidatoAtual];


            System.out.println("\nO candidato "+candidato+" solicitou este salario: R$ "+ String.format("%.2f",pretensaoSalarial));
            if (salarioBase>= pretensaoSalarial){
                System.out.println("O candidato "+candidato+" foi selecionado para a vaga");
                candidatosSelecionados++;
                paraLigar[candidatosSelecionados] = candidato;
                i++;
            }
            candidatoAtual++;
        }
        System.out.println("NUMERO DE CANDIDATOS PARA LIGAR: " + candidatosSelecionados);
        for (String ligacoes : paraLigar) {
            System.out.println(ligacoes);
        }
        return paraLigar;
       
    }

    static void ligarSelecionados(String [] paraLigar){
        for (String ligacoes: paraLigar){
            entrandoEmContato(ligacoes);
        }

    }
    static double valorPretendido(){
        return ThreadLocalRandom.current().nextDouble(1800,2300);
    }

    static void analisarCandidato(double pretensaoSalarial){
        double salarioBase = 2000.00;
        if (salarioBase>pretensaoSalarial){
            System.out.println("LIGAR PARA O CANDIDATO");
        }
        else if (salarioBase==pretensaoSalarial){
            System.out.println("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
        }
        else {
            System.out.println("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDATOS");
        }
    }
}
