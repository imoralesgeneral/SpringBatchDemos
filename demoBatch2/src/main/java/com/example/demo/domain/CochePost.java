package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CochePost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String marca;
	public String modelo;
	public float ratio;
	
	public CochePost() {
		
	}
			
	public CochePost(Coche coche, float ratio) {
		super();
		this.id = coche.getId();
		this.marca = coche.getMarca();
		this.modelo = coche.getModelo();
		this.ratio = ratio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

	@Override
	public String toString() {
		return "CochePost [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", ratio=" + ratio + "]";
	}

}
