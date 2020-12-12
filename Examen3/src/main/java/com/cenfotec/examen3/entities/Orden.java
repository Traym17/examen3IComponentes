package com.cenfotec.examen3.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orden implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.AUTO) long id;
	private long cantProducto;
	private long costo;
    private String tipoProducto;
    private String imagen;
    private long cliente;
    //@ManyToOne(fetch=FetchType.LAZY)
   // @JoinColumn(name="clienteid",nullable=false)    
   // private Cliente cliente;
}
