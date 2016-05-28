package com.dragonsong.recorder;
import com.cycling74.max.*;

public class DictionaryPacker extends MaxObject{
	int[] clocker = new int[2000]; 
	float[] jointData = new float[60]; 	
	String[] setDict = new String[120000]; 
	
	private static final String[] INLET_ASSIST = new String[]{ 
		"joint data"
	}; 
	
	private static final String[] OUTLET_ASSIST = new String[]{ 
		"joint data ready for dictionary"
	}; 
	
	public DictionaryPacker(Atom[] args){
		declareInlets(new int[]{DataTypes.ALL, DataTypes.ALL});
		declareOutlets(new int[]{DataTypes.ALL});
		
		setInletAssist(INLET_ASSIST);
		setOutletAssist(OUTLET_ASSIST);
	}
		
	public void bang(){ 
		for (int i = 0; i < clocker.length; i++){
			for (int j = 0; j < jointData.length; j++){
				String axis = ""; 
				if (j % 3 == 0){ 
					axis = "x"; 
				}
				else if(j % 3 == 1){
					axis = "y"; 
				}
				else if(j% 3 ==2){
					axis = "z";
				}
				setDict[i] = "get " + clocker[i] + ":" + jointData + ":" + axis + " " + jointData[j];
			}
		}
	}
		
	public void list(Atom[] list){
		int inletNo = getInlet(); 
		switch(inletNo){ 
			case 0:
				for (int i = 0; i < list.length; i++){
					clocker[i] = list[i].getInt(); 
				}
			case 1:
				for (int i = 0; i < list.length; i++){
					jointData[i] = list[i].getFloat(); 
				}
		}
	}
		

}
