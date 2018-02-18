/*
* Clase Cliente de un Socket
*/
package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import vistas.Vista10Control;

/**
 * <p>Clase Cliente: Se encarga de establecer la conexión con el Servidor.</p>
 * <p>Este clase tiene como función:</p>
 * <ol>
 * <li>Conectarse al servidor.</li>
 * <li>Envío y recepción de datos.</li>
 * <li>Una vez finalizada la comunicación se cierra el socket.</li>
 * </ol>
 * 
 * @author José Francisco Sánchez Portillo
 */
public class Cliente {
    // Variable Socket para establecer el canal de conexión
    private Socket socket;
    
    /**
     * Método Constructor: 
     * Establece la conexión con el servidor.
     */
    public Cliente() {
        conexion();
    }
    
    /**
     * Método para el envío y recepción de datos: el Cliente envía un comando y
     * el Servidor envía la respuesta.
     * 
     * @param comando Recibe el Comando desde la interface con la orden que envía al Servidor.
     */
    public void comandosCliente(String comando) {
        Vista10Control.log("Cliente> Envía al Servidor la orden: " + comando);
         try {
             // Enviando comando:
             OutputStream out = socket.getOutputStream();
             DataOutputStream flujo_salida = new DataOutputStream(out);
             flujo_salida.writeUTF(comando); // Lanzando comando
             // Escuchando y recibiendo respuesta:
             InputStream in = socket.getInputStream();
             DataInputStream flujo_entrada = new DataInputStream(in);
             Vista10Control.log(flujo_entrada.readUTF()); // Imprimiendo respuesta
         } catch (IOException ex) {
             Vista10Control.log("Cliente> Error de comandos del Cliente: " + ex.getMessage());
         } finally {
             if (comando.equals("End")){
                 desconexion(); // Si el comando es End cuando el Servidor responde cerrramos el Socket
             }
         }
    }
    /**
     * Establece la conexión con el servidor.
     */
    public void conexion () {
        try {
            // Establecer una conexión a localhost con puerto 50000
            // El Servidor está pendiente de esta petición:
            socket = new Socket("127.0.0.1", 50000);
            String id_servidor = "Cliente> Conectando al servidor: IP: " + socket.getRemoteSocketAddress();
            Vista10Control.log(id_servidor);
            // Escuchando la respuesta de Conexión por parte del Servidor:
            InputStream in = socket.getInputStream();
            DataInputStream flujo_entrada = new DataInputStream(in);
            Vista10Control.log(flujo_entrada.readUTF()); // Imprimiendo respuesta
        } catch (IOException ex) {
            Vista10Control.log("Cliente> Error de conexión del Cliente: " + ex.getMessage());
        }                
    }
    /**
     * Cierra el Socket del Cliente.
     */
    public void desconexion () {
        try {
            socket.close(); // Se cierra la Conexión.
            Vista10Control.log("Cliente> Cliente desconectado.");
        } catch (IOException ex) {
            Vista10Control.log("Cliente> Error de cierre de conexión: " + ex.getMessage());
        }
    }
    
}
