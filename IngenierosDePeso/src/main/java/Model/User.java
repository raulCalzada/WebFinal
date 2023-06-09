/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Clase que representa a un usuario.
 * Esta clase encapsula los datos y atributos relacionados con un usuario, como su nombre, apellido, dirección de correo electrónico, etc.
 * Además, tiene propiedades para las instancias de las clases "Empresa" y "Proyecto" asociadas al usuario.
 * Proporciona métodos para acceder y modificar los datos del usuario, así como para realizar operaciones relacionadas con las empresas y proyectos asociados.
 * Esta clase se utiliza para gestionar la información y acciones relacionadas con los usuarios en una aplicación o sistema.
 *
 * @author raulc
 */

public class User {
    String tipo;
    String id;
    String username;
    String password;
    String dni;
    String nombre, apellidos;
    String fecha_alta, fecha_baja;
    Project proyecto;
    Empresa empresa;

    public User() {
       
    }
    
    //solo para Usuarios tipo U
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
//para todos los usuarios

    public Project getProyecto() {
        return proyecto;
    }

    public void setProyecto(Project proyecto) {
        this.proyecto = proyecto;
    }
        
    
    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public String getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(String fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

   
    
  
    
    
}
