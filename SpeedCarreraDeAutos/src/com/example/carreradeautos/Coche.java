package com.example.carreradeautos;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Clase Coche (OpenGL 1.x)
 * 
 * @author Jhonny Felipez
 * @version 1.0 21/02/2016
 * 
 */
public class Coche {
	
	/* Ruedas */
	private float verticesRuedas[] = new float[] {
		0, 2,   // 0
		62, 2,  // 1
		62, 22, // 2
		0, 22,  // 3
		5, 74,  // 4
		54, 74, // 5
		54, 89, // 6
		5, 89,  // 7
	};

	/* ParaChoques */
	private float verticesParachoques[] = new float[] {
		10, 0,   // 0
		50, 0,   // 1
		50, 16,  // 2
		10, 16,  // 3
		6, 98,   // 4
		55, 98,  // 5
		55, 107, // 6
		6, 107,  // 7
	};

	/* Carroceria */
	private float verticesCarroceria[] = new float[] {
		16, 16, // 0
		44, 16, // 1
		52, 34, // 2
		39, 98, // 3
		22, 98, // 4
		8, 34,  // 5
	};

	/* Indices */
	private short indices[] = new short[] {
			0, 1, 2, 0, 2, 3,
			4, 5, 6, 4, 6, 7
	};

	FloatBuffer bufVerticesRuedas;
	FloatBuffer bufVerticesParachoques;
	FloatBuffer bufVerticesCarroceria;
	ShortBuffer bufIndices;
	private int color = 1;
	public Coche() {

		/* Ruedas */
		ByteBuffer bufByte = ByteBuffer
				.allocateDirect(verticesRuedas.length * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVerticesRuedas = bufByte.asFloatBuffer();
		bufVerticesRuedas = bufByte.asFloatBuffer();
		bufVerticesRuedas.put(verticesRuedas).rewind();

		/* Parachoques */
		bufByte = ByteBuffer.allocateDirect(verticesParachoques.length * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVerticesParachoques = bufByte.asFloatBuffer();
		bufVerticesParachoques.put(verticesParachoques);
		bufVerticesParachoques.rewind();

		/* Indices */
		bufByte = ByteBuffer.allocateDirect(indices.length * 2);
		bufByte.order(ByteOrder.nativeOrder());
		bufIndices = bufByte.asShortBuffer();
		bufIndices.put(indices);
		bufIndices.rewind();

		/* Carroceria */
		bufByte = ByteBuffer.allocateDirect(verticesCarroceria.length * 4);
		bufByte.order(ByteOrder.nativeOrder());
		bufVerticesCarroceria = bufByte.asFloatBuffer();
		bufVerticesCarroceria.put(verticesCarroceria);
		bufVerticesCarroceria.rewind();

	}
	public void setColor(int i){
		color = i;
	}
	public void dibuja(GL10 gl) {

		gl.glScalef(0.5f, 0.5f, 0);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		/* Dibuja las Ruedas */
		
		gl.glColor4f(0, 0, 0, 1);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVerticesRuedas);
		gl.glDrawElements(GL10.GL_TRIANGLES, 12, GL10.GL_UNSIGNED_SHORT,
				bufIndices);

		/* Dibuja el Parachoques */
		switch(color){
		case 1:gl.glColor4f(1, 0, 0, 1);break;
		case 2:gl.glColor4f(0, 1, 0, 1);break;
		case 3:gl.glColor4f(0, 0, 1, 1);break;
		}
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVerticesParachoques);
		gl.glDrawElements(GL10.GL_TRIANGLES, 12, GL10.GL_UNSIGNED_SHORT,
				bufIndices);

		/* Dibuja la Carroceria */
		
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVerticesCarroceria);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 6);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
