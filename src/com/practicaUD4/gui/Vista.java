package com.practicaUD4.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.practicaUD4.base.Cliente;
import com.practicaUD4.base.Proveedor;
import com.practicaUD4.base.Producto;

import javax.swing.*;
import java.awt.*;

public class Vista {
    private static final Color ROSA_FONDO = new Color(255, 240, 245);
    private static final Color ROSA_PANEL = new Color(255, 220, 230);
    private static final Color CREMA_CAMPO = new Color(255, 250, 240);
    private static final Color MENTA_NUEVO = new Color(180, 230, 200);
    private static final Color MELOCOTON = new Color(255, 210, 160);
    private static final Color SALMON = new Color(255, 180, 180);
    private static final Color CIRUELA = new Color(130, 80, 100);
    private static final Color LISTA_FONDO = new Color(245, 255, 250);
    public JTabbedPane tabbedPane1;
    public JPanel panel1;
    public JPanel JPanelCliente;
    public JPanel JPanelProveedores;
    public JPanel JPanelProducto;
    public JTextField txtNombreCliente;
    public JTextField txtApellidosCliente;
    public DatePicker dateFechaNacimiento;
    public JTextField txtCiudadCliente;
    public JTextField txtTelefonoCliente;
    public JTextField txtEmailCliente;
    public JTextField txtCategoriaCliente;
    public JList listClientes;
    public JButton btnNuevoCliente;
    public JButton modificarButton;
    public JButton btnEliminarCliente;
    DefaultListModel<Cliente> dlmClientes;
    public JTextField txtNombreProveedor;
    public JTextField txtEmailProveedor;
    public JTextField txtTelefonoProveedor;
    public JTextField txtTipoProveedor;
    public JTextField txtWebProveedor;
    public JTextField txtDireccionProveedor;
    public JTextField txtContactoProveedor;
    public JList listProveedores;
    public JButton btnNuevoProveedor;
    public JButton btnModificarProveedor;
    public JButton btnEliminarProveedor;
    DefaultListModel<Proveedor> dlmProveedores;
    public JTextField txtNombreProducto;
    public JTextField txtCodigoProducto;
    public JComboBox<String> cmbProveedorProducto;
    public JTextField txtTipoProducto;
    public JComboBox<String> cmbClienteProducto;
    public JTextField txtPrecioProducto;
    public DatePicker dateCaducidadProducto;
    public JTextField txtStockProducto;
    public JList listProductos;
    public JButton btnNuevoProducto;
    public JButton btnModificarProducto;
    public JButton btnEliminarProducto;
    DefaultListModel<Producto> dlmProductos;

