/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Clase que representa un proyecto.
 * Esta clase encapsula los datos y atributos relacionados con un proyecto, como su nombre, descripción, fecha de inicio, fecha de finalización, etc.
 * Proporciona métodos para acceder y modificar los datos del proyecto, así como para realizar operaciones relacionadas con el mismo.
 * Esta clase se utiliza para instanciar y gestionar proyectos dentro de una aplicación o sistema.
 *
 * @author raulc
 */

public class Project {
    private String id_proyecto;
    private String nombre;
    private String id_empresa;

    public String getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(String id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }
    
    
    
    
}
