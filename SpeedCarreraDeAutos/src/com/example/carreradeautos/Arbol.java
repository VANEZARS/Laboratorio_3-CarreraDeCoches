package com.example.carreradeautos;

import javax.microedition.khronos.opengles.GL10;

public class Arbol {

	Circulo circulo;
	Rectangulo rectangulo;
	public Arbol(GL10 gl){
		circulo = new Circulo(1, 30, true);
		rectangulo = new Rectangulo();
	}
	public void dibuja(GL10 gl){
		gl.glColor4f(50/255f, 1, 50/255f, 0);
		circulo.dibuja(gl);
		gl.glTranslatef(0.5f, 0.5f, 0);
		circulo.dibuja(gl);
		gl.glTranslatef(0.5f, -0.5f, 0);
		circulo.dibuja(gl);
		gl.glTranslatef(-1f, -2, 0);
		gl.glColor4f(1, 100/255f, 100/255f, 0);
		rectangulo.dibuja(gl);
	}
}
