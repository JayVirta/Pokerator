package application;
	
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
	
	public static void main(String[] args) {
		Card card1 = new Card("heart", 3);
		Card card2 = new Card("heart", 3);
		Card card3 = new Card("heart", 3);
		Card card4 = new Card("heart", 5);
		Card card5 = new Card("heart", 5);
		Hand hand = new Hand(card1, card2, card3, card4, card5);
		System.out.println(hand.whatIsHand());
	}
}
