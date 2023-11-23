
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente {

    private Socket clientesocket;

    public Cliente() {

        clientesocket = new Socket();
    }

    public Socket getClientesocket() {
        return clientesocket;
    }

    public void setClientesocket(Socket clientesocket) {
        this.clientesocket = clientesocket;
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();

        DataInputStream entradaDataInputStream;
        DataOutputStream salidaOutputStream;
        
        
        
        try {
            cliente.setClientesocket(new Socket("127.0.0.1", 5000));
            System.out.println("Conectado al servidor");

            entradaDataInputStream = new DataInputStream(cliente.getClientesocket().getInputStream());

            System.out.println(entradaDataInputStream.readUTF());

            salidaOutputStream = new DataOutputStream(cliente.getClientesocket().getOutputStream());

            String palabra = "";
            while (!palabra.equals("adios")) {
                System.out.println("Escribe el Numero del Placa");
                palabra = scanner.nextLine();

                salidaOutputStream.writeUTF(palabra);

                String respuesta = entradaDataInputStream.readUTF();
                System.out.println(respuesta);
            }

            cliente.getClientesocket().close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }

}
