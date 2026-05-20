package model;

//Clase y atributos

public class Carta{
    private int id;
    private String nombre;
    private int elixir;
    private String rareza;
    private String tipo;

//Constructor

    public Carta(String nombre, int elixir, String rareza, String tipo) {
        this.elixir = elixir;
        this.nombre = nombre;
        this.rareza = rareza;
        this.tipo = tipo;
    }

// Getters y Setters

    public int getElixir() {
        return elixir;
    }

    public void setElixir(int elixir) {
        this.elixir = elixir;
    }

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

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

//Metodo toString()

    @Override
    public String toString() {
        return "Carta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rareza='" + rareza + '\'' +
                ", elixir=" + elixir +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
