/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Clase que representa el marcaje de un usuario.
 * Esta clase encapsula los datos y atributos relacionados con el marcaje de un usuario, como la fecha y hora del marcaje, el tipo de marcaje (entrada o salida), etc.
 * Proporciona métodos para acceder y modificar los datos del marcaje, así como para realizar operaciones relacionadas con el mismo.
 * Esta clase se utiliza para gestionar la información y las acciones relacionadas con los marcajes de los usuarios dentro de una aplicación o sistema.
 *
 * @author raulc
 */

public class Marcaje {
    private String id_marcaje;
    private String fecha;
    private String tipo_marcaje;
    private String id_usuario;

    public String getId_marcaje() {
        return id_marcaje;
    }

    public void setId_marcaje(String id_marcaje) {
        this.id_marcaje = id_marcaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_marcaje() {
        return tipo_marcaje;
    }

    public void setTipo_marcaje(String tipo_marcaje) {
        this.tipo_marcaje = tipo_marcaje;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
}
