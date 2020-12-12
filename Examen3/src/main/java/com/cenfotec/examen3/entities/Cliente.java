package com.cenfotec.examen3.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cliente implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)long id;
    private long numeroTarjeta;
	private long mesTarjeta;
	private long annoTarjeta;
    private String nombre;
    private String apellido;
    private String direccion;
   // @OneToMany(fetch=FetchType.LAZY,  mappedBy="cliente")
   // private List<Orden> ordenes;

	
}
