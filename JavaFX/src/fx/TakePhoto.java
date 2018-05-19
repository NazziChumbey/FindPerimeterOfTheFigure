package fx;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class TakePhoto {
	public static void Take() {
		long startTime = System.nanoTime();
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture camera = new VideoCapture(-1);
		camera.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, 640);
		camera.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, 480);

		if (!camera.isOpened()) {
			System.out.println("Error");
		} else {
			Mat frame = new Mat();
			while (true) {
				if (camera.read(frame)) {
					Imgcodecs.imwrite("camera.jpg", frame);
					System.out.println("OK");
					break;
				}
			}
		}
		camera.release();
		
		long estimatedTime = System.nanoTime() - startTime;
		System.out.println("Take photo : " + estimatedTime);
	}
}
