package com.example.carreradeautos;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Clase Carretera (OpenGL 1.x)
 * 
 * @author Jhonny Felipez
 * @version 1.0 21/02/2016
 * 
 */
public class Carretera {

	/* Camino */
	private float verticesCamino[] = new float[] {
		 96,   0, // 0
		224,   0, // 1
		224, 480, // 2
		 96, 480, // 3
	};
	
	/* Línea */
	private float verticesLinea[] = new float[] {
		157,  0, // 0
		163,  0, // 1
		163, 35, // 2
		157, 35, // 3
	};


	FloatBuffer bufVerticesCamino;
	
	FloatBuffer bufVerticesLinea;

	public Carretera() {
		
		/* Camino */
		
		ByteBuffer bufByte = ByteBuffer.allocateDirect(verticesCamino.length * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVerticesCamino = bufByte.asFloatBuffer();
		bufVerticesCamino.put(verticesCamino).rewind();
		
		/* Línea */
		
		bufByte = ByteBuffer.allocateDirect(verticesLinea.length * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVerticesLinea = bufByte.asFloatBuffer();
		bufVerticesLinea.put(verticesLinea).rewind();
		
	}

	public void dibuja(GL10 gl, float desplazamiento) {
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		/* Dibuja el camino */
		
		gl.glColor4f(127f / 255, 127f / 255, 127f / 255, 1);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVerticesCamino);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		
		/* Dibuja las líneas */
		
		gl.glPushMatrix();
		gl.glTranslatef(0, desplazamiento, 0);
		
		gl.glColor4f(1, 1, 1, 1);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVerticesLinea);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
	
		gl.glPushMatrix();
		for (int i = 1; i <= 8; i++) {
			gl.glTranslatef(0, 60, 0);
			gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		}
		gl.glPopMatrix();
		gl.glPopMatrix();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
	}

}
