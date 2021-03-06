/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsfx;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author jelis
 */
public class MathsFX extends Application {

    private Player player;
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    double lineX = WIDTH / 2 - 100;
    double lineY = HEIGHT / 3 + 50;
    private Pane root = new Pane();
    private Pane rootLearn = new Pane();
    private Pane rootAdd = new Pane();
    private VBox menuBox = new VBox(-5);
    private Line line;
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    Scene sceneLearn = new Scene(rootLearn, WIDTH, HEIGHT);
    Scene sceneAdd = new Scene(rootAdd, WIDTH - 135, HEIGHT);

    //Buttons of MainMenu
    private Button btnLearn = new CreateButton("      Εκμάθηση         ", lineX + 10, lineY, 0);
    private Button btnAdd = new CreateButton("       Πρόσθεση        ", lineX + 10, lineY + 10, 0);
    private Button btnMinus = new CreateButton("       Αφαίρεση         ", lineX + 10, lineY + 20, 0);
    private Button btnCompare = new CreateButton("        Σύγκριση         ", lineX + 10, lineY + 30, 0);
    private Button btnMultiply = new CreateButton(" Πολλαπλασιασμός ", lineX + 10, lineY + 40, 0);
    private Button btnDivide = new CreateButton("        Διαίρεση         ", lineX + 10, lineY + 50, 0);
    private Button btnExit = new CreateButton("         Εξόδος          ", lineX + 10, lineY + 60, 0);
    private Button btnReturnMine = new CreateReturnButton("   <-  Επιστροφή     ", 0, 0);
    private Button btnReturnOnAdd = new CreateReturnButton("   <-  Επιστροφή      ", 0, 0);
    private Button btnReturn = new CreateReturnButton("   <-  Επιστροφή      ", 0, 0);
    private Button btnReturn2 = new CreateReturnButton("   <-  Επιστροφή      ", 0, 0);
    private Button btnReturn3 = new CreateReturnButton("   <-  Επιστροφή      ", 0, 0);
    private Button btnReturn4 = new CreateReturnButton("   <-  Επιστροφή      ", 0, 0);
    private Button btnReturn5 = new CreateReturnButton("   <-  Επιστροφή      ", 0, 0);
    private Button btnReturn6 = new CreateReturnButton("   <-  Επιστροφή      ", 0, 0);
    private Button btnNext = new CreateReturnButton("         Επόμενη ->        ", 975, 670);
    //Buttons for VideoPlayer
    private Button btnStart = new CreateButton("         Πρόσθεση          ", 210, 650, 0);
    private Button btnStart2 = new CreateButton("         Αφαίρεση          ", 370, 650, 0);
    private Button btnStart3 = new CreateButton("         Σύγκριση          ", 525, 650, 0);
    private Button btnStart4 = new CreateButton("         Πολλαπλασιασμός         ", 680, 650, 0);
    private Button btnStart5 = new CreateButton("         Διαίρεση          ", 880, 650, 0);
    //Buttons for Answers
    private Button btnAdd1 = new CreateButtonAnswers("30", 390, 450);
    private Button btnAddCorrentAnswerOnFirst = new CreateButtonAnswers("27", 535, 450);
    private Button btnAdd3 = new CreateButtonAnswers("18", 670, 450);

    //Texts For Numbers
    private Text text = new CreateTextForNumbers(390, 370, "15", Color.RED, 70);
    private Text text2 = new CreateTextForNumbers(540, 370, "+", Color.RED, 70);
    private Text text3 = new CreateTextForNumbers(650, 370, "12", Color.RED, 70);
    private Text text4 = new CreateTextForNumbers(750, 370, "=", Color.RED, 70);

    //Αλεξ
    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();
    Button help = new Button();
//    Button btnReturn = new Button("         Επιστροφή         ");
    Button btnContinue = new Button("         Συνέχεια          ");
    Button btnContinue2 = new Button("         Συνέχεια          ");

