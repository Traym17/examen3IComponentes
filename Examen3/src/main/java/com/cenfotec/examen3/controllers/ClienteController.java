package com.cenfotec.examen3.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping({ "/cliente" })
public class ClienteController {
	private ClienteRepository repository;
	private OrdenRepository repositoryOrden;
	
	ClienteController(ClienteRepository clienteRepository,OrdenRepository ordenRepository) {
		this.repository = clienteRepository;
		this.repositoryOrden = ordenRepository;
	}

	@GetMapping(path = { "apellido/{id}" })
	public List findAll1(@PathVariable String id) {
		List<Cliente>lista=repository.findAll();
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getApellido().contains(id)) {
				
			}else {
				lista.remove(lista.get(i));
			}
		}
		return lista;
	}
	
	
	@GetMapping(path = { "direccion/{id}" })
	public List findAll2(@PathVariable String id) {
		List<Cliente>lista=repository.findAll();
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getDireccion().contains(id)) {
				
			}else {
				lista.remove(lista.get(i));
			}
		}
		return lista;
	}
	
	
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Cliente> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Cliente create(@RequestBody Cliente cliente) {
		return repository.save(cliente);
	}
	
	@PutMapping(path = { "/{id}" })
	public ResponseEntity<Cliente> update(@PathVariable("id") long id, @RequestBody Cliente contact) {
		return repository.findById(id).map(record -> {
			//record.setNumeroTarjeta(contact.getNumeroTarjeta());
			record.setMesTarjeta(contact.getMesTarjeta());
			record.setAnnoTarjeta(contact.getAnnoTarjeta());
			record.setNombre(contact.getNombre());
			record.setApellido(contact.getApellido());
			record.setDireccion(contact.getDireccion());
			Cliente updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return repository.findById(id).map(record -> {
			List<Orden>lista=this.repositoryOrden.findAll();
			int result=0;
			for(int i=0;i<lista.size();i++) {
				long idCliente=lista.get(i).getCliente();
				if(idCliente==id) {
					result++;
				}
			}
			if(result==0) {
				repository.deleteById(id);
				return ResponseEntity.ok().build();
			 
			}else {
				return ResponseEntity.notFound().build();
			}
			
		}).orElse(ResponseEntity.notFound().build());
	}
}
