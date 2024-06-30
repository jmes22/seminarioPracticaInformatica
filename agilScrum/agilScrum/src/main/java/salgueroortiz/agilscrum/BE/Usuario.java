package salgueroortiz.agilscrum.BE;

/**
 *
 * @author juan1
 */
public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String mail;
    private String usuario;
    private String contrasena;
    private int idRol;

    public Usuario() { }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
       
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
     public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String getContrasena() {
        return contrasena;
    }

    public void setCodigo(String contrasena) {
        this.contrasena = contrasena;
    }
    
     public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}