    ImageView Askisi1 = new ImageView();
    Image Number61Image = new Image(getClass().getResourceAsStream("/Number_61.jpg"), 60, 60, false, false);
    Image Askisi_Image = new Image(getClass().getResourceAsStream("/Askisi_1.png"));
    Image Number77Image = new Image(getClass().getResourceAsStream("/Number_77.jpg"), 60, 60, false, false);
    Image Number55Image = new Image(getClass().getResourceAsStream("/Number_55.jpg"), 60, 60, false, false);
    Image HelpImage = new Image(getClass().getResourceAsStream("/info2.jpg"), 20, 20, false, false);
    Image ScaleImage = new Image(getClass().getResourceAsStream("/Scale.jpg"));
    ImageView Scale = new ImageView();
    Image Help3Image1 = new Image(getClass().getResourceAsStream("/Dekada_Ask3.png"));
    Image Help3Image2 = new Image(getClass().getResourceAsStream("/Monada_Ask3.png"));
    Image ScaleSolutionImage = new Image(getClass().getResourceAsStream("/Askisi_1_Solution.jpg"));
    ImageView ScaleSolution = new ImageView();
    ImageView Help3_1 = new ImageView();
    ImageView Help3_2 = new ImageView();

    Image Number5Image = new Image(getClass().getResourceAsStream("/Number_5.jpg"), 60, 60, false, false);
    Image Number7Image = new Image(getClass().getResourceAsStream("/Number_7.jpg"), 60, 60, false, false);
    Image Number9Image = new Image(getClass().getResourceAsStream("/Number_9.jpg"), 60, 60, false, false);
    Image Askisi2_Image = new Image(getClass().getResourceAsStream("/Askisi_2.png"));
    ImageView Askisi2 = new ImageView();
    Image Askisi2_Solution_Image = new Image(getClass().getResourceAsStream("/Askisi_2_Solution.jpg"));
    ImageView Askisi2_Solution = new ImageView();

    Button button4 = new Button();
    Button button5 = new Button();
    Button button6 = new Button();
    Button help2 = new Button();

    Image Number70Image = new Image(getClass().getResourceAsStream("/Number_70(cube).png"), 146, 133, false, false);
    Image Number65Image = new Image(getClass().getResourceAsStream("/Number_65(cube).png"), 146, 133, false, false);
    Image Number49Image = new Image(getClass().getResourceAsStream("/Number_49(cube).png"), 146, 133, false, false);
    Image Askisi3_Image = new Image(getClass().getResourceAsStream("/Askisi_3.png"));
    ImageView Askisi3 = new ImageView();

    Button button7 = new Button();
    Button button8 = new Button();
    Button button9 = new Button();
    Button help3 = new Button();

