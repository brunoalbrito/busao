
import br.com.mack.teste.TesteRequest;
import br.com.panelinha.model.Linha;
import br.com.panelinha.model.Onibus;
import br.com.panelinha.parser.LinhaParser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StartAgendador {

    static String linha;
    static int codigoLinha;
    static List<Linha> linhas = null;
    static List<Onibus> onibuses = new ArrayList<>();

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("**********Vamos buscar um onibus************");
        System.out.println("Digite o código da linha:");
        linha = reader.nextLine();

        int i = 1;
        linhas = LinhaParser.buscarLinhas(linha);
        for (Linha l : linhas) {
            System.out.println("Código: " + i + " " + l);
            i++;
        }
        System.out.println("Escolha o sentido da linha e digite o código da linha:");
        codigoLinha = reader.nextInt();
        System.out.println("Onibus e suas posições");
        System.out.println("**********Sentido selecionado**********");
        codigoLinha--;
        if (linhas.get(codigoLinha).getSentidoOperacional() == 1) {
            System.out.println("Saida: " + linhas.get(codigoLinha).getTerminalPrincipal());
            System.out.println("Chegada: " + linhas.get(codigoLinha).getTerminalSegundario());
        } else {
            System.out.println("Saida: " + linhas.get(codigoLinha).getTerminalSegundario());
            System.out.println("Chegada: " + linhas.get(codigoLinha).getTerminalPrincipal());
        }
//        for(Onibus onibus:onibuses){
//            System.out.println(onibus);
//        }

//        while (true) {
//            System.out.println("Alerta de execução ...");
//            try {
//                Date date = new Date();
//                System.out.println(date);
//                TesteRequest.main();
//                Thread.sleep(120 * 1000);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
    }

}
