package salgueroortiz.agilscrum;

/**
 *
 * @author juan1
 */
public class Estado {
    private String nombre;
    private String codigo;

    public Estado(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
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
