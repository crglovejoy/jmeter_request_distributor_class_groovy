package com.yahoo.crglovejoy.jmeter.distributor

class Distributor {
	private String[] distribArr
	private int distribArrSize
	private Random rndInst

	// ********************************************************************************
	// * Distribution config string is expected to be like this "0:25,1:25,2:25,3:25" *
	// * (label : number of instances in the distribution array).  Once the           *
	// * distribution list has been poplulated with all the labels, the list          *
	// * is scrambled and then assigned to our distribArr property via toArray().     *
	// *                                                                              *
	// * convertLabelToIntStr:                                                        *
	// * When using non-int strings as labels and want the label to be used as a      *
	// * switch value, pass true for this parameter to have the label mapped to an    *
	// * int string value in the distribution array.							  *
	// ********************************************************************************
	
	Distributor(String configStr, Boolean convertLabelToIntStr=false) {
		this.rndInst = new Random()
		List<String> distribList = []
		String[] distribParts = configStr.split(',')
		int idx = 0
		
		for (distrib in distribParts) {
			String[] parts = distrib.split(':')
			String label = ""

			// If using non-int strings for labels, can convert the label to an int from
			// the idx value here - when wanting to use the label as a switch value.
			// Otherwise, use the provided label directly.
			if (convertLabelToIntStr) {
				label = idx.toString()
				idx++
			} else {
				label = parts[0].trim()
			}
			
			int distVal = parts[1].trim().toInteger()
		
			for (int i = 0 ; i < distVal; i++) {
				distribList.add(label)
			}
		}

		Collections.shuffle(distribList)
		this.distribArr = distribList.toArray()
		this.distribArrSize = distribArr.size()
	}

	// ***********************  GetRandomDistLabel ***********************
	// * Get a label at random from the disribution array and return it. *
	// *******************************************************************
	
	String GetRandomDistLabel() {
		distribArr[rndInst.nextInt(this.distribArrSize)]
	}

	// *****************  GetDistributionArrayCopy ***********************
	// * Get a clone of the distribution array for unit test purposes    *
	// *******************************************************************

	String[] GetDistributionArrayCopy() {
		distribArr.clone()
	}

	// ****************************  Setters and Getters ********************************
	// * Setters and getters for the properties we want to remain private. 				*
	// * Throw an exception of a consumer attempts to access these properties directly. *
	//***********************************************************************************
	
	void setDistribArrSize(int sz) {
		throw new Exception("Cannot set distribArrSize")
	}
	int getDistribArrSize() {
		throw new Exception("Cannot get distribArrSize")
	}
	void setDistribArr(String[] arr) {
		throw new Exception("Cannot set distribArr")
	}
	String[] getDistribArr() {
		throw new Exception("Cannot get distribArr")
	}
	void setRndInst(Random rnd) {
		throw new Exception("Cannot set rndInst")
	}
	Random getRndInst() {
		throw new Exception("Cannot get rndInst")
	}
}