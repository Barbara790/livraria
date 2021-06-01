package com.generation.livraria.controller;

import java.util.List;


import org.apache.catalina.connector.Response;
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

import com.generation.livraria.model.Categoria;
import com.generation.livraria.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	public CategoriaRepository repositorio;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> GetAll(){
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Categoria> GetById(@PathVariable long id){
		return repositorio.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/genero/{genero}")
	public ResponseEntity<List<Categoria>> GetByGenero (@PathVariable String genero) {
		return ResponseEntity.ok(repositorio.findAllByGeneroContainingIgnoreCase(genero));
	}
	
	@PostMapping
	public ResponseEntity<Categoria>post(@RequestBody Categoria genero){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(genero));
	}
	
	@PutMapping
	public ResponseEntity<Categoria>put(@RequestBody Categoria genero){
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(genero));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repositorio.deleteById(id);
	}
	

}
