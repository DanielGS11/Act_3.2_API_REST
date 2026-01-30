package com.activity.tiendajuegos.request;

/*
 Esta clase es unicamente para el metodo actualizar del controller de videojuego, ya que en la URL no deben
 aparecer los paraemtros de esta clase, por lo que se hara un RequestBody con esta clase
 */
public class ActualizacionJuego {
    private double newPrecio;
    private int newStock;

    public double getNewPrecio() {
        return newPrecio;
    }

    public void setNewPrecio(double newPrecio) {
        this.newPrecio = newPrecio;
    }

    public int getNewStock() {
        return newStock;
    }

    public void setNewStock(int newStock) {
        this.newStock = newStock;
    }
}
