
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private ServerSocket serverSocket;
    private int puerto;

    public Servidor(int puerto) {

        this.puerto = puerto;
        try {
            serverSocket = new ServerSocket(puerto);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public static void main(String[] args) {

        Servidor servidor = new Servidor(5000);

        HashMap<String, String> placas = new HashMap<>();

        placas.put("A", "Azuay");
        placas.put("B", "Bolívar ");
        placas.put("U", "Cañar");
        placas.put("X", "Cotopaxi");
        placas.put("H", "Chimborazo ");
        placas.put("O", "El Oro ");
        placas.put("E", "Esmeraldas ");
        placas.put("Q", "Francisco de Orellana  ");
        placas.put("W", "Galápagos ");
        placas.put("G", "Guayas  ");
        placas.put("I", "Imbabura  ");
        placas.put("R", "Los Ríos  ");
        placas.put("M", "Morona Santiago   ");
        placas.put("V", "Napo  ");
        placas.put("S", "Pastaza   ");
        placas.put("P", "Pichincha");
        placas.put("V", "Napo  ");
        placas.put("S", "Pastaza   ");
        placas.put("P", "Pichincha");
        placas.put("Y", "Santa Elena  ");
        placas.put("J", "Santo Domingo de los Tsáchilas  ");
        placas.put("K", "Sucumbíos ");
        placas.put("T", "Tungurahua ");
        placas.put("Z", "Zamora Chinchipe  ");

        DataOutputStream salidaOutputStream;
        DataInputStream entraDataInputStream;

        try {
            System.out.println("Esperando Clientes en el Servidor en el Puerto : " + servidor.getPuerto());

            Socket socket = servidor.getServerSocket().accept();
            System.out.println("Cliente conectado");

            salidaOutputStream = new DataOutputStream(socket.getOutputStream());
            entraDataInputStream = new DataInputStream(socket.getInputStream());

            salidaOutputStream.writeUTF("Solicitud Aceptada....");

            String palabra = "";
            String valor = "";
            while (!palabra.equals("adios")) {
                palabra = entraDataInputStream.readUTF();

                String primeraletra = palabra.substring(0, 1);
               
                for (String llave : placas.keySet()) {
                    if (llave.startsWith( primeraletra.toUpperCase())) {
                        valor = placas.get(llave);
                        // System.out.println("El valor para la llave " + llave + " es: " + valor);
                        break;
                    }
                    valor = "La palabra no se encuentra en el Placas";
                }

                if (palabra.equals("adios")) {
                    valor = "";
                }

                salidaOutputStream.writeUTF(valor);
            }

            socket.close();

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
