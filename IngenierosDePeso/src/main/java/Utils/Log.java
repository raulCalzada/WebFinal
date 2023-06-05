/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase creada para gestionar los logs del sistema.
 * Esta clase se encarga principalmente de registrar y gestionar las trazas de la aplicación, permitiendo el seguimiento y análisis de eventos importantes.
 * Proporciona métodos para escribir mensajes de log en diferentes niveles de severidad, así como para configurar opciones de registro y almacenamiento de los logs.
 *
 * @author raulc
 */


public class Log {
    public static Logger log = LogManager.getLogger("log");
    public static Logger logdb = LogManager.getLogger("logdb");

}
