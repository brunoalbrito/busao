
import br.com.panelinha.model.Agendador;
import java.util.Timer;

public class StartAgendador {
    
    public static void main(String[] args) {
        Timer timer = new Timer();
        Agendador agendador = new Agendador();
        /**
         * timer.schedule(tarefa a ser executada, tempo de espera para executar, intervalo entre as execucoes)
         */
        timer.schedule(agendador, 0,  120 * 1000);
//        while (true) {
//            System.out.println("Alerta de execução ...");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
    }

}
