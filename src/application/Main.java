package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Card card1 = new Card("hearts", 1);
		Card card2 = new Card("hearts", 10);
		Card card3 = new Card("hearts", 11);
		Card card4 = new Card("hearts", 12);
		Card card5 = new Card("hearts", 13);
		simpleHand hand = new simpleHand(card1, card2, card3, card4, card5);
		hand.handDetermine();
	}
}
