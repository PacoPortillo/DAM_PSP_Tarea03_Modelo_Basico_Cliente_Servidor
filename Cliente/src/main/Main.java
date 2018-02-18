
package main;

import vistas.Splash;

/**
 * <p>Clase que inicia el programa Cliente.</p>
 * <p>Este program tiene como función:</p>
 * <ol>
 * <li>Conectarse al servidor.</li>
 * <li>Enviar ordenes al servidor.</li>
 * <li>Recibir la información del servidor en función de las órdenes enviadas.</li>
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
