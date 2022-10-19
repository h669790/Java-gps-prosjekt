package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		ystep = MAPYSIZE / (Math.abs(maxlat - minlat));

		return ystep;

		
	}

	public void showRouteMap(int ybase) {

		setColor(0, 100, 0);
		double nextLongitude;
		double nextLatitude;
		int x = 0;
		int y = ybase;
		int r = 3;
		fillCircle(x, y, r);


		for (GPSPoint i : gpspoints) {
			nextLongitude = i.getLongitude() - GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
			nextLatitude = i.getLatitude() - GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));


			x = MARGIN + (int) (nextLongitude * xstep());
			y = ybase - (int) (nextLatitude * ystep());


			fillCircle(x, y, r);
		}

	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		drawString("Total Time     :" + GPSUtils.formatTime(gpscomputer.totalTime()), 5, TEXTDISTANCE);
		drawString("Total distance :" + GPSUtils.formatDouble(gpscomputer.totalDistance()), 5, TEXTDISTANCE * 2);
		drawString("Total elevation:" + GPSUtils.formatDouble(gpscomputer.totalElevation()), 5, TEXTDISTANCE * 3);
		drawString("Max speed      :" + GPSUtils.formatDouble(gpscomputer.maxSpeed()), 5, TEXTDISTANCE * 4);
		drawString("Average speed  :" + GPSUtils.formatDouble(gpscomputer.averageSpeed()), 5, TEXTDISTANCE * 5);
		drawString("Energy         :" + GPSUtils.formatDouble(gpscomputer.totalKcal(GPSComputer.WEIGHT)), 5,
				TEXTDISTANCE * 6);
		drawString("Total Time     :" + GPSUtils.formatTime(gpscomputer.totalTime()), 5, TEXTDISTANCE * 7);

	}

}
