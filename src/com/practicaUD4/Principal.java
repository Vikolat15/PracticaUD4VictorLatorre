package com.practicaUD4;

import com.practicaUD4.gui.Modelo;
import com.practicaUD4.gui.Controlador;
import com.practicaUD4.gui.Vista;

public class Principal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
