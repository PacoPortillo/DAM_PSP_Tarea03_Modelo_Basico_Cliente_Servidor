
package main;

import vistas.Splash;

/**
 * <p>Clase que inicia el programa Servidor.</p>
 * <p>Este program tiene como función:</p>
 * <ol>
 * <li>Publicar una Conexión.</li>
 * <li>Conectar con los Clientes.</li>
 * <li>Recibir comandos de los Clientes.</li>
 * <li>Enviar la información al Cliente en función de las órdenes recibidas.</li>
 * </ol>
 * 
 * @author José Francisco Sánchez Portillo
 */
public class Main {

    /**
     * Método Main() inicia la clase {@link Splash}.
     * 
     * @param args No recibe argumentos por línea de comandos
     */
    public static void main(String[] args) {
        new Thread(new Splash()).start();
    }
    
}
