/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author raulc
 */
public class FormatoFecha {

    public String formatearFechaSeg(String fecha) {
        String nuevoFormato = "yyyy-MM-dd HH:mm:ss";

        LocalDateTime date = LocalDateTime.parse(fecha);
        DateTimeFormatter formatoNuevo = DateTimeFormatter.ofPattern(nuevoFormato);
        String fechaFormateada = date.format(formatoNuevo);
        return fechaFormateada;
    }

    public String formatearFechaMin(String fecha) {
        String nuevoFormato = "yyyy-MM-dd HH:mm";

        LocalDateTime date = LocalDateTime.parse(fecha);
        DateTimeFormatter formatoNuevo = DateTimeFormatter.ofPattern(nuevoFormato);
        String fechaFormateada = date.format(formatoNuevo);
        return fechaFormateada;
    }

    //compara las fechas y verifica si fecha1<=fecha2
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
