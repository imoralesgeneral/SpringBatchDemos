package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CochePostCSV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String marca;
	public String modelo;
	public float ratio;
	public boolean ratioMayorQueUno;
	
	public CochePostCSV() {
		
	}
			
	public CochePostCSV(CochePost coche, boolean ratioMayor) {
		super();
		this.id = coche.getId();
		this.marca = coche.getMarca();
		this.modelo = coche.getModelo();
		this.ratio = coche.getRatio();
		this.ratioMayorQueUno = ratioMayor;
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

	public boolean isRatioMayorQueUno() {
		return ratioMayorQueUno;
	}

	public void setRatioMayorQueUno(boolean ratioMayorQueUno) {
		this.ratioMayorQueUno = ratioMayorQueUno;
	}

	@Override
	public String toString() {
		return "CochePostCSV [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", ratio=" + ratio
				+ ", ratioMayorQueUno=" + ratioMayorQueUno + "]";
	}

}