    //SceneAle scene2, scene3, scene4, scene5;
    Stage Window;

    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 300);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);
    }

    public static void startAnimationButton(Button b) {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1.5), b);
        st.setToY(1);
        st.play();
    }

    private void startAnimationLine() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1.5), line);
        st.setToY(1);
        st.play();
    }

    public void startAdd() {
        Stage videoStage = new Stage();
        MenuBar menu = new MenuBar();

        player = new Player(getClass().getResource("add.mp4").toExternalForm());
        player.setTop(menu);

        // Adding player to the Scene 
        Scene Vid2 = new Scene(player, 1270, 720, Color.BLACK);

        videoStage.setScene(Vid2); // Setting the scene to stage 
        videoStage.show(); // Showing the stage 

        //stop video when Close window
        videoStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                player.player.stop();
            }
        });
    }

    public void startMinus() {
        Stage videoStage = new Stage();
        MenuBar menu = new MenuBar();

        player = new Player(getClass().getResource("minus.mp4").toExternalForm());
        player.setTop(menu);

        // Adding player to the Scene 
        Scene Vid2 = new Scene(player, 1270, 720, Color.BLACK);

        videoStage.setScene(Vid2); // Setting the scene to stage 
        videoStage.show(); // Showing the stage 

        //stop video when Close window
        videoStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                player.player.stop();
            }
        });
    }

    public void startCompare() {
        Stage videoStage = new Stage();
        MenuBar menu = new MenuBar();

        player = new Player(getClass().getResource("compare.mp4").toExternalForm());
        player.setTop(menu);

        // Adding player to the Scene 
        Scene Vid2 = new Scene(player, 1270, 720, Color.BLACK);

        videoStage.setScene(Vid2); // Setting the scene to stage 
        videoStage.show(); // Showing the stage 

        //stop video when Close window
        videoStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                player.player.stop();
            }
        });
    }

    public void startMulti() {
        Stage videoStage = new Stage();
        MenuBar menu = new MenuBar();

        player = new Player(getClass().getResource("multi.mp4").toExternalForm());
        player.setTop(menu);

        // Adding player to the Scene 
        Scene Vid2 = new Scene(player, 950, 720, Color.BLACK);

        videoStage.setScene(Vid2); // Setting the scene to stage 
        videoStage.show(); // Showing the stage 

        //stop video when Close window
        videoStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                player.player.stop();
            }
        });
    }

    public void startDivide() {
        Stage videoStage = new Stage();
        MenuBar menu = new MenuBar();

        player = new Player(getClass().getResource("divide.mp4").toExternalForm());
        player.setTop(menu);

        // Adding player to the Scene 
        Scene Vid2 = new Scene(player, 1270, 720, Color.BLACK);

        videoStage.setScene(Vid2); // Setting the scene to stage 
        videoStage.show(); // Showing the stage 

        //stop video when Close window
        videoStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                player.player.stop();
            }
        });
    }

    private Text t = new Text("Μπράβο μικρέ");

    @Override
    public void start(Stage primaryStage) {
        //Background Images
        ImageView imageView = new ImageView(new Image(getClass().getResource("bg_photo2.jpg").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        ImageView imageView2 = new ImageView(new Image(getClass().getResource("bg_photo2.jpg").toExternalForm()));
        imageView2.setFitWidth(WIDTH);
        imageView2.setFitHeight(HEIGHT);

        ImageView imageView3 = new ImageView(new Image(getClass().getResource("board.jpg").toExternalForm()));
        imageView2.setFitWidth(WIDTH);
        imageView2.setFitHeight(HEIGHT);

        //Titles
        Title title = new Title("Μαθηματικά");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);

        Title titleAdd = new Title("Κάνε την πράξη");
        titleAdd.setTranslateX(WIDTH / 2.5 - title.getTitleWidth() / 2);
        titleAdd.setTranslateY(HEIGHT / 4);

        Title titleLearn = new Title("Δίαλεξε την κατηγορία \n"
                + "που επιθυμείς να μάθεις");

        titleLearn.setTranslateX(WIDTH / 4 - title.getTitleWidth() / 2.5);
        titleLearn.setTranslateY(HEIGHT / 3);

        t.setFill(Color.RED);
        t.setFont(new Font(50));
        t.setTranslateX(425);
        t.setTranslateY(280);
        //MainLine + animation
        addLine(lineX, lineY);
        startAnimationLine();
        menuBox.getChildren().addAll(btnLearn, btnAdd, btnMinus, btnCompare, btnMultiply, btnDivide, btnExit);
        menuBox.fillWidthProperty();
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setFillWidth(true);

        //Button Actions
        btnExit.setOnAction(e -> Platform.exit());
        btnStart.setOnAction(e -> startAdd());
        btnStart2.setOnAction(e -> startMinus());
        btnStart3.setOnAction(e -> startCompare());
        btnStart4.setOnAction(e -> startMulti());
        btnStart5.setOnAction(e -> startDivide());

        //Αλεξ
        Askisi1 = new ImageView(Askisi_Image);
        Askisi2 = new ImageView(Askisi2_Image);
        Askisi3 = new ImageView(Askisi3_Image);
        Window = primaryStage;
        Window.setTitle("Άσκησεις");

        //Images for Help3
        Help3_1 = new ImageView(Help3Image1);
        Help3_2 = new ImageView(Help3Image2);

        //Scale Help Image for 3rd Class
        Scale = new ImageView(ScaleImage);

        //Scale Solution
        ScaleSolution = new ImageView(ScaleSolutionImage);

        //Askisi2 Solution
        Askisi2_Solution = new ImageView(Askisi2_Solution_Image);

        //Number 77 button (Correct Button)
        button2 = new Button();
        button2.setGraphic(new ImageView(Number77Image));
        button2.setTranslateX(10);
        button2.setTranslateY(20);

        //Number 61 button
        button1 = new Button();
        button1.setGraphic(new ImageView(Number61Image));
        button1.setTranslateX(230);
        button1.setTranslateY(20);

        //Number 55 button
        button3 = new Button();
        button3.setGraphic(new ImageView(Number55Image));
        button3.setTranslateX(-230);
        button3.setTranslateY(20);

        //Number 5 button (2nd Exercise)
        button4 = new Button();
        button4.setGraphic(new ImageView(Number5Image));
        button4.setTranslateX(230);
        button4.setTranslateY(20);

        //Number 9 button (2nd Exercise)
        button5 = new Button();
        button5.setGraphic(new ImageView(Number9Image));
        button5.setTranslateX(10);
        button5.setTranslateY(20);

        //Number 7 button (2nd Exercise)
        button6 = new Button();
        button6.setGraphic(new ImageView(Number7Image));
        button6.setTranslateX(-230);
        button6.setTranslateY(20);

        //Help Button
        help = new Button();
        help.setGraphic(new ImageView(HelpImage));
        help.setTranslateX(170);
        help.setTranslateY(0);

        //Help Button (2nd Exercise)
        help2 = new Button();
        help2.setGraphic(new ImageView(HelpImage));
        help2.setTranslateX(170);
        help2.setTranslateY(0);

        //Help Button (3rd Exercise)
        help3 = new Button();
        help3.setGraphic(new ImageView(HelpImage));
        help3.setTranslateX(170);
        help3.setTranslateY(0);

        //Number 65 Button (3rd Exercise)
        button7 = new Button();
        button7.setGraphic(new ImageView(Number65Image));
        button7.setMinHeight(133);
        button7.setMinWidth(146);
        button7.setTranslateX(230);
        button7.setTranslateY(30);

        //Number 49 Button (3rd Exercise)
        button8 = new Button();
        button8.setMinHeight(133);
        button8.setMinWidth(146);
        button8.setGraphic(new ImageView(Number49Image));
        button8.setTranslateX(10);
        button8.setTranslateY(30);

        //Number 70 Button (3rd Exercise)
        button9 = new Button();
        button9.setGraphic(new ImageView(Number70Image));
        button9.setMinHeight(133);
        button9.setMinWidth(146);
        button9.setTranslateX(-230);
        button9.setTranslateY(30);

        StackPane layout = new StackPane();
        StackPane layout2 = new StackPane();
        StackPane layout3 = new StackPane();
        StackPane layout4 = new StackPane();
        StackPane layout5 = new StackPane();

        //CSS for Eπιστροφη button
        //btnReturn.getStylesheets().add(getClass().getResource("btnBack.css").toExternalForm());
        btnReturn2.getStylesheets().add(getClass().getResource("btnBack.css").toExternalForm());
        btnReturn3.getStylesheets().add(getClass().getResource("btnBack.css").toExternalForm());
        btnReturn4.getStylesheets().add(getClass().getResource("btnBack.css").toExternalForm());
        btnReturn5.getStylesheets().add(getClass().getResource("btnBack.css").toExternalForm());
        //CSS for Συνέχεια button
        btnContinue.getStylesheets().add(getClass().getResource("btnAnswers.css").toExternalForm());
        btnContinue2.getStylesheets().add(getClass().getResource("btnAnswers.css").toExternalForm());

        layout.getChildren().addAll(Askisi1, button1, button2, button3, btnReturn, help);
        layout2.getChildren().addAll(ScaleSolution, btnReturn2, btnContinue);
        layout3.getChildren().addAll(Askisi2, button4, button5, button6, btnReturn3, help2);
        layout4.getChildren().addAll(Askisi2_Solution, btnReturn4, btnContinue2);
        layout5.getChildren().addAll(Askisi3, button7, button8, button9, help3, btnReturn5);

        Scene sceneAddFirst = new Scene(layout, 1164, 365);
        Scene sceneFirstCorrect = new Scene(layout2, 1164, 365);
        Scene sceneAddSecond = new Scene(layout3, 1164, 365);
        Scene sceneAddSecondCorrect = new Scene(layout4, 1164, 365);
        Scene sceneAddThird = new Scene(layout5, 1164, 365);

        button1.setOnAction(e -> WrongAnswerBox.display("Λάθος Άπαντηση", "Ξαναπροσπάθησε!"));
        button3.setOnAction(e -> WrongAnswerBox.display("Λάθος Άπαντηση", "Ξαναπροσπάθησε!"));
        help.setOnAction(e -> Help.display("Βοήθεια", Scale));
        btnContinue.setOnAction(e -> Window.setScene(sceneAddSecond));
        button2.setOnAction(e -> Window.setScene(sceneFirstCorrect));
        button5.setOnAction(e -> WrongAnswerBox.display("Λάθος Άπαντηση", "Ξαναπροσπάθησε!"));
        button4.setOnAction(e -> WrongAnswerBox.display("Λάθος Άπαντηση", "Ξαναπροσπάθησε!"));
        btnReturn3.setOnAction(e -> Window.setScene(scene));
        help2.setOnAction(e -> Help2.display("Βοήθεια", "Το κάθε δάχτυλο είναι μια μονάδα!"));
        btnReturn4.setOnAction(e -> Window.setScene(scene));
        button6.setOnAction(e -> Window.setScene(sceneAddSecondCorrect));
        btnContinue2.setOnAction(e -> Window.setScene(sceneAddThird));
        button8.setOnAction(e -> WrongAnswerBox.display("Λάθος Άπαντηση", "Ξαναπροσπάθησε!"));
        button9.setOnAction(e -> WrongAnswerBox.display("Λάθος Άπαντηση", "Ξαναπροσπάθησε!"));
        help3.setOnAction(e -> Help3.display("Βοήθεια", Help3_1, Help3_2));
        btnReturn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition ft = new FadeTransition(Duration.millis(1000));
                ft.setNode(root);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                primaryStage.setScene(scene);
                rootAdd.getChildren().removeAll(t, btnNext);
                ft.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        btnAddCorrentAnswerOnFirst.setTranslateX(535);
                        btnAddCorrentAnswerOnFirst.setTranslateY(450);
                    }
                });
            }
        });
        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition ft = new FadeTransition(Duration.millis(1000));
                ft.setNode(root);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                primaryStage.setScene(scene);
                rootAdd.getChildren().removeAll(t, btnNext);
                ft.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        btnAddCorrentAnswerOnFirst.setTranslateX(535);
                        btnAddCorrentAnswerOnFirst.setTranslateY(450);
                    }
                });
            }
        });
        btnReturn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition ft = new FadeTransition(Duration.millis(1000));
                ft.setNode(root);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                primaryStage.setScene(scene);
                rootAdd.getChildren().removeAll(t, btnNext);
                ft.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        btnAddCorrentAnswerOnFirst.setTranslateX(535);
                        btnAddCorrentAnswerOnFirst.setTranslateY(450);
                    }
                });
            }
        });
        btnReturn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition ft = new FadeTransition(Duration.millis(1000));
                ft.setNode(root);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                primaryStage.setScene(scene);
                rootAdd.getChildren().removeAll(t, btnNext);
                ft.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        btnAddCorrentAnswerOnFirst.setTranslateX(535);
                        btnAddCorrentAnswerOnFirst.setTranslateY(450);
                    }
                });
            }
        });
        btnReturn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition ft = new FadeTransition(Duration.millis(1000));
                ft.setNode(root);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                primaryStage.setScene(scene);
                rootAdd.getChildren().removeAll(t, btnNext);
                ft.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        btnAddCorrentAnswerOnFirst.setTranslateX(535);
                        btnAddCorrentAnswerOnFirst.setTranslateY(450);
                    }
                });
            }
        });
        btnReturn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition ft = new FadeTransition(Duration.millis(1000));
                ft.setNode(root);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                primaryStage.setScene(scene);
                rootAdd.getChildren().removeAll(t, btnNext);
                ft.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        btnAddCorrentAnswerOnFirst.setTranslateX(535);
                        btnAddCorrentAnswerOnFirst.setTranslateY(450);
                    }
                });
            }
        });

        layout.setAlignment(button1, Pos.TOP_LEFT);
        layout.setAlignment(button2, Pos.TOP_CENTER);
        layout.setAlignment(button3, Pos.TOP_RIGHT);
        layout.setAlignment(btnReturn, Pos.TOP_LEFT);
        layout.setAlignment(help, Pos.TOP_LEFT);
        layout2.setAlignment(btnReturn2, Pos.TOP_LEFT);
        layout2.setAlignment(btnContinue, Pos.TOP_RIGHT);
        layout3.setAlignment(button4, Pos.TOP_LEFT);
        layout3.setAlignment(button5, Pos.TOP_CENTER);
        layout3.setAlignment(button6, Pos.TOP_RIGHT);
        layout3.setAlignment(help2, Pos.TOP_LEFT);
        layout3.setAlignment(btnReturn3, Pos.TOP_LEFT);
        layout4.setAlignment(btnReturn4, Pos.TOP_LEFT);
        layout4.setAlignment(btnContinue2, Pos.TOP_RIGHT);
        layout5.setAlignment(button7, Pos.TOP_LEFT);
        layout5.setAlignment(button8, Pos.TOP_CENTER);
        layout5.setAlignment(button9, Pos.TOP_RIGHT);
        layout5.setAlignment(btnReturn5, Pos.TOP_LEFT);
        layout5.setAlignment(help3, Pos.TOP_LEFT);

        btnReturnMine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition ft = new FadeTransition(Duration.millis(1000));
                ft.setNode(root);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                primaryStage.setScene(scene);
            }
        });
        btnReturnOnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition ft = new FadeTransition(Duration.millis(1000));
                ft.setNode(root);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                primaryStage.setScene(scene);
                rootAdd.getChildren().removeAll(t, btnNext);
                ft.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        btnAddCorrentAnswerOnFirst.setTranslateX(535);
                        btnAddCorrentAnswerOnFirst.setTranslateY(450);
                    }
                });
            }
        });
        btnLearn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rootLearn.translateYProperty().set(scene.getHeight());

                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(rootLearn.translateYProperty(), 0, Interpolator.EASE_OUT);
                KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
                timeline.getKeyFrames().add(kf);
                primaryStage.setScene(sceneLearn);
                timeline.play();
            }
        });
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition ft = new FadeTransition(Duration.millis(1000));
                ft.setNode(rootAdd);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                primaryStage.setScene(sceneAdd);
            }
        });
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FadeTransition ft = new FadeTransition(Duration.millis(1000));
                ft.setNode(layout);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                primaryStage.setScene(sceneAddFirst);
            }
        });

        btnAddCorrentAnswerOnFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Path path = new Path();
                path.getElements().add(new MoveTo(535, 370));
                path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
                path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 850, 350));
                PathTransition pathTransition = new PathTransition();
                pathTransition.setDuration(Duration.millis(2500));
                pathTransition.setPath(path);
                pathTransition.setNode(btnAddCorrentAnswerOnFirst);
                pathTransition.setOrientation(PathTransition.OrientationType.NONE);
                pathTransition.setCycleCount(1);
                pathTransition.play();

                pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        rootAdd.getChildren().addAll(t, btnNext);
                    }

                });
            }
        });

        //Scene, Stages
        rootAdd.getChildren().addAll(imageView3, titleAdd, btnReturnOnAdd, text, text2, text3, text4, btnAdd1, btnAddCorrentAnswerOnFirst, btnAdd3);
        rootLearn.getChildren().addAll(imageView2, btnStart, titleLearn, btnStart2, btnStart3, btnStart4, btnStart5, btnReturnMine);
        root.getChildren().addAll(imageView, title, menuBox, line);

        primaryStage.setTitle("Μαθηματικά Β δημοτικού");
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
