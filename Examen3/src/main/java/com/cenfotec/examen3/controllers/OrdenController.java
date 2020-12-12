package com.cenfotec.examen3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.examen3.entities.Cliente;
import com.cenfotec.examen3.entities.Orden;
import com.cenfotec.examen3.repositories.ClienteRepository;
import com.cenfotec.examen3.repositories.OrdenRepository;

@RestController
@RequestMapping({ "/orden" })
public class OrdenController {
private OrdenRepository repository;
private ClienteRepository clienterepository;

		OrdenController(OrdenRepository ordenRepository,ClienteRepository clienteRepository) {
		this.repository = ordenRepository;
		this.clienterepository = clienteRepository;
	}

	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Orden> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@GetMapping(path = { "/item/{id}" })
	public List findAll1(@PathVariable String id) {
		List<Orden>lista=repository.findAll();
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getTipoProducto().equals(id)) {
			}else {
				lista.remove(lista.get(i));
			}
		}
		return lista;
	}
	
	
	@PutMapping(path = { "/cant/{id}" })
	public ResponseEntity<Orden> update(@PathVariable("id") long id, @RequestBody Orden orden2) {
		return repository.findById(id).map(record -> {
			//System.out.println(orden2.getCantProducto());
			//System.out.println(record.getCantProducto());
		record.setCantProducto(orden2.getCantProducto());
		record.setCosto(precio(record.getTipoProducto(),orden2.getCantProducto()));
			Orden updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(path = { "tipo/{id}" })
	public ResponseEntity<Orden> update2(@PathVariable("id") long id, @RequestBody Orden orden) {
		return repository.findById(id).map(record -> {
			record.setTipoProducto(orden.getTipoProducto());
			record.setCosto(precio(orden.getTipoProducto(),record.getCantProducto()));
			Orden updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Orden create(@RequestBody Orden orden) {
		orden.setCosto(precio(orden.getTipoProducto(),orden.getCantProducto()));
		orden.setCantProducto(orden.getCantProducto());
		return repository.save(orden);
	}
	
	/*@PostMapping(path = { "/{id}" })
	public Orden create(@PathVariable long id,@RequestBody Orden orden){
		Optional<Cliente> cliente=this.clienterepository.findById(id);
		orden.setCliente(cliente.get());
		orden.setCosto(precio(orden.getTipoProducto(),orden.getCantProducto()));
		return c.save(orden);
	}*/
	
	public long precio(String tipo,long cantidad) {
		long precio=0;
		switch(tipo)
		{
		case "vaso":
			precio=(int) (cantidad*13);
			break;
		case "tasa":
			precio=(int) (cantidad*15);
			break;
		case "camiseta":
			precio=(int) (cantidad*9);
			break;
		case "almohadon":
			precio=(int) (cantidad*8);
			break;
		}
		return precio;
	}
}
