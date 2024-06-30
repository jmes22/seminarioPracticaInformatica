package salgueroortiz.agilscrum.BE;

/**
 *
 * @author juan1
 */
public class Rol {
    private int id;
    private String nombre;
    private String codigo;

     public Rol() { }
     
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
