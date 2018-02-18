/*
* Clase Servidor de un Socket
*/
package logica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;
import vistas.Vista10Control;

/**
 * <p>Clase Servidor: Se encarga de crear y configurar la conexión.</p>
 * <p>Este clase tiene como función:</p>
 * <ol>
 * <li>Publicar el Puerto: Crear y configurar el canal de conexión.</li>
 * <li>Esperar peticiones por parte de algún Cliente.</li>
 * <li>Recepción de comandos y envío de datos.</li>
 * <li>Una vez finalizada la comunicación se cierra el socket con el Cliente.</li>
 * </ol>
 * 
 * @author José Francisco Sánchez Portillo
 */
public class Servidor extends Thread {
    // Variable para mantener la conversación con el Cliente hasta el Comando End
    private boolean on = true;
    //Variable ServerSocket para abrir la conexión de tipo Servidor:
    private ServerSocket server;
    // Variable Socket para establecer el canal de conexión
    private Socket socket;
    // Variable para el Calendario y fechas:
    private  Calendar fecha;
    // Para formatear el mes a datos más humanos:
    private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    
    /**
     * El método Constructor crea la Conexión como Servidor. 
     */
    public Servidor() {
        conexion();
    }
    /**
     * Método que establece la conexión como servidor,
     * Crea un hilo de ejecución independiente, lo que hace que pueda estar atendiendo 
     * Clientes sin ocupar el proceso principal del Servidor.
     */
    public void conexion () {
        on = true;
        Thread hilo = new Thread(this);
        hilo.start();
        Vista10Control.log("Servidor> Servidor en funcionamiento...");
    }
    /**
     * Método run() para la ejecución de este hilo
     */
    @Override
    public void run () {
        try {
            // Esto sólo se ejeucta una vez: Se publica el Servidor disponible:
            server = new ServerSocket(50000);
            // Se ejecuta infinitamente buscando Clientes para conectar
            while (true) {    
                // Se establece la conexión con el Cliente
                socket = server.accept();
                
                // Enviando
                OutputStream ini = socket.getOutputStream();
                DataOutputStream flujo_inicial = new DataOutputStream(ini);
                flujo_inicial.writeUTF("El Servidor dice> Cliente conectado: IP: " + socket.getRemoteSocketAddress());
                
                String id_cliente = "Servidor> Cliente conectado: IP: " + socket.getInetAddress();
                Vista10Control.log(id_cliente);
                Vista10Control.log("Servidor> Esperando la solicitud del Cliente...");
                
                while (on) { // Esto se ejecuta continuamente mientras el Cliente no ejecute un comando End
                    this.fecha = new GregorianCalendar();
                    // Escuchando la entrada de comando por parte del Cliente:
                    InputStream in = socket.getInputStream();
                    DataInputStream flujo_entrada = new DataInputStream(in);
                    String comando = flujo_entrada.readUTF();
                    Vista10Control.log("El Cliente dice> Ejecutar comando: " + comando);
                    
                    String respuesta;
                    
                    switch (comando) {
                        case "Day":
                            respuesta = Integer.toString(fecha.get(Calendar.DAY_OF_MONTH));
                            break;
                        case "Month":
                            respuesta = meses[fecha.get(Calendar.MONTH)];
                            break;
                        case "Year":
                            respuesta = Integer.toString(fecha.get(Calendar.YEAR));
                            break;
                        case "Time":
                            respuesta = String.format("%02d", fecha.get(Calendar.HOUR_OF_DAY)) 
                                    + ":" + String.format("%02d", fecha.get(Calendar.MINUTE)) 
                                    + ":" + String.format("%02d", fecha.get(Calendar.SECOND));
                            break;
                        case "All":
                            respuesta = String.format("%02d", fecha.get(Calendar.HOUR_OF_DAY))
                                    + ":" + String.format("%02d", fecha.get(Calendar.MINUTE))
                                    + ":" + String.format("%02d", fecha.get(Calendar.SECOND))
                                    + String.format("%8s", "")
                                    + String.format("%02d", fecha.get(Calendar.DAY_OF_MONTH)) + "/" 
                                    + String.format("%02d", (fecha.get(Calendar.MONTH)+1)) 
                                    + "/" + fecha.get(Calendar.YEAR);
                            break;
                        case "End":
                            respuesta = "Conexión cerrada";
                            on = false; // Se rompe la ejecución infinita: comando End
                            break;
                        default:
                            respuesta = "No se reconoce el comando solicitado.";
                            break;  
                    }
                    Vista10Control.log("Servidor> Envía respuesta al Cliente: " + respuesta);
                    // Enviando respuesta al Cliente:
                    OutputStream out = socket.getOutputStream();
                    DataOutputStream flujo_salida = new DataOutputStream(out);
                    flujo_salida.writeUTF("El Servidor dice> " + respuesta);
                } // Fin de Esto se ejecuta continuamente mientras el Cliente no ejecute un comando End
                desconexion();
                break; // Se rompe la ejecución infinita de búsqueda de Clientes y se finaliza el Servidor.
            } // Fin de Se ejecuta infinitamente buscando Clientes para conectar
        } catch (IOException ex) {
            Vista10Control.log("Servidor> Error de conexión del Servidor: " + ex.getMessage());
        }
    }
    /**
     * Desconecta totalmente el Servidor
     */
    public void desconexion () {
        try {
            on = false;
            socket.close();
            server.close();
            Vista10Control.log("Servidor> Servidor desconectado.");
        } catch (IOException ex) {
            Vista10Control.log("Servidor> Error de desconexión del Servidor: " + ex.getMessage());
        }
    }
    
}
