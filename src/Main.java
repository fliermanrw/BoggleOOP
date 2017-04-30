
import Model.Trie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    //Mooie toevoeging

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/BoggleBoard.fxml"));
        primaryStage.setTitle("Boggle Solver");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
        //Trie trie = new Trie();
        //trie.printwordlist();

        //System.out.println((trie.add("xx")));

        //System.out.println("now finding..");

        //System.out.println(trie.find("appel"));


        //System.out.println((trie.add("appel")));
        //System.out.println(trie.find("appe"));



    }
}
