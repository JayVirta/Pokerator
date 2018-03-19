package application;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CameraViewController 
{
	private ScheduledExecutorService timer;
	@FXML
	private Button startBtn;
	@FXML
	private Button cameraBtn;
	@FXML
	private Button backBtn;
	@FXML
	private ImageView currentFrame;
	private boolean cameraActive = false;
	private static int cameraId = 0;
	@FXML
	protected void startCamera(ActionEvent event)
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        if(!camera.isOpened()){
            System.out.println("Error");
        }
        else {
            Mat frame = new Mat();
            camera.read(frame); 
            while(true){
                if (camera.read(frame)){
                    System.out.println("Frame Obtained");
                    System.out.println("Captured Frame Width " + 
                    frame.width() + " Height " + frame.height());
                    Imgcodecs.imwrite("camera.jpg", frame);
                    System.out.println("OK");
                    break;
                }
            }   
        }
        camera.release();
    }
	}
