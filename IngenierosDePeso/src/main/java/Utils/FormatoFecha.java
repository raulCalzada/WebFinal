/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase para manejar operaciones relacionadas con fechas.
 * Esta clase proporciona métodos y utilidades para trabajar con fechas, como cálculos, comparaciones y formateo de fechas.
 * También puede incluir funciones para convertir fechas entre diferentes formatos o realizar operaciones específicas relacionadas con fechas.
 *
 *@author raulc
 */

public class FormatoFecha {
    /**
     * Para pasar una fecha al formato yyyy-MM-dd HH:mm:ss
     * Esta clase se encarga de manejar datos con fechas
     * @param fecha
     * @return la fecha en el formato yyyy-MM-dd HH:mm:ss
     */

    public String formatearFechaSeg(String fecha) {
        String nuevoFormato = "yyyy-MM-dd HH:mm:ss";

        LocalDateTime date = LocalDateTime.parse(fecha);
        DateTimeFormatter formatoNuevo = DateTimeFormatter.ofPattern(nuevoFormato);
        String fechaFormateada = date.format(formatoNuevo);
        return fechaFormateada;
    }

    /**
     * Para pasar una fecha al formato yyyy-MM-dd HH:mm
     * @param fecha
     * @return la fecha en el formato yyyy-MM-dd HH:mm
     */
    public String formatearFechaMin(String fecha) {
        String nuevoFormato = "yyyy-MM-dd HH:mm";

        LocalDateTime date = LocalDateTime.parse(fecha);
        DateTimeFormatter formatoNuevo = DateTimeFormatter.ofPattern(nuevoFormato);
        String fechaFormateada = date.format(formatoNuevo);
        return fechaFormateada;
    }

    /**
     * Compara la fecha y ve si la primera es menor o igual a la primera y la tercera con la segunda
     * @param menor
     * @param medio
     * @param mayor
     * @return 
     */
    public Boolean fechaMenorIgualFecha(String menor, String medio, String mayor) {
        DateTimeFormatter formatoMenorMayor = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        DateTimeFormatter formatoMedio = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
        LocalDateTime dMedio = LocalDateTime.parse(medio, formatoMedio);
        LocalDateTime dMayor = LocalDateTime.parse(mayor, formatoMenorMayor);
        LocalDateTime dMenor = LocalDateTime.parse(menor, formatoMenorMayor);
        
        Boolean menorQue = (dMenor.isBefore(dMedio) || dMenor.isEqual(dMedio)) && (dMedio.isBefore(dMayor) || dMedio.isEqual(dMayor)) ;

        return menorQue;
    }
    

}
