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
    public String formatearFechaSeg(String fecha){
        String nuevoFormato = "yyyy-MM-dd HH:mm:ss";
            
            LocalDateTime date = LocalDateTime.parse(fecha);
            DateTimeFormatter formatoNuevo = DateTimeFormatter.ofPattern(nuevoFormato);
            String fechaFormateada = date.format(formatoNuevo);
            return fechaFormateada;
    }
    public String formatearFechaMin(String fecha){
        String nuevoFormato = "yyyy-MM-dd HH:mm";
            
            LocalDateTime date = LocalDateTime.parse(fecha);
            DateTimeFormatter formatoNuevo = DateTimeFormatter.ofPattern(nuevoFormato);
            String fechaFormateada = date.format(formatoNuevo);
            return fechaFormateada;
    }
}
