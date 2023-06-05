/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Clase que representa una empresa con todos sus parámetros.
 * Esta clase encapsula los datos y atributos de una empresa, como su nombre, dirección, número de teléfono, etc.
 * Proporciona métodos para acceder y modificar los parámetros de la empresa, así como para realizar operaciones relacionadas con la misma.
 * Esta clase se utiliza para gestionar la información y las acciones relacionadas con una empresa dentro de una aplicación o sistema.
 *
 * @author raulc
 */

public class Empresa {
    private String id_empresa;
    private String nombre_empresa;

    
    public String getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }
    
    
    
    
}
