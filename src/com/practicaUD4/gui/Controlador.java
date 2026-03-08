package com.practicaUD4.gui;

import com.practicaUD4.base.Cliente;
import com.practicaUD4.base.Proveedor;
import com.practicaUD4.base.Producto;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controlador implements ActionListener, ListSelectionListener {
    Vista vista;
    Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        inicializar();
    }

    private void inicializar() {
        addActionListeners(this);
        addListSelectionListeners(this);
        modelo.conectar();
        listarClientes(modelo.getClientes());
        listarProveedores(modelo.getProveedores());
        listarProductos(modelo.getProductos());
        refrescarComboProveedores();
        refrescarComboClientes();
    }

    private void addActionListeners(ActionListener listener) {
        vista.btnNuevoCliente.addActionListener(listener);
        vista.modificarButton.addActionListener(listener);
        vista.btnEliminarCliente.addActionListener(listener);
        vista.btnNuevoProveedor.addActionListener(listener);
        vista.btnModificarProveedor.addActionListener(listener);
        vista.btnEliminarProveedor.addActionListener(listener);
        vista.btnNuevoProducto.addActionListener(listener);
        vista.btnModificarProducto.addActionListener(listener);
        vista.btnEliminarProducto.addActionListener(listener);
    }

    private void addListSelectionListeners(ListSelectionListener listener) {
        vista.listClientes.addListSelectionListener(listener);
        vista.listProveedores.addListSelectionListener(listener);
        vista.listProductos.addListSelectionListener(listener);
    }

    private void refrescarComboProveedores() {
        vista.cmbProveedorProducto.removeAllItems();
        for (Proveedor p : modelo.getProveedores()) {
            vista.cmbProveedorProducto.addItem(p.getNombre());
        }
    }

    private void refrescarComboClientes() {
        vista.cmbClienteProducto.removeAllItems();
        for (Cliente c : modelo.getClientes()) {
            vista.cmbClienteProducto.addItem(c.getApellidos() + ", " + c.getNombre());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        Cliente unCliente;
        Proveedor unProveedor;
        Producto unProducto;
        switch (comando) {
            case "Nuevo Cliente":
                unCliente = new Cliente();
                modificarClienteFromCampos(unCliente);
                modelo.guardarCliente(unCliente);
                listarClientes(modelo.getClientes());
                refrescarComboClientes();
                vista.limpiarCamposCliente();
                break;
            case "Modificar Cliente":
                unCliente = (Cliente) vista.listClientes.getSelectedValue();
                if (unCliente == null) break;
                modificarClienteFromCampos(unCliente);
                modelo.modificarCliente(unCliente);
                listarClientes(modelo.getClientes());
                refrescarComboClientes();
                break;
            case "Eliminar Cliente":
                unCliente = (Cliente) vista.listClientes.getSelectedValue();
                if (unCliente == null) break;
                modelo.borrarCliente(unCliente);
                listarClientes(modelo.getClientes());
                refrescarComboClientes();
                vista.limpiarCamposCliente();
                break;
            case "Nuevo Proveedor":
                unProveedor = new Proveedor();
                modificarProveedorFromCampos(unProveedor);
                modelo.guardarProveedor(unProveedor);
                listarProveedores(modelo.getProveedores());
                refrescarComboProveedores();
                vista.limpiarCamposProveedor();
                break;
            case "Modificar Proveedor":
                unProveedor = (Proveedor) vista.listProveedores.getSelectedValue();
                if (unProveedor == null) break;
                modificarProveedorFromCampos(unProveedor);
                modelo.modificarProveedor(unProveedor);
                listarProveedores(modelo.getProveedores());
                refrescarComboProveedores();
                break;
            case "Eliminar Proveedor":
                unProveedor = (Proveedor) vista.listProveedores.getSelectedValue();
                if (unProveedor == null) break;
                modelo.borrarProveedor(unProveedor);
                listarProveedores(modelo.getProveedores());
                refrescarComboProveedores();
                vista.limpiarCamposProveedor();
                break;
            case "Nuevo Producto":
                unProducto = new Producto();
                modificarProductoFromCampos(unProducto);
                modelo.guardarProducto(unProducto);
                listarProductos(modelo.getProductos());
                vista.limpiarCamposProducto();
                break;
            case "Modificar Producto":
                unProducto = (Producto) vista.listProductos.getSelectedValue();
                if (unProducto == null) break;
                modificarProductoFromCampos(unProducto);
                modelo.modificarProducto(unProducto);
                listarProductos(modelo.getProductos());
                break;
            case "Eliminar Producto":
                unProducto = (Producto) vista.listProductos.getSelectedValue();
                if (unProducto == null) break;
                modelo.borrarProducto(unProducto);
                listarProductos(modelo.getProductos());
                vista.limpiarCamposProducto();
                break;
        }
    }

    private void listarClientes(List<Cliente> lista) {
        vista.dlmClientes.clear();
        for (Cliente c : lista) vista.dlmClientes.addElement(c);
    }

    private void listarProveedores(List<Proveedor> lista) {
        vista.dlmProveedores.clear();
        for (Proveedor p : lista) vista.dlmProveedores.addElement(p);
    }

    private void listarProductos(List<Producto> lista) {
        vista.dlmProductos.clear();
        for (Producto p : lista) vista.dlmProductos.addElement(p);
    }

    private void modificarClienteFromCampos(Cliente c) {
        c.setNombre(vista.txtNombreCliente.getText());
        c.setApellidos(vista.txtApellidosCliente.getText());
        c.setFechaNacimiento(vista.dateFechaNacimiento.getDate());
        c.setCiudad(vista.txtCiudadCliente.getText());
        c.setTelefono(vista.txtTelefonoCliente.getText());
        c.setEmail(vista.txtEmailCliente.getText());
        c.setCategoria(vista.txtCategoriaCliente.getText());
    }

    private void modificarProveedorFromCampos(Proveedor p) {
        p.setNombre(vista.txtNombreProveedor.getText());
        p.setEmail(vista.txtEmailProveedor.getText());
        p.setTelefono(vista.txtTelefonoProveedor.getText());
        p.setTipoProveedor(vista.txtTipoProveedor.getText());
        p.setWeb(vista.txtWebProveedor.getText());
        p.setDireccion(vista.txtDireccionProveedor.getText());
        p.setContactoPrincipal(vista.txtContactoProveedor.getText());
    }

    private void modificarProductoFromCampos(Producto p) {
        p.setNombre(vista.txtNombreProducto.getText());
        p.setCodigo(vista.txtCodigoProducto.getText());
        p.setIdProveedor((String) vista.cmbProveedorProducto.getSelectedItem());
        p.setTipo(vista.txtTipoProducto.getText());
        p.setIdCliente((String) vista.cmbClienteProducto.getSelectedItem());
        String precioTxt = vista.txtPrecioProducto.getText().trim();
        p.setPrecio(precioTxt.isEmpty() ? 0.0 : Double.parseDouble(precioTxt));
        p.setFechaCaducidad(vista.dateCaducidadProducto.getDate());
        String stockTxt = vista.txtStockProducto.getText().trim();
        p.setStock(stockTxt.isEmpty() ? 0 : Integer.parseInt(stockTxt));
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) return;
        if (e.getSource() == vista.listClientes) {
            Cliente c = (Cliente) vista.listClientes.getSelectedValue();
            if (c != null) {
                vista.txtNombreCliente.setText(c.getNombre());
                vista.txtApellidosCliente.setText(c.getApellidos());
                vista.dateFechaNacimiento.setDate(c.getFechaNacimiento());
                vista.txtCiudadCliente.setText(c.getCiudad());
                vista.txtTelefonoCliente.setText(c.getTelefono());
                vista.txtEmailCliente.setText(c.getEmail());
                vista.txtCategoriaCliente.setText(c.getCategoria());
            }
        }
        if (e.getSource() == vista.listProveedores) {
            Proveedor p = (Proveedor) vista.listProveedores.getSelectedValue();
            if (p != null) {
                vista.txtNombreProveedor.setText(p.getNombre());
                vista.txtEmailProveedor.setText(p.getEmail());
                vista.txtTelefonoProveedor.setText(p.getTelefono());
                vista.txtTipoProveedor.setText(p.getTipoProveedor());
                vista.txtWebProveedor.setText(p.getWeb());
                vista.txtDireccionProveedor.setText(p.getDireccion());
                vista.txtContactoProveedor.setText(p.getContactoPrincipal());
            }
        }
        if (e.getSource() == vista.listProductos) {
            Producto p = (Producto) vista.listProductos.getSelectedValue();
            if (p != null) {
                vista.txtNombreProducto.setText(p.getNombre());
                vista.txtCodigoProducto.setText(p.getCodigo());
                seleccionarEnCombo(vista.cmbProveedorProducto, p.getIdProveedor());
                vista.txtTipoProducto.setText(p.getTipo());
                seleccionarEnCombo(vista.cmbClienteProducto, p.getIdCliente());
                vista.txtPrecioProducto.setText(String.valueOf(p.getPrecio()));
                vista.dateCaducidadProducto.setDate(p.getFechaCaducidad());
                vista.txtStockProducto.setText(String.valueOf(p.getStock()));
            }
        }
    }

    private void seleccionarEnCombo(JComboBox<String> combo, String valor) {
        if (valor == null) return;
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (valor.equals(combo.getItemAt(i))) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }
}