package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The controller associated with the only view of our application. The
 * application logic is implemented here. It handles the button for
 * starting/stopping the camera, the acquired video stream, the relative
 * controls and the image segmentation process.
 * 
 * @author <a href="mailto:luigi.derussis@polito.it">Luigi De Russis</a>
 * @version 2.0 (2017-03-10)
 * @since 1.0 (2015-01-13)
 * 
 */
public class ObjRecognitionController
{
	// FXML camera button
	@FXML
	private Button cameraButton;
	// the FXML area for showing the current frame
	@FXML
	private ImageView originalFrame;
	// the FXML area for showing the mask
	@FXML
	private ImageView maskImage;
	// the FXML area for showing the output of the morphological operations
	@FXML
	private ImageView morphImage;
	// FXML slider for setting HSV ranges
	@FXML
	private Slider hueStart;
	@FXML
	private Slider hueStop;
	@FXML
	private Slider saturationStart;
	@FXML
	private Slider saturationStop;
	@FXML
	private Slider valueStart;
	@FXML
	private Slider valueStop;
	// FXML label to show the current values set with the sliders
	@FXML
	private Label hsvCurrentValues;
	@FXML
	private ImageView ogOutput;
	@FXML
	private ImageView card1View;
	@FXML
	private ImageView card2View;
	@FXML
	private ImageView card3View;
	@FXML
	private ImageView card4View;
	@FXML
	private ImageView card5View;
	@FXML
	private ComboBox<String> suit1;
	@FXML
	private ComboBox<String> suit2;
	@FXML
	private ComboBox<String> suit3;
	@FXML
	private ComboBox<String> suit4;
	@FXML
	private ComboBox<String> suit5;
	@FXML
	private ComboBox<String> value1;
	@FXML
	private ComboBox<String> value2;
	@FXML
	private ComboBox<String> value3;
	@FXML
	private ComboBox<String> value4;
	@FXML
	private ComboBox<String> value5;
	@FXML
	private Button submitBtn;
	@FXML
	private Text handName;
	String suitOne;
	String suitTwo;
	String suitThree;
	String suitFour;
	String suitFive;
	String valueOne;
	String valueTwo;
	String valueThree;
	String valueFour;
	String valueFive;
	private List<Point> firstContour = new ArrayList<Point>();
	// a timer for acquiring the video stream
	private ScheduledExecutorService timer;
	// the OpenCV object that performs the video capture
	private VideoCapture capture = new VideoCapture();
	// a flag to change the button behavior
	private boolean cameraActive;
	private int coords[][] = new int[5][2];
	private int height[] = new int[5];
	private int width[] = new int[5];
	private Mat originalImage;
	private Image card1;
	private Image card2;
	private Image card3;
	private Image card4;
	private Image card5;
	private Mat card1Mat;
	private Mat card2Mat;
	private Mat card3Mat;
	private Mat card4Mat;
	private Mat card5Mat;
	// property for object binding
	private ObjectProperty<String> hsvValuesProp;
		
	/**
	 * The action triggered by pushing the button on the GUI
	 * @throws IOException 
	 */
	@FXML
	private void startCamera() throws IOException
	{
		// bind a text property with the string containing the current range of
		// HSV values for object detection
		hsvValuesProp = new SimpleObjectProperty<>();
		this.hsvCurrentValues.textProperty().bind(hsvValuesProp);
				
		// set a fixed width for all the image to show and preserve image ratio
		this.imageViewProperties(this.originalFrame, 1080);
		this.imageViewProperties(this.maskImage, 540);
		this.imageViewProperties(this.morphImage, 540);
		this.imageViewProperties(this.ogOutput, 540);
		this.imageViewProperties(this.card1View, 270);
		this.imageViewProperties(this.card2View, 270);
		this.imageViewProperties(this.card3View, 270);
		this.imageViewProperties(this.card4View, 270);
		this.imageViewProperties(this.card5View, 270);
		
		if (!this.cameraActive)
		{
			// start the video capture
			this.capture.open(0);
			
			// is the video stream available?
			if (this.capture.isOpened())
			{
				this.cameraActive = true;
				
				// grab a frame every 33 ms (30 frames/sec)
				Runnable frameGrabber = new Runnable() {
					
					@Override
					public void run()
					{
						// effectively grab and process a single frame
						Mat frame = grabFrame();
						// convert and show the frame
						Image imageToShow = Utils.mat2Image(frame);
						updateImageView(originalFrame, imageToShow);
					}
				};
				
				this.timer = Executors.newSingleThreadScheduledExecutor();
				this.timer.scheduleAtFixedRate(frameGrabber, 0, 100, TimeUnit.MILLISECONDS);
				
				// update the button content
				this.cameraButton.setText("Stop Camera");
			}
			else
			{
				// log the error
				System.err.println("Failed to open the camera connection...");
			}
		}
		else
		{
			capture.read(originalImage);
			// the camera is not active at this point
			this.cameraActive = false;
			// update again the button content
			this.cameraButton.setText("Start Camera");
			
			// stop the timer
			this.stopAcquisition();
		}
	}
	
