/*
 * 
 *
 * Implementacão do cliente para o Laboratorio de Sockets Java
 */
import java.io.*;
import java.net.*;

/**
 * @author Daria Antonova (adaptado)
 *
 */
public class MeuServidor implements Runnable {

    private Socket socketCliente;

    public MeuServidor(Socket s) {
        this.socketCliente = s;

    }

    public static void main(String args[]) {

        // declara um socket servidor (para escutar)
        ServerSocket servidorEco = null;

        // declara um socket cliente para o servidor
        Socket socketCliente = null;
      
        int porta = 1600; // porta on o servidor irá escutar

        // Note que a porta menor que 1023 pode ser usada somente por
        // usuários com privilégio especial (permissões de root ou super user)
        try {
            System.out.println("Iniciando o servidor de eco em "
                    + InetAddress.getLocalHost() + " porta " + porta);
            servidorEco = new ServerSocket(porta);
            System.out.println("sucesso");
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            // Cria um objecto socket de ServerSocket para escutar e aceitar conexões
            int cont = 0;
            for (;;) {
                System.out.println("Pronto para receber conexoes de clientes");
                socketCliente = servidorEco.accept();
               

                Thread t = new Thread(new MeuServidor(socketCliente));
                t.start();

            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    @Override
    public void run() {
        // declara um fluxo de entrada e um de saída
        BufferedReader is;
        PrintStream os;
        String linha;
        try {
            System.out.println("Aceitado conexão de "
                    + socketCliente.getInetAddress().getHostName());

            // Abrir os fluxos de entrada e saída
            is = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            os = new PrintStream(socketCliente.getOutputStream());

            System.out.println("Pronto para receber dados de "
                    + socketCliente.getInetAddress().getHostName());
            while (true) { // reenviar os dados de volta ao cliente enquanto o servidor receber dados

                linha = is.readLine();

                System.out.println(socketCliente.getInetAddress().getHostName() + ": (" + linha + ")");

                os.println("Sua mensagem foi recebida e processada!");

            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
