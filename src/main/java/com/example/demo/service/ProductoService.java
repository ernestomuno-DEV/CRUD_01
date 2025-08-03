package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;

@Service
public class ProductoService{
   private List<Producto> productos = new ArrayList<>();
   private Long nextId = 1L;

   public List<Producto> obtenerProductos(){
    return productos;
   }

   public Producto obtenerProductoPorId(Long id){
    for(Producto producto : productos){
        if(producto.getId().equals(id)){
            return producto;
        }
    }
    return null;
   }

   public Producto crearProducto(Producto producto){
    producto.setId(nextId++);
    productos.add(producto);
    return producto;
   }

   public Producto actualizarProducto(Long id, Producto producto){

    for(Producto p : productos){
        if(p.getId() == id){
            p.setNombre(producto.getNombre());
            p.setDescripcion(producto.getDescripcion());
            p.setStock(producto.getStock());
            return p;
        }
    }
    return null;


   }

   public void eliminarProducto(Long id){
    productos.removeIf(producto -> producto.getId().equals(id));
   }




}

