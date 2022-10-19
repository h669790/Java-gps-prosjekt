package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		gpspoints = new GPSPoint[n];
		antall = 0;
		
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;
		
		if(this.antall < this.gpspoints.length) {
			this.gpspoints[this.antall] = gpspoint;
			inserted = true;
			antall++;
			
		}else {
			inserted = false;
		}
				
			return inserted;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		
		
		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
		return insertGPS(gpspoint);

		
		
	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		int i = 0;
		
		while(i < this.gpspoints.length) {
			String time = Integer.toString(this.gpspoints[i].getTime());
			String latitude = Double.toString(this.gpspoints[i].getLatitude());
			String longitude = Double.toString(this.gpspoints[i].getLatitude());
			String elevation = Double.toString(this.gpspoints[i].getElevation());
			
			i++;
			
			System.out.println(time + " (" + latitude + longitude + ") " + elevation);
			
			
			
		}System.out.println("====== Konvertert GPS Data - SLUTT ======");

		
		
		

	}
}
