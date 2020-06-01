package com.example.carreradeautos;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Clase Renderiza (OpenGL 1.x)
 * 
 * @author Jhonny Felipez
 * @version 1.0 21/02/2016
 * 
 */
public class Renderiza extends GLSurfaceView implements Renderer {
	
	/* Objetos */
	Carretera carretera;
	Coche coche;
	
	/* Desplazamientos */
	float despLineas;
	
	float despX_Coche1;
	float despY_Coche1;
	
	float despX_Coche2;
	float despY_Coche2;
	
	float despX_Coche3;
	float despY_Coche3;
	
	float despX_Arbol;
	float despY_Arbol;

	/* Variables del tiempo de ejecución */
	private long inicio, fin, duracion;
	private float tiempo_real;
	private double tiempoParpadeo = 0;
	private final float PERIODO_DEL_COCHE1 = 0.010f; // en segundos
	Arbol arbol;
	MainActivity actividad;
	
	public Renderiza(Context contexto) {
		super(contexto);
		actividad = new MainActivity();
		this.setRenderer(this);
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		arbol = new Arbol(gl);
		carretera = new Carretera();
		coche = new Coche();
		
		despLineas = 0;
		despX_Coche1 = 113;
		despY_Coche1 = 50;
		
		despX_Coche2 = 113;
		despY_Coche2 = 480;
		
		despX_Coche3 = 113+68;
		despY_Coche3 = 240;

		despY_Arbol = 100;

		inicio = System.currentTimeMillis();
		tiempoParpadeo = PERIODO_DEL_COCHE1;
		
		gl.glClearColor(0, 1, 1, 0);
		
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		gl.glLoadIdentity();
		
		/* Dibuja la carretera */
		
		carretera.dibuja(gl, despLineas);
		
		/* Dibuja el coche1 */
		
		gl.glTranslatef(despX_Coche1, despY_Coche1, 0);
		coche.setColor(1);
		coche.dibuja(gl);
		
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glTranslatef(despX_Coche2, despY_Coche2, 0);
		coche.setColor(2);
		coche.dibuja(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glTranslatef(despX_Coche3, despY_Coche3, 0);
		coche.setColor(3);
		coche.dibuja(gl);
		gl.glPopMatrix();
		for(float i = despY_Arbol; i < 510; i+=100){
			gl.glPushMatrix();
			gl.glLoadIdentity();
			gl.glTranslatef(40, i, 0);
			gl.glScalef(20, 20, 0);
			arbol.dibuja(gl);
			gl.glPopMatrix();
		}
		for(float i = despY_Arbol; i < 510; i+=100){
			gl.glPushMatrix();
			gl.glLoadIdentity();
			gl.glTranslatef(320-60, i, 0);
			gl.glScalef(20, 20, 0);
			arbol.dibuja(gl);
			gl.glPopMatrix();
		}
		
		/* Obtiene el tiempo real*/
		fin = System.currentTimeMillis();
		duracion = fin - inicio;
		tiempo_real = duracion / 1000f;
		inicio = fin;
		
		/* Incrementa y verifica el límite del tiempo */
		tiempoParpadeo = tiempoParpadeo - tiempo_real;
		if (tiempoParpadeo < 0.001) {
			tiempoParpadeo = PERIODO_DEL_COCHE1;
			despLineas = despLineas - 8;
			despY_Coche2 = despY_Coche2 - 6;
			despY_Coche3 = despY_Coche3 - 6;
			despY_Arbol = despY_Arbol - 8;
			
			if (despLineas < -60){
				despLineas = despLineas + 60;
			}
			if (despY_Coche2 < -10){
				despY_Coche2 = 480;
			}
			if (despY_Coche3 < -10){
				despY_Coche3 = 480;
			}
			if (despY_Arbol < -10){
				despY_Arbol = 85;
			}
			
		}
		if(despX_Coche1 == despX_Coche2 && (despY_Coche1 >= despY_Coche2 || despY_Coche1+50 >=despY_Coche2 )){
			reiniciar();
		}
		if(despX_Coche1 == despX_Coche3 && (despY_Coche1 >= despY_Coche3 || despY_Coche1+50 >=despY_Coche3 )){
			reiniciar();
		}
		
	}
	public void reiniciar(){
		carretera = new Carretera();
		coche = new Coche();
		
		despLineas = 0;
		despX_Coche1 = 113;
		despY_Coche1 = 50;
		
		despX_Coche2 = 113;
		despY_Coche2 = 480;
		
		despX_Coche3 = 113+68;
		despY_Coche3 = 240;

		inicio = System.currentTimeMillis();
		tiempoParpadeo = PERIODO_DEL_COCHE1;

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		
		gl.glViewport(0, 0, w, h);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluOrtho2D(gl, 0, 320, 0, 480);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		
		if (e.getAction() == MotionEvent.ACTION_UP) {
			
			/* Cambia de carril el coche1 */
			
			if (despX_Coche1 == 113)
				despX_Coche1 = 113 + 68;
			else
				despX_Coche1 = 113;
			
		}
		return true;
		
	}
}