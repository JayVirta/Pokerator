package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("ObjRecognition.fxml"));
			Scene scene = new Scene(root,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Card card1 = new Card("CLUBS", 9);
		Card card2 = new Card("SPADES", 1);
		Card card3 = new Card("DIAMONDS", 2);
		Card card4 = new Card("CLUBS", 13);
		Card card5 = new Card("DIAMONDS", 9);
		simpleHand hand = new simpleHand(card1, card2, card3, card4, card5);
		System.out.println(hand.whatIsHand());
		System.out.println(hand.getSimpleChance());
		launch(args);
	}
}
