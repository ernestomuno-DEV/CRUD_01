package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private ProductoService productoService;

   @GetMapping
   public List<Producto> listarProductos(){
    return productoService.obtenerProductos();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id){
    Producto producto = productoService.obtenerProductoPorId(id);
    if (producto != null){
        return ResponseEntity.ok(producto);
    } else {
        return ResponseEntity.notFound().build();
    }
   }

   @PostMapping
   public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto){
    if(producto.getNombre() != null && producto.getDescripcion() != null && producto.getStock() > 0){
        Producto creado = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }else{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
   }
}

   @PutMapping("/{id}")
   public ResponseEntity<Producto> actulizarProductoPorIProducto(@PathVariable Long id, @RequestBody Producto producto){
    Producto actualizado = productoService.actualizarProducto(id, producto);
    if(actualizado != null){
        return ResponseEntity.ok(actualizado);
    } else {
        return ResponseEntity.notFound().build();
    }
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
    productoService.eliminarProducto(id);
    return ResponseEntity.noContent().build();
   }

   

   }


    
