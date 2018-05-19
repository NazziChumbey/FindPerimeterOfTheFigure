package fx;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Calculation {
	public static String CalcData() {
		long startTime = System.nanoTime();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat image = Imgcodecs.imread("camera.jpg", CvType.CV_8UC1);
		Mat canny = new Mat(image.rows(), image.cols(), CvType.CV_8UC1);
		System.out.println();
		if (image.empty() == true) {
			System.out.println("Error: no image found!");
		} else {
			System.out.println("Image found!");
		}

		System.out.println("+" /* + cannyValue */);
		Imgproc.Canny(image, canny, 70, 250 /* imageValue, cannyValue */);
		Imgcodecs.imwrite("LogoCanny.jpg", canny);

		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(canny, contours, hierarchy, Imgproc.RETR_CCOMP,
				Imgproc.CHAIN_APPROX_SIMPLE);
		List<MatOfPoint2f> matrix = new ArrayList<MatOfPoint2f>();
		MatOfPoint2f myPt = new MatOfPoint2f();

		double largest_area = 0;
		double[] array = new double[3];
		int index = 0;
		// -----------------------------------------------------------------------------
		int countNonZeroElementsPx = Core.countNonZero(canny);
		System.out
				.println("countNonZeroElementsPx = " + countNonZeroElementsPx);
		// -----------------------------------------------------------------------------
		for (int i = 0; i < contours.size(); i++) {
			double a = Imgproc.contourArea(contours.get(i), false);
			if (a > largest_area) {
				largest_area = a;
				contours.get(i).convertTo(myPt, CvType.CV_32FC2);
				matrix.add(myPt);
				array[index] = Imgproc.arcLength(myPt, true);
				index++;
			}
		}
		double minContour, maxContour;
		if (array[2] != 0) {
			minContour = (array[1] + array[0]) / 2;
			maxContour = array[2];
		} else {
			minContour = array[0];
			maxContour = array[1];
		}
		double lenghtMaxContour = 62.2;// 101.2;
		double lenghtMinContour = 25.8;// 34.7;
		double findContour = (double) Math
				.round((minContour * lenghtMaxContour) / maxContour * 1000d) / 1000d;
		System.out.println("Length of the found contour : " + findContour);
		double rizn = (double) Math
				.round((lenghtMinContour - findContour) * 10000d) / 10000d;
		double vitsotoc = (double) Math
				.round(((rizn * 100) / lenghtMinContour) * 10000d) / 10000d;
		System.out.println("Difference : " + rizn + "\nPercentage mistakes : "
				+ vitsotoc + " %");

		for (double i : array) {
			System.out.println("" + i);
		}

		long estimatedTime = System.nanoTime() - startTime;
		System.out.println("Calc : " + estimatedTime);
		return "Perimeter contour of the figure : " + findContour
				+ " cm\nDifference : " + rizn + " cm\nPercentage mistakes : "
				+ vitsotoc + " %";
	}
}