	/**
	 * Get a frame from the opened video stream (if any)
	 * 
	 * @return the {@link Image} to show
	 */
	private Mat grabFrame()
	{
		Mat frame = new Mat();
		Mat frame2 = new Mat();
		Mat subFrame1 = new Mat();
		// check if the capture is open
		if (this.capture.isOpened())
		{
			try
			{
				// read the current frame
				this.capture.read(frame);
				originalImage = frame;
				// if the frame is not empty, process it
				if (!frame.empty())
				{
					// init
					Mat blurredImage = new Mat();
					Mat hsvImage = new Mat();
					Mat mask = new Mat();
					Mat morphOutput = new Mat();
					this.updateImageView(ogOutput, Utils.mat2Image(originalImage));
					// remove some noise
					Imgproc.blur(frame, blurredImage, new Size(7, 7));
					// convert the frame to HSV
					Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
					
					// get thresholding values from the UI
					// remember: H ranges 0-180, S and V range 0-255
					Scalar minValues = new Scalar(this.hueStart.getValue(), this.saturationStart.getValue(),
							this.valueStart.getValue());
					Scalar maxValues = new Scalar(this.hueStop.getValue(), this.saturationStop.getValue(),
							this.valueStop.getValue());
					
					// show the current selected HSV range
					String valuesToPrint = "Hue range: " + minValues.val[0] + "-" + maxValues.val[0]
							+ "\tSaturation range: " + minValues.val[1] + "-" + maxValues.val[1] + "\tValue range: "
							+ minValues.val[2] + "-" + maxValues.val[2];
					Utils.onFXThread(this.hsvValuesProp, valuesToPrint);
					
					// threshold HSV image to select tennis balls
					Core.inRange(hsvImage, minValues, maxValues, mask);
					// show the partial output
					this.updateImageView(this.maskImage, Utils.mat2Image(mask));
					// morphological operators
					// dilate with large element, erode with small ones
					Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
					Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12, 12));
					
					Imgproc.erode(mask, morphOutput, erodeElement);
					Imgproc.erode(morphOutput, morphOutput, erodeElement);
					Imgproc.dilate(morphOutput, morphOutput, dilateElement);
					Imgproc.dilate(morphOutput, morphOutput, dilateElement);
					// show the partial output
					this.updateImageView(this.morphImage, Utils.mat2Image(morphOutput));
					// find the tennis ball(s) contours and show them
					frame = this.findAndFillCards(mask, frame);
					frame2 = this.findAndDrawCards(morphOutput, frame2);				
				}
				
			}
			catch (Exception e)
			{
				// log the (full) error
				System.err.print("Exception during the image elaboration...");
				e.printStackTrace();
			}
		}
		
		return frame;
	}
	
	/**
	 * Given a binary image containing one or more closed surfaces, use it as a
	 * mask to find and highlight the objects contours
	 * 
	 * @param maskedImage
	 *            the binary image to be used as a mask
	 * @param frame
	 *            the original {@link Mat} image to be used for drawing the
	 *            objects contours
	 * @return the {@link Mat} image with the objects contours framed
	 */
	private Mat findAndFillCards(Mat maskedImage, Mat frame)
	{
		// init
		List<MatOfPoint> contours = new ArrayList<>();
		Mat hierarchy = new Mat();
		
		// find contours
		Imgproc.findContours(maskedImage, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
		
		// if any contour exist...
		if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
		{
			// for each contour, display it in blue
			for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
			{
				Imgproc.drawContours(frame, contours, idx, new Scalar(250, 0, 0), -1);
			}
		}
		return frame;
	}
	private Mat findAndDrawCards(Mat maskedImage, Mat frame)
	{
		// init
		List<MatOfPoint> contours = new ArrayList<>();
		Mat hierarchy = new Mat();
		// find contours
		Imgproc.findContours(maskedImage, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
		MatOfPoint2f approxCurve = new MatOfPoint2f();
		// if any contour exist...
		if (hierarchy.size().height > 0 && hierarchy.size().width > 0)
		{
			// for each contour, display it in blue
			for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0])
			{
				Rect rect = Imgproc.boundingRect(contours.get(idx));
				Imgproc.drawContours(frame, contours, idx, new Scalar(0, 250, 0), 1);
				System.out.println(contours.size());
				Imgproc.rectangle(contours.get(idx), rect.tl(), rect.br(), new Scalar(0, 0, 250), 1);
				if(contours.size()==5)
				{
					Rect rect1 = Imgproc.boundingRect(contours.get(0));
					Rect rect2 = Imgproc.boundingRect(contours.get(1));
					Rect rect3 = Imgproc.boundingRect(contours.get(2));
					Rect rect4 = Imgproc.boundingRect(contours.get(3));
					Rect rect5 = Imgproc.boundingRect(contours.get(4));
					coords[0][0] = rect1.x;
					coords[1][0] = rect2.x;
					coords[2][0] = rect3.x;
					coords[3][0] = rect4.x;
					coords[4][0] = rect5.x;
					coords[0][1] = rect1.y;
					coords[1][1] = rect2.y;
					coords[2][1] = rect3.y;
					coords[3][1] = rect4.y;
					coords[4][1] = rect5.y;
					height[0] = rect1.height;
					height[1] = rect2.height;
					height[2] = rect3.height;
					height[3] = rect4.height;
					height[4] = rect5.height;
					width[0] = rect1.width;
					width[1] = rect2.width;
					width[2] = rect3.width;
					width[3] = rect4.width;
					width[4] = rect5.width;
				}
			}
		}
		return frame;
	}
	/**
	 * Set typical {@link ImageView} properties: a fixed width and the
	 * information to preserve the original image ration
	 * 
	 * @param image
	 *            the {@link ImageView} to use
	 * @param dimension
	 *            the width of the image to set
	 */
	private void imageViewProperties(ImageView image, int dimension)
	{
		// set a fixed width for the given ImageView
		image.setFitWidth(dimension);
		// preserve the image ratio
		image.setPreserveRatio(true);
	}
	
	/**
	 * Stop the acquisition from the camera and release all the resources
	 * @throws IOException 
	 */
	private void stopAcquisition() throws IOException
	{
		if (this.timer!=null && !this.timer.isShutdown())
		{
			try
			{
				// stop the timer
				this.timer.shutdown();
				this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
			}
			catch (InterruptedException e)
			{
				// log any exception
				System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
			}
		}
		
		if (this.capture.isOpened())
		{
			card1Mat = originalImage.submat(coords[0][1], coords[0][1]+height[0], coords[0][0], coords[0][0]+width[0]);
			card2Mat = originalImage.submat(coords[1][1], coords[1][1]+height[1], coords[1][0], coords[1][0]+width[1]);
			card3Mat = originalImage.submat(coords[2][1], coords[2][1]+height[2], coords[2][0], coords[2][0]+width[2]);
			card4Mat = originalImage.submat(coords[3][1], coords[3][1]+height[3], coords[3][0], coords[3][0]+width[3]);
			card5Mat = originalImage.submat(coords[4][1], coords[4][1]+height[4], coords[4][0], coords[4][0]+width[4]);
			this.updateImageView(this.card1View, Utils.mat2Image(card1Mat));
			this.updateImageView(this.card2View, Utils.mat2Image(card2Mat));
			this.updateImageView(this.card3View, Utils.mat2Image(card3Mat));
			this.updateImageView(this.card4View, Utils.mat2Image(card4Mat));
			this.updateImageView(this.card5View, Utils.mat2Image(card5Mat));
			this.capture.release();
			suit1.getItems().addAll("DIAMONDS", "HEARTS", "CLUBS", "SPADES");
			suit2.getItems().addAll("DIAMONDS", "HEARTS", "CLUBS", "SPADES");
			suit3.getItems().addAll("DIAMONDS", "HEARTS", "CLUBS", "SPADES");
			suit4.getItems().addAll("DIAMONDS", "HEARTS", "CLUBS", "SPADES");
			suit5.getItems().addAll("DIAMONDS", "HEARTS", "CLUBS", "SPADES");
			value1.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13");
			value2.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13");
			value3.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13");
			value4.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13");
			value5.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13");
		}
	}
	@FXML
	private void submitHand()
	{
		valueOne = value1.getValue();
		valueTwo = value2.getValue();
		valueThree = value3.getValue();
		valueFour = value4.getValue();
		valueFive = value5.getValue();
		suitOne = suit1.getValue();
		suitTwo = suit2.getValue();
		suitThree = suit3.getValue();
		suitFour = suit4.getValue();
		suitFive = suit5.getValue();
		Card card1 = new Card(suitOne, Integer.parseInt(valueOne));
		Card card2 = new Card(suitTwo, Integer.parseInt(valueTwo));
		Card card3 = new Card(suitThree, Integer.parseInt(valueThree));
		Card card4 = new Card(suitFour, Integer.parseInt(valueFour));
		Card card5 = new Card(suitFive, Integer.parseInt(valueFive));
		simpleHand hand = new simpleHand(card1, card2, card3, card4, card5);
		System.out.println(hand.whatIsHand());
		System.out.println(hand.getSimpleChance());
	}
	/**
	 * Update the {@link ImageView} in the JavaFX main thread
	 * 
	 * @param view
	 *            the {@link ImageView} to update
	 * @param image
	 *            the {@link Image} to show
	 */
	private void updateImageView(ImageView view, Image image)
	{
		Utils.onFXThread(view.imageProperty(), image);
	}
	
	/**
	 * On application close, stop the acquisition from the camera
	 */
	protected void setClosed() throws IOException
	{
		this.stopAcquisition();
	}
	
}