    public Vista() {
        construirUI();
        JFrame frame = new JFrame("Heladería - MongoDB");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 650);
        frame.setMinimumSize(new Dimension(800, 650));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        inicializar();
    }

    private void construirUI() {
        panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(ROSA_FONDO);
        tabbedPane1 = new JTabbedPane();
        tabbedPane1.setBackground(ROSA_PANEL);
        tabbedPane1.setFont(new Font("SansSerif", Font.BOLD, 12));
        JPanelCliente = construirPanelClientes();
        JPanelProveedores = construirPanelProveedores();
        JPanelProducto = construirPanelProductos();
        tabbedPane1.addTab("Clientes", JPanelCliente);
        tabbedPane1.addTab("Proveedores", JPanelProveedores);
        tabbedPane1.addTab("Productos", JPanelProducto);
        panel1.add(tabbedPane1, BorderLayout.CENTER);
    }

    private JPanel construirPanelClientes() {
        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.setBackground(ROSA_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JPanel form = new JPanel(new GridLayout(7, 2, 6, 6));
        form.setBackground(ROSA_PANEL);
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtNombreCliente = campo();
        txtApellidosCliente = campo();
        dateFechaNacimiento = new DatePicker();
        txtCiudadCliente = campo();
        txtTelefonoCliente = campo();
        txtEmailCliente = campo();
        txtCategoriaCliente = campo();
        form.add(etiqueta("Nombre:"));
        form.add(txtNombreCliente);
        form.add(etiqueta("Apellidos:"));
        form.add(txtApellidosCliente);
        form.add(etiqueta("F. Nacimiento:"));
        form.add(dateFechaNacimiento);
        form.add(etiqueta("Ciudad:"));
        form.add(txtCiudadCliente);
        form.add(etiqueta("Teléfono:"));
        form.add(txtTelefonoCliente);
        form.add(etiqueta("Email:"));
        form.add(txtEmailCliente);
        form.add(etiqueta("Categoría:"));
        form.add(txtCategoriaCliente);
        listClientes = lista();
        JScrollPane scroll = scroll(listClientes);
        btnNuevoCliente = boton("Añadir", MENTA_NUEVO, "Nuevo Cliente");
        modificarButton = boton("Modificar", MELOCOTON, "Modificar Cliente");
        btnEliminarCliente = boton("Eliminar", SALMON, "Eliminar Cliente");
        JPanel botonesPanel = new JPanel(new GridLayout(3, 1, 0, 6));
        botonesPanel.setBackground(ROSA_FONDO);
        botonesPanel.add(btnNuevoCliente);
        botonesPanel.add(modificarButton);
        botonesPanel.add(btnEliminarCliente);
        JPanel derechaPanel = new JPanel(new BorderLayout(0, 6));
        derechaPanel.setBackground(ROSA_FONDO);
        derechaPanel.add(botonesPanel, BorderLayout.NORTH);
        JPanel centro = new JPanel(new GridLayout(1, 2, 6, 0));
        centro.setBackground(ROSA_FONDO);
        centro.add(scroll);
        centro.add(derechaPanel);
        panel.add(form, BorderLayout.NORTH);
        panel.add(centro, BorderLayout.CENTER);
        return panel;
    }

    private JPanel construirPanelProveedores() {
        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.setBackground(ROSA_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JPanel form = new JPanel(new GridLayout(7, 2, 6, 6));
        form.setBackground(ROSA_PANEL);
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtNombreProveedor = campo();
        txtEmailProveedor = campo();
        txtTelefonoProveedor = campo();
        txtTipoProveedor = campo();
        txtWebProveedor = campo();
        txtDireccionProveedor = campo();
        txtContactoProveedor = campo();
        form.add(etiqueta("Nombre:"));
        form.add(txtNombreProveedor);
        form.add(etiqueta("Email:"));
        form.add(txtEmailProveedor);
        form.add(etiqueta("Teléfono:"));
        form.add(txtTelefonoProveedor);
        form.add(etiqueta("Tipo Proveedor:"));
        form.add(txtTipoProveedor);
        form.add(etiqueta("Web:"));
        form.add(txtWebProveedor);
        form.add(etiqueta("Dirección:"));
        form.add(txtDireccionProveedor);
        form.add(etiqueta("Contacto principal:"));
        form.add(txtContactoProveedor);
        listProveedores = lista();
        JScrollPane scroll = scroll(listProveedores);
        btnNuevoProveedor = boton("Añadir", MENTA_NUEVO, "Nuevo Proveedor");
        btnModificarProveedor = boton("Modificar", MELOCOTON, "Modificar Proveedor");
        btnEliminarProveedor = boton("Eliminar", SALMON, "Eliminar Proveedor");
        JPanel botonesPanel = new JPanel(new GridLayout(3, 1, 0, 6));
        botonesPanel.setBackground(ROSA_FONDO);
        botonesPanel.add(btnNuevoProveedor);
        botonesPanel.add(btnModificarProveedor);
        botonesPanel.add(btnEliminarProveedor);
        JPanel derechaPanel = new JPanel(new BorderLayout(0, 6));
        derechaPanel.setBackground(ROSA_FONDO);
        derechaPanel.add(botonesPanel, BorderLayout.NORTH);
        JPanel centro = new JPanel(new GridLayout(1, 2, 6, 0));
        centro.setBackground(ROSA_FONDO);
        centro.add(scroll);
        centro.add(derechaPanel);
        panel.add(form, BorderLayout.NORTH);
        panel.add(centro, BorderLayout.CENTER);
        return panel;
    }

    private JPanel construirPanelProductos() {
        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.setBackground(ROSA_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JPanel form = new JPanel(new GridLayout(8, 2, 6, 6));
        form.setBackground(ROSA_PANEL);
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtNombreProducto = campo();
        txtCodigoProducto = campo();
        cmbProveedorProducto = combo();
        txtTipoProducto = campo();
        cmbClienteProducto = combo();
        txtPrecioProducto = campo();
        dateCaducidadProducto = new DatePicker();
        txtStockProducto = campo();
        form.add(etiqueta("Nombre:"));
        form.add(txtNombreProducto);
        form.add(etiqueta("Código:"));
        form.add(txtCodigoProducto);
        form.add(etiqueta("Proveedor:"));
        form.add(cmbProveedorProducto);
        form.add(etiqueta("Tipo:"));
        form.add(txtTipoProducto);
        form.add(etiqueta("Cliente:"));
        form.add(cmbClienteProducto);
        form.add(etiqueta("Precio:"));
        form.add(txtPrecioProducto);
        form.add(etiqueta("F. Caducidad:"));
        form.add(dateCaducidadProducto);
        form.add(etiqueta("Stock:"));
        form.add(txtStockProducto);
        listProductos = lista();
        JScrollPane scroll = scroll(listProductos);
        btnNuevoProducto = boton("Añadir", MENTA_NUEVO, "Nuevo Producto");
        btnModificarProducto = boton("Modificar", MELOCOTON, "Modificar Producto");
        btnEliminarProducto = boton("Eliminar", SALMON, "Eliminar Producto");
        JPanel botonesPanel = new JPanel(new GridLayout(3, 1, 0, 6));
        botonesPanel.setBackground(ROSA_FONDO);
        botonesPanel.add(btnNuevoProducto);
        botonesPanel.add(btnModificarProducto);
        botonesPanel.add(btnEliminarProducto);
        JPanel derechaPanel = new JPanel(new BorderLayout(0, 6));
        derechaPanel.setBackground(ROSA_FONDO);
        derechaPanel.add(botonesPanel, BorderLayout.NORTH);
        JPanel centro = new JPanel(new GridLayout(1, 2, 6, 0));
        centro.setBackground(ROSA_FONDO);
        centro.add(scroll);
        centro.add(derechaPanel);
        panel.add(form, BorderLayout.NORTH);
        panel.add(centro, BorderLayout.CENTER);
        return panel;
    }

    private JLabel etiqueta(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(CIRUELA);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        return lbl;
    }

    private JTextField campo() {
        JTextField tf = new JTextField();
        tf.setBackground(CREMA_CAMPO);
        tf.setFont(new Font("SansSerif", Font.PLAIN, 12));
        return tf;
    }

    private JComboBox<String> combo() {
        JComboBox<String> cb = new JComboBox<>();
        cb.setBackground(CREMA_CAMPO);
        cb.setFont(new Font("SansSerif", Font.PLAIN, 12));
        return cb;
    }

    private JList lista() {
        JList list = new JList();
        list.setBackground(LISTA_FONDO);
        list.setFont(new Font("SansSerif", Font.PLAIN, 12));
        list.setSelectionBackground(new Color(255, 200, 215));
        list.setSelectionForeground(CIRUELA);
        return list;
    }

    private JScrollPane scroll(JList list) {
        JScrollPane sp = new JScrollPane(list);
        sp.getViewport().setBackground(LISTA_FONDO);
        sp.setPreferredSize(new Dimension(200, 160));
        return sp;
    }

    private JButton boton(String texto, Color color, String actionCommand) {
        JButton btn = new JButton(texto);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setActionCommand(actionCommand);
        return btn;
    }

    private void inicializar() {
        dlmClientes = new DefaultListModel<>();
        listClientes.setModel(dlmClientes);
        dlmProveedores = new DefaultListModel<>();
        listProveedores.setModel(dlmProveedores);
        dlmProductos = new DefaultListModel<>();
        listProductos.setModel(dlmProductos);
        dateFechaNacimiento.getComponentToggleCalendarButton().setText("Calendario");
        dateCaducidadProducto.getComponentToggleCalendarButton().setText("Calendario");
    }

    public void limpiarCamposCliente() {
        txtNombreCliente.setText("");
        txtApellidosCliente.setText("");
        dateFechaNacimiento.setDate(null);
        txtCiudadCliente.setText("");
        txtTelefonoCliente.setText("");
        txtEmailCliente.setText("");
        txtCategoriaCliente.setText("");
    }

    public void limpiarCamposProveedor() {
        txtNombreProveedor.setText("");
        txtEmailProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtTipoProveedor.setText("");
        txtWebProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtContactoProveedor.setText("");
    }

    public void limpiarCamposProducto() {
        txtNombreProducto.setText("");
        txtCodigoProducto.setText("");
        if (cmbProveedorProducto.getItemCount() > 0) cmbProveedorProducto.setSelectedIndex(0);
        txtTipoProducto.setText("");
        if (cmbClienteProducto.getItemCount() > 0) cmbClienteProducto.setSelectedIndex(0);
        txtPrecioProducto.setText("");
        dateCaducidadProducto.setDate(null);
        txtStockProducto.setText("");
    }
}