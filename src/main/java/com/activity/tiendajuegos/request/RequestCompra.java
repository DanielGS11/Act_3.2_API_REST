package com.activity.tiendajuegos.request;

/*
 Esta clase es unicamente para el metodo comprar del controller de tienda, ya que en la URL no deben
 aparecer los paraemtros de esta clase, por lo que se hara un RequestBody con esta clase
 */
public class RequestCompra {
    private Long idCliente;
    private Long idVideojuego;


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(Long idVideojuego) {
        this.idVideojuego = idVideojuego;
    }
}
