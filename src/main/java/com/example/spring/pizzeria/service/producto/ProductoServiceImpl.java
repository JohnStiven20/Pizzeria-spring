package com.example.spring.pizzeria.service.producto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.spring.pizzeria.model.Alergeno;
import com.example.spring.pizzeria.model.Bebida;
import com.example.spring.pizzeria.model.Ingrediente;
import com.example.spring.pizzeria.model.Producto;
import com.example.spring.pizzeria.repository.AlergenoRepository;
import com.example.spring.pizzeria.repository.IngredienteRepository;
import com.example.spring.pizzeria.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private AlergenoRepository alergenoRepository;
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Override
    public void delete(Producto producto) {
        if (productoRepository.existsByNombre(producto.getNombre())) {
            productoRepository.delete(producto);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el producto");
        }
    }

    @Override
    public Producto update(Producto producto) {
        if (productoRepository.existsByNombre(producto.getNombre())) {
            return productoRepository.save(producto);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el producto");
        }
    }

    
    @Override
    public Producto save(Producto producto) {

        Optional<Producto> productoOptional = productoRepository.findByNombre(producto.getNombre());
        if (productoOptional.isPresent()) {
            return productoOptional.get();
        }
        
        if (producto instanceof Bebida) {
            producto.setIngredientes(null);
           return productoRepository.save(producto);
        }
        
        List<Ingrediente> ingredientesUnicos = producto.getIngredientes().stream()
                .map(ing -> saveIngrediente(ing))
                .distinct()
                .collect(Collectors.toList());

        producto.setIngredientes(ingredientesUnicos);

        return productoRepository.save(producto);
    }

    public Ingrediente saveIngrediente(Ingrediente ingrediente) {

        Optional<Ingrediente> ingredienteOptional = ingredienteRepository.findByNombre(ingrediente.getNombre());

        if (ingredienteOptional.isPresent()) {
            return ingredienteOptional.get();
        }

        List<Alergeno> alergenosUnicos = ingrediente.getAlergenos().stream()
                .map(alergeno -> saveAlergeno(alergeno))
                .distinct()
                .collect(Collectors.toList());

        ingrediente.setAlergenos(alergenosUnicos);

        return ingredienteRepository.save(ingrediente);
    }

    public Alergeno saveAlergeno(Alergeno alergeno) {

        Optional<Alergeno> alergenOptional = alergenoRepository.findByNombre(alergeno.getNombre());

        if (alergenOptional.isPresent()) {
            return alergenOptional.get();
        }

        return alergenoRepository.save(alergeno);
    }

    
    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

}