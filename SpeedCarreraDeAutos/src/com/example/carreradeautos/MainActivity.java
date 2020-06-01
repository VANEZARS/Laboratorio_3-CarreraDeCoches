package com.example.carreradeautos;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
	
public class MainActivity extends Activity {
	//MainActivityTouch mt;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }
    /*
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Thread logo=new Thread(){
			public void run(){
				try {
					int tiempo=0;
					while(tiempo<2000){
						sleep(100);
						tiempo=tiempo+100;
					}
					
					//Intent intent = new Intent(this, MainActivityTouch.class);
					Intent intent=new Intent("com.example.carreradeautos.MainActivityTouch");
					//this.startActivity(i);
			    	
					//Intent intent = new Intent(this, MainActivityTouch.class);
			    	startActivity(intent);
					
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					finish();	
				}
			}

			private void startActivity(Intent intent) {
				// TODO Auto-generated method stub
				
			}

		};
		logo.start();

	}

*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

    
    public void jugar(View vista){
    	Intent intent = new Intent(this, MainActivitySpeed.class);
    	this.startActivity(intent);
    }
    
}
