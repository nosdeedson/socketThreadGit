/*
 *
 *
 * Implementacão do cliente para o Laboratorio de Sockets Java
 */

import java.io.*;
import java.net.*;

/**
 * @author Daria Antonova
 *
 */

public class MeuCliente {

    public static void main(String[] args) throws IOException {

    	Socket socketCliente = null; 
        PrintWriter os = null;
        BufferedReader is = null;

        // nome e numero da porta da máquina que você irá se conectar
        //String remoto = new String("computador1.fepi.br");
        String remoto = new String("192.168.1.106");
        int port = 1600;

        try {

        System.out.println("MeuCliente esta tentando conectar ao servidor "+ remoto +"...");

        	// cria um novo objeto socket chamado socketCliente onde
        	// "computador1" é o nome da máquina na rede, e 6070 é o numero da porta
            socketCliente = new Socket(remoto, port);

            // abre um PrintWriter no fluxo de saída do socket
            os = new PrintWriter(socketCliente.getOutputStream(), true);
            
			// abre um BufferReader no fluxo de entrada do socket
            is = new BufferedReader(new InputStreamReader(
                                        socketCliente.getInputStream()));

            // veja a documentação do Java I/O para se familiarizar com classes do Java I/O

        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: " + remoto + ".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erro ao receber o I/O para conexao com " + remoto + ".");
            System.exit(1);
        }

		// cria um BufferedReader para entrada do usuário na máquina local
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

		String userInput;

		// enquanto houver entrada pelo usuário
		while ((userInput = stdIn.readLine()) != null) {

			os.println(userInput);

			if (userInput.compareTo("sair") == 0) { break; }

			System.out.println("resposta: " + is.readLine());

		}

		os.close();
		is.close();
		stdIn.close();
		socketCliente.close();
    }

}

