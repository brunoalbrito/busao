
import br.com.mack.teste.TesteRequest;
import java.util.Date;

public class StartAgendador {
    
    public static void main(String[] args) {
//        Timer timer = new Timer();
//        Agendador agendador = new Agendador();
        /**
         * timer.schedule(tarefa a ser executada, tempo de espera para executar, intervalo entre as execucoes)
         */
//        timer.schedule(agendador, 0, 60 * 1000);
        while (true) {
            System.out.println("Alerta de execução ...");
            try {
                Date date = new Date();
                System.out.println(date);
                TesteRequest.main();
                Thread.sleep(120 *1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
