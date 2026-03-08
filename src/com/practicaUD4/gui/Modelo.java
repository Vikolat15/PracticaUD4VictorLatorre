package com.practicaUD4.gui;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.practicaUD4.base.Cliente;
import com.practicaUD4.base.Proveedor;
import com.practicaUD4.base.Producto;
import com.practicaUD4.util.Util;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Modelo {
    private final static String COLECCION_CLIENTES = "Clientes";
    private final static String COLECCION_PROVEEDORES = "Proveedores";
    private final static String COLECCION_PRODUCTOS = "Productos";
    private final static String DATABASE = "base_heladeria";
    private MongoClient client;
    private MongoDatabase baseDatos;
    private MongoCollection coleccionClientes;
    private MongoCollection coleccionProveedores;
    private MongoCollection coleccionProductos;

    public void conectar() {
        client = new MongoClient();
        baseDatos = client.getDatabase(DATABASE);
        coleccionClientes = baseDatos.getCollection(COLECCION_CLIENTES);
        coleccionProveedores = baseDatos.getCollection(COLECCION_PROVEEDORES);
        coleccionProductos = baseDatos.getCollection(COLECCION_PRODUCTOS);
    }

    public void desconectar() {
        client.close();
    }

    public void guardarCliente(Cliente unCliente) {
        coleccionClientes.insertOne(clienteToDocument(unCliente));
    }

    public List<Cliente> getClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        Iterator<Document> it = coleccionClientes.find().iterator();
        while (it.hasNext()) lista.add(documentToCliente(it.next()));
        return lista;
    }

    public List<Cliente> getClientes(String text) {
        ArrayList<Cliente> lista = new ArrayList<>();
        Document query = new Document();
        List<Document> criterios = new ArrayList<>();
        criterios.add(new Document("nombre", new Document("$regex", "/*" + text + "/*")));
        criterios.add(new Document("apellidos", new Document("$regex", "/*" + text + "/*")));
        query.append("$or", criterios);
        Iterator<Document> it = coleccionClientes.find(query).iterator();
        while (it.hasNext()) lista.add(documentToCliente(it.next()));
        return lista;
    }

    public void modificarCliente(Cliente unCliente) {
        coleccionClientes.replaceOne(new Document("_id", unCliente.getId()), clienteToDocument(unCliente));
    }

    public void borrarCliente(Cliente unCliente) {
        coleccionClientes.deleteOne(new Document("_id", unCliente.getId()));
    }

    public Document clienteToDocument(Cliente c) {
        Document d = new Document();
        d.append("nombre", c.getNombre());
        d.append("apellidos", c.getApellidos());
        d.append("fechaNacimiento", Util.formatearFecha(c.getFechaNacimiento()));
        d.append("ciudad", c.getCiudad());
        d.append("telefono", c.getTelefono());
        d.append("email", c.getEmail());
        d.append("categoria", c.getCategoria());
        return d;
    }

    public Cliente documentToCliente(Document d) {
        Cliente c = new Cliente();
        c.setId(d.getObjectId("_id"));
        c.setNombre(d.getString("nombre"));
        c.setApellidos(d.getString("apellidos"));
        c.setFechaNacimiento(Util.parsearFecha(d.getString("fechaNacimiento")));
        c.setCiudad(d.getString("ciudad"));
        c.setTelefono(d.getString("telefono"));
        c.setEmail(d.getString("email"));
        c.setCategoria(d.getString("categoria"));
        return c;
    }

    public void guardarProveedor(Proveedor unProveedor) {
        coleccionProveedores.insertOne(proveedorToDocument(unProveedor));
    }

    public List<Proveedor> getProveedores() {
        ArrayList<Proveedor> lista = new ArrayList<>();
        Iterator<Document> it = coleccionProveedores.find().iterator();
        while (it.hasNext()) lista.add(documentToProveedor(it.next()));
        return lista;
    }

    public List<Proveedor> getProveedores(String text) {
        ArrayList<Proveedor> lista = new ArrayList<>();
        Document query = new Document();
        List<Document> criterios = new ArrayList<>();
        criterios.add(new Document("nombre", new Document("$regex", "/*" + text + "/*")));
        criterios.add(new Document("tipoProveedor", new Document("$regex", "/*" + text + "/*")));
        query.append("$or", criterios);
        Iterator<Document> it = coleccionProveedores.find(query).iterator();
        while (it.hasNext()) lista.add(documentToProveedor(it.next()));
        return lista;
    }

    public void modificarProveedor(Proveedor unProveedor) {
        coleccionProveedores.replaceOne(new Document("_id", unProveedor.getId()), proveedorToDocument(unProveedor));
    }

    public void borrarProveedor(Proveedor unProveedor) {
        coleccionProveedores.deleteOne(new Document("_id", unProveedor.getId()));
    }

    public Document proveedorToDocument(Proveedor p) {
        Document d = new Document();
        d.append("nombre", p.getNombre());
        d.append("email", p.getEmail());
        d.append("telefono", p.getTelefono());
        d.append("tipoProveedor", p.getTipoProveedor());
        d.append("web", p.getWeb());
        d.append("direccion", p.getDireccion());
        d.append("contactoPrincipal", p.getContactoPrincipal());
        return d;
    }

    public Proveedor documentToProveedor(Document d) {
        Proveedor p = new Proveedor();
        p.setId(d.getObjectId("_id"));
        p.setNombre(d.getString("nombre"));
        p.setEmail(d.getString("email"));
        p.setTelefono(d.getString("telefono"));
        p.setTipoProveedor(d.getString("tipoProveedor"));
        p.setWeb(d.getString("web"));
        p.setDireccion(d.getString("direccion"));
        p.setContactoPrincipal(d.getString("contactoPrincipal"));
        return p;
    }

    public void guardarProducto(Producto unProducto) {
        coleccionProductos.insertOne(productoToDocument(unProducto));
    }

    public List<Producto> getProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        Iterator<Document> it = coleccionProductos.find().iterator();
        while (it.hasNext()) lista.add(documentToProducto(it.next()));
        return lista;
    }

    public List<Producto> getProductos(String text) {
        ArrayList<Producto> lista = new ArrayList<>();
        Document query = new Document();
        List<Document> criterios = new ArrayList<>();
        criterios.add(new Document("nombre", new Document("$regex", "/*" + text + "/*")));
        criterios.add(new Document("codigo", new Document("$regex", "/*" + text + "/*")));
        query.append("$or", criterios);
        Iterator<Document> it = coleccionProductos.find(query).iterator();
        while (it.hasNext()) lista.add(documentToProducto(it.next()));
        return lista;
    }

    public void modificarProducto(Producto unProducto) {
        coleccionProductos.replaceOne(new Document("_id", unProducto.getId()), productoToDocument(unProducto));
    }

    public void borrarProducto(Producto unProducto) {
        coleccionProductos.deleteOne(new Document("_id", unProducto.getId()));
    }

    public Document productoToDocument(Producto p) {
        Document d = new Document();
        d.append("nombre", p.getNombre());
        d.append("codigo", p.getCodigo());
        d.append("idProveedor", p.getIdProveedor());
        d.append("tipo", p.getTipo());
        d.append("idCliente", p.getIdCliente());
        d.append("precio", p.getPrecio());
        d.append("fechaCaducidad", Util.formatearFecha(p.getFechaCaducidad()));
        d.append("stock", p.getStock());
        return d;
    }

    public Producto documentToProducto(Document d) {
        Producto p = new Producto();
        p.setId(d.getObjectId("_id"));
        p.setNombre(d.getString("nombre"));
        p.setCodigo(d.getString("codigo"));
        p.setIdProveedor(d.getString("idProveedor"));
        p.setTipo(d.getString("tipo"));
        p.setIdCliente(d.getString("idCliente"));
        Object precioObj = d.get("precio");
        if (precioObj instanceof Double) p.setPrecio((Double) precioObj);
        else if (precioObj instanceof Integer) p.setPrecio(((Integer) precioObj).doubleValue());
        p.setFechaCaducidad(Util.parsearFecha(d.getString("fechaCaducidad")));
        Object stockObj = d.get("stock");
        if (stockObj instanceof Integer) p.setStock((Integer) stockObj);
        return p;
    }
}