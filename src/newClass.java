

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class newClass extends Application {
    ArrayList<Questions> mainArrayOfQuestionObjects = new ArrayList<>();
    Stage window ;
    Scene createUIscene, quizUIscene, deleteUIscene, editUIscene, mainUIscene;


    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
            readQuestionsFromFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


        window = primaryStage;
        Pane root = new Pane();
        primaryStage.setTitle("JavaFX Question Bank");
        mainUIscene = new Scene(root, 500, 500);
        primaryStage.setScene(mainUIscene);
        primaryStage.show();
        Text Names = new Text("Mohammed Alsaihati     Mustafa Alsadah");
        Text welcomeMessageText = new Text("Welcome to Questions Bank ");
        welcomeMessageText.setFont(Font.font("Century Gothic",32));
        welcomeMessageText.setStyle("-fx-fill: white");
        welcomeMessageText.setX(35);
        welcomeMessageText.setY(70);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #1e90ff, #00FFFF)");
        Button addBtn = new Button("Add");
        addBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");

        Button editBtn = new Button("Edit");
        Button view1 = new Button("View");
        view1.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");
        root.getChildren().addAll(view1);
        view1.setOnAction(e->{
            try {
                viewQuestions();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        editBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");
        Button deleteBtn = new Button("Delete");
        deleteBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");
        Button viewBtn = new Button("Quiz");
        viewBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");
        viewBtn.setLayoutX(185);
        viewBtn.setLayoutY(320);
        view1.setLayoutX(270);
        view1.setLayoutY(250);
        deleteBtn.setLayoutY(250);
        deleteBtn.setLayoutX(100);
        deleteBtn.setMinSize(100,50);
        editBtn.setLayoutX(270);
        editBtn.setLayoutY(150);
        editBtn.setMinSize(100,50);
        addBtn.setLayoutX(100);
        addBtn.setLayoutY(150);
        addBtn.setMinSize(100,50);
        view1.setMinSize(100,50);

        viewBtn.setMinSize(100,50);
        Names.setX(50);
        Names.setY(430);
        Names.setFont(Font.font("Century Gothic",20));
        Names.setStyle("-fx-fill: white");

        root.getChildren().addAll(viewBtn,addBtn, welcomeMessageText , editBtn , deleteBtn, Names);
        //
        addBtn.setOnMouseEntered(e-> addBtn.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        addBtn.setOnMouseExited(e-> addBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));
        viewBtn.setOnMouseEntered(e-> viewBtn.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        viewBtn.setOnMouseExited(e-> viewBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));
        view1.setOnMouseEntered(e-> view1.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        view1.setOnMouseExited(e-> view1.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));
        editBtn.setOnMouseEntered(e-> editBtn.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        editBtn.setOnMouseExited(e-> editBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));
        deleteBtn.setOnMouseEntered(e-> deleteBtn.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        deleteBtn.setOnMouseExited(e-> deleteBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));
        //
        addBtn.setOnAction(e-> {
            try {
                addQuestions();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        editBtn.setOnAction(e->{
            try {
                editQuestion();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        deleteBtn.setOnAction(e->{
            try {
                deleteQuestions();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        viewBtn.setOnAction(e->{
            try {
                quiz();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        window.setOnCloseRequest(e->{
            try {
                saveChanges();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }
    public void readQuestionsFromFile() throws IOException, ClassNotFoundException , FileNotFoundException, EOFException {
        try {
            ObjectInputStream binaryFileReader = new ObjectInputStream(new FileInputStream("QuestionBank.dat"));
            for (int i =0 ; i<binaryFileReader.available();i++){
                mainArrayOfQuestionObjects.add(new Questions(binaryFileReader.readUTF(),binaryFileReader.readUTF(),binaryFileReader.readUTF(),binaryFileReader.readUTF(),binaryFileReader.readUTF(),binaryFileReader.readUTF()));
            }

        }catch (FileNotFoundException ee){

        }catch (EOFException e){

        }
    }
    public void saveChanges() throws IOException {
        ObjectOutputStream binaryFileWriter = new ObjectOutputStream(new FileOutputStream("QuestionBank.dat"));

        for(int indexOfQuestionObject = 0; indexOfQuestionObject < mainArrayOfQuestionObjects.size(); indexOfQuestionObject++){
            binaryFileWriter.writeUTF(mainArrayOfQuestionObjects.get(indexOfQuestionObject).getQuestionText());
            binaryFileWriter.writeUTF(mainArrayOfQuestionObjects.get(indexOfQuestionObject).getAnswer1Text());
            binaryFileWriter.writeUTF(mainArrayOfQuestionObjects.get(indexOfQuestionObject).getAnswer2Text());
            binaryFileWriter.writeUTF(mainArrayOfQuestionObjects.get(indexOfQuestionObject).getAnswer3Text());
            binaryFileWriter.writeUTF(mainArrayOfQuestionObjects.get(indexOfQuestionObject).getAnswer4Text());
            binaryFileWriter.writeUTF(mainArrayOfQuestionObjects.get(indexOfQuestionObject).getAnswer5Text());
        }
        binaryFileWriter.close();
    }///////DONE////////
    public void addQuestions() throws Exception{
        Pane PaneCreate = new Pane();
        PaneCreate.setStyle("-fx-background-color: linear-gradient(to bottom right, #1e90ff, #00FFFF)");
        createUIscene = new Scene(PaneCreate, 500,500);
        window.setScene(createUIscene);
        window.setTitle("Create Questions");
        Button Confirm = new Button("Confirm");
        Confirm.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");
        Button Back = new Button("Back");
        Back.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");
        PaneCreate.getChildren().addAll(Confirm , Back);
        Confirm.setLayoutY(450);
        Confirm.setLayoutX(285);
        Back.setLayoutX(400);
        Back.setLayoutY(450);
        Back.setMinSize(70,30);
        Confirm.setMinSize(70,30);
        TextField questionTextField = new TextField();
        TextField answer1TextField = new TextField();
        TextField answer2TextField = new TextField();
        TextField answer3TextField = new TextField();
        TextField answer4TextField = new TextField();
        TextField correctAnswerTextField = new TextField();
        Text Correct = new Text("Correct Answer: ");
        Correct.setStyle("-fx-fill: white");
        Text Ques = new Text("Question: " );
        Ques.setStyle("-fx-fill: white");
        Text Answer11 = new Text("Answer 1: " );
        Answer11.setStyle("-fx-fill: white");
        Text Answer22 = new Text("Answer 2: " );
        Answer22.setStyle("-fx-fill: white");
        Text Answer33 = new Text("Answer 3: " );
        Answer33.setStyle("-fx-fill: white");
        Text Answer44 = new Text("Answer 4: " );
        Answer44.setStyle("-fx-fill: white");

        //Pane nodes moving:
        Answer11.setLayoutX(50);
        Answer11.setLayoutY(120);
        answer1TextField.setLayoutX(110);
        answer1TextField.setLayoutY(103);
        answer1TextField.setMinSize(300 ,20 );
        Answer22.setLayoutX(50);
        Answer22.setLayoutY(170);
        answer2TextField.setLayoutX(110);
        answer2TextField.setLayoutY(151);
        answer2TextField.setMinSize(300 ,20 );
        Answer33.setLayoutX(50);
        Answer33.setLayoutY(220);
        answer3TextField.setLayoutX(110);
        answer3TextField.setLayoutY(199);
        answer3TextField.setMinSize(300 ,20 );
        answer4TextField.setMinSize(300 ,20 );
        Answer44.setLayoutX(50);
        Answer44.setLayoutY(270);
        answer4TextField.setLayoutY(250);
        answer4TextField.setLayoutX(110);
        correctAnswerTextField.setLayoutX(110);
        correctAnswerTextField.setLayoutY(300);
        correctAnswerTextField.setMinSize(300 ,20 );
        Correct.setLayoutX(20);
        Correct.setLayoutY(320);
        Ques.setLayoutX(50);
        Ques.setLayoutY(70);
        questionTextField.setLayoutX(110);
        questionTextField.setLayoutY(55);
        questionTextField.setMinSize(300 ,20);
        correctAnswerTextField.setPromptText("Enter the correct answer");
        PaneCreate.getChildren().addAll(Correct,correctAnswerTextField,Ques, questionTextField , Answer11, answer1TextField , Answer22 , answer2TextField, Answer33 ,answer3TextField, Answer44, answer4TextField);

        Confirm.setOnAction(e->{
            //Add TextFields Content into a Question Object constructor as a parameters and save that question object into the arrayList of questions.
            mainArrayOfQuestionObjects.add(new Questions(questionTextField.getText() , answer1TextField.getText(), answer2TextField.getText(), answer3TextField.getText() ,answer4TextField.getText() , correctAnswerTextField.getText()));
            //Clear text fields content
            questionTextField.setText("");
            answer1TextField.setText("");
            answer2TextField.setText("");
            answer3TextField.setText("");
            answer4TextField.setText("");
            correctAnswerTextField.setText("");
        });
        Back.setOnAction(e->{
            window.setScene(mainUIscene);
        });

        //Set a different style for buttons when mouse enters their fields.
        Back.setOnMouseEntered(e-> Back.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        Back.setOnMouseExited(e-> Back.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));
        Confirm.setOnMouseEntered(e-> Confirm.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        Confirm.setOnMouseExited(e-> Confirm.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));

    }///////DONE////////
    public void editQuestion() throws IOException {
        //Create a root for edit UI:
        Pane editPane = new Pane();
        editPane.setStyle("-fx-background-color: linear-gradient(to bottom right, #1e90ff, #00FFFF)");
        editUIscene = new Scene(editPane, 500,500);
        window.setScene(editUIscene);
        window.setTitle("Edit questionsTextCBX");
        Button confirmBtn = new Button("Confirm");
        Button backBtn = new Button("Back");
        confirmBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");
        backBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");
        confirmBtn.setLayoutY(450);
        confirmBtn.setLayoutX(285);
        backBtn.setLayoutX(400);
        backBtn.setLayoutY(450);
        backBtn.setMinSize(70,30);
        confirmBtn.setMinSize(70,30);
        backBtn.setOnMouseEntered(e-> backBtn.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        backBtn.setOnMouseExited(e-> backBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));
        confirmBtn.setOnMouseEntered(e-> confirmBtn.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        confirmBtn.setOnMouseExited(e-> confirmBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));

        //create an array that contains the questionTextField text of all questionTextField objects in the main list
        ArrayList questionsTextArray = new ArrayList();
        for (int i = 0; i< mainArrayOfQuestionObjects.size(); i++){
            questionsTextArray.add(mainArrayOfQuestionObjects.get(i).getQuestionText());
        }

        ComboBox questionsTextCBX = new ComboBox();
        editPane.getChildren().addAll(questionsTextCBX , confirmBtn , backBtn);
        //insert all questions texts into the combo box
        questionsTextCBX.getItems().addAll(questionsTextArray);

        TextField questionTextField = new TextField();
        TextField answer1TextField = new TextField();
        TextField answer2TextField = new TextField();
        TextField answer3TextField = new TextField();
        TextField answer4TextField = new TextField();
        TextField correctAnswerTextField = new TextField();
        Text correctTxt = new Text("correct Answer: ");
        correctTxt.setStyle("-fx-fill: white");
        Text questionTxt = new Text("Question : " );
        questionTxt.setStyle("-fx-fill: white");
        Text answer1Txt = new Text("Answer 1: " );
        answer1Txt.setStyle("-fx-fill: white");
        Text answer2Txt = new Text("Answer 2: " );
        answer2Txt.setStyle("-fx-fill: white");
        Text answer3Txt = new Text("Answer 3: " );
        answer3Txt.setStyle("-fx-fill: white");
        Text answer4Txt = new Text("Answer 4: " );
        answer4Txt.setStyle("-fx-fill: white");

        answer1Txt.setLayoutX(50);
        answer1Txt.setLayoutY(120);
        answer1TextField.setLayoutX(110);
        answer1TextField.setLayoutY(103);
        answer1TextField.setMinSize(300 ,20 );
        answer2Txt.setLayoutX(50);
        answer2Txt.setLayoutY(170);
        answer2TextField.setLayoutX(110);
        answer2TextField.setLayoutY(151);
        correctAnswerTextField.setLayoutX(110);
        correctAnswerTextField.setLayoutY(300);
        correctAnswerTextField.setMinSize(300 ,20 );
        correctTxt.setLayoutX(20);
        correctTxt.setLayoutY(320);
        answer2TextField.setMinSize(300 ,20 );
        answer3Txt.setLayoutX(50);
        answer3Txt.setLayoutY(220);
        answer3TextField.setLayoutX(110);
        answer3TextField.setLayoutY(199);
        answer3TextField.setMinSize(300 ,20 );
        answer4TextField.setMinSize(300 ,20 );
        answer4Txt.setLayoutX(50);
        answer4Txt.setLayoutY(270);
        answer4TextField.setLayoutY(250);
        answer4TextField.setLayoutX(110);
        questionTxt.setLayoutX(50);
        questionTxt.setLayoutY(70);
        questionTextField.setLayoutX(110);
        questionTextField.setLayoutY(55);
        questionTextField.setMinSize(300 ,20);
        editPane.getChildren().addAll(correctTxt, correctAnswerTextField, questionTxt, questionTextField, answer1Txt, answer1TextField, answer2Txt, answer2TextField, answer3Txt, answer3TextField, answer4Txt, answer4TextField);

        AtomicInteger Index = new AtomicInteger();

        questionsTextCBX.setOnAction(e->{
            //create an Atomic integer that changes automaticly to save the value of combo box current index
            Index.set(questionsTextCBX.getSelectionModel().getSelectedIndex());
            //insert the selected question object elements in the text fields by calling the object using the atomic integer
            questionTextField.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getQuestionText());
            answer1TextField.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getAnswer1Text());
            answer2TextField.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getAnswer2Text());
            answer3TextField.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getAnswer3Text());
            answer4TextField.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getAnswer4Text());
            correctAnswerTextField.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getAnswer5Text());
        });
        confirmBtn.setOnAction(e->{
            //editing the original questio in the main list by creating a new version of the question and inserting it in the index of the old question
            mainArrayOfQuestionObjects.set(Index.intValue() , new Questions(questionTextField.getText(), answer1TextField.getText(), answer2TextField.getText(), answer3TextField.getText(), answer4TextField.getText(), correctAnswerTextField.getText()));
        });
        backBtn.setOnAction(e->{
            window.setScene(mainUIscene);
        });


    }///////DONE////////
    public void viewQuestions() throws IOException {
        Pane viewPane = new Pane();
        viewPane.setStyle("-fx-background-color: linear-gradient(to bottom right, #1e90ff, #00FFFF)");
        editUIscene = new Scene(viewPane, 500,500);
        window.setScene(editUIscene);
        window.setTitle("quiz questionsTextCBX");
        Button Back = new Button("Back");
        Back.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");

        Back.setLayoutX(400);
        Back.setLayoutY(450);
        Back.setMinSize(70,30);
        Back.setOnMouseEntered(e-> Back.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        Back.setOnMouseExited(e-> Back.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));

        ArrayList questionsTextArray = new ArrayList();
        for (int i = 0; i< mainArrayOfQuestionObjects.size(); i++){
            questionsTextArray.add(mainArrayOfQuestionObjects.get(i).getQuestionText());
        }

        ComboBox questionsTextCBX = new ComboBox();
        viewPane.getChildren().addAll(questionsTextCBX, Back);
        questionsTextCBX.getItems().addAll(questionsTextArray);


        TextField Question = new TextField();
        TextField Answer1 = new TextField();
        TextField Answer2 = new TextField();
        TextField Answer3 = new TextField();
        TextField Answer4 = new TextField();
        TextField CorrectAnswer = new TextField();
        Text Correct = new Text("Correct Answer: ");
        Correct.setStyle("-fx-fill: white");
        Text Ques = new Text("Question: " );
        Ques.setStyle("-fx-fill: white");
        Text Answer11 = new Text("Answer 1: " );
        Answer11.setStyle("-fx-fill: white");
        Text Answer22 = new Text("Answer 2: " );
        Answer22.setStyle("-fx-fill: white");
        Text Answer33 = new Text("Answer 3: " );
        Answer33.setStyle("-fx-fill: white");
        Text Answer44 = new Text("Answer 4: " );
        Answer44.setStyle("-fx-fill: white");

        Answer11.setLayoutX(50);
        Answer11.setLayoutY(120);
        Answer1.setLayoutX(110);
        Answer1.setLayoutY(103);
        Answer1.setMinSize(300 ,20 );
        Answer22.setLayoutX(50);
        Answer22.setLayoutY(170);
        Answer2.setLayoutX(110);
        Answer2.setLayoutY(151);
        CorrectAnswer.setLayoutX(110);
        CorrectAnswer.setLayoutY(300);
        CorrectAnswer.setMinSize(300 ,20 );
        Correct.setLayoutX(20);
        Correct.setLayoutY(320);
        Answer2.setMinSize(300 ,20 );
        Answer33.setLayoutX(50);
        Answer33.setLayoutY(220);
        Answer3.setLayoutX(110);
        Answer3.setLayoutY(199);
        Answer3.setMinSize(300 ,20 );
        Answer4.setMinSize(300 ,20 );
        Answer44.setLayoutX(50);
        Answer44.setLayoutY(270);
        Answer4.setLayoutY(250);
        Answer4.setLayoutX(110);
        Ques.setLayoutX(50);
        Ques.setLayoutY(70);
        Question.setLayoutX(110);
        Question.setLayoutY(55);
        Question.setMinSize(300 ,20);
        Question.setEditable(false);
        Answer1.setEditable(false);
        Answer2.setEditable(false);
        Answer3.setEditable(false);
        Answer4.setEditable(false);
        CorrectAnswer.setEditable(false);
        viewPane.getChildren().addAll(Correct,CorrectAnswer,Ques, Question , Answer11, Answer1 , Answer22 , Answer2, Answer33 ,Answer3, Answer44, Answer4);
        AtomicInteger Index = new AtomicInteger();
        questionsTextCBX.setOnAction(e->{
            Index.set(questionsTextCBX.getSelectionModel().getSelectedIndex());
            Question.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getQuestionText());
            Answer1.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getAnswer1Text());
            Answer2.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getAnswer2Text());
            Answer3.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getAnswer3Text());
            Answer4.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getAnswer4Text());
            CorrectAnswer.setText(mainArrayOfQuestionObjects.get(Index.intValue()).getAnswer5Text());
        });

        Back.setOnAction(e->{

            window.setScene(mainUIscene);

        });


    }///////DONE////////
    public void deleteQuestions() throws FileNotFoundException {
        Pane deletePane = new Pane();
        deletePane.setStyle("-fx-background-color: linear-gradient(to bottom right, #1e90ff, #00FFFF)");
        deleteUIscene = new Scene(deletePane, 500 ,500);
        window.setScene(deleteUIscene);
        window.setTitle("Delete Questions");

        ArrayList arrayOfQuestionsTexts = new ArrayList();
        for(int i = 0; i< mainArrayOfQuestionObjects.size(); i++){
            arrayOfQuestionsTexts.add(mainArrayOfQuestionObjects.get(i).getQuestionText());
        }
        ComboBox questionsTextsCBX = new ComboBox();
        questionsTextsCBX.getItems().addAll(arrayOfQuestionsTexts);
        Button confirmBtn = new Button("Delete");
        Button backBtn = new Button("Back");
        deletePane.getChildren().addAll(questionsTextsCBX, confirmBtn, backBtn);
        questionsTextsCBX.setLayoutX(20);
        questionsTextsCBX.setLayoutY(20);
        confirmBtn.setLayoutY(450);
        confirmBtn.setLayoutX(285);
        backBtn.setLayoutX(390);
        backBtn.setLayoutY(450);
        backBtn.setMinSize(70,30);
        confirmBtn.setMinSize(70,30);
        backBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");
        confirmBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");
        confirmBtn.setOnMouseEntered(e-> confirmBtn.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        confirmBtn.setOnMouseExited(e-> confirmBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));
        backBtn.setOnMouseEntered(e-> backBtn.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        backBtn.setOnMouseExited(e-> backBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));


        AtomicInteger indexOfQuestion = new AtomicInteger();

        questionsTextsCBX.setOnAction(e->{
            indexOfQuestion.set(questionsTextsCBX.getSelectionModel().getSelectedIndex());
        });
        confirmBtn.setOnAction(e->{
            mainArrayOfQuestionObjects.remove(indexOfQuestion.intValue());
        });
        backBtn.setOnAction(e->{
            window.setScene(mainUIscene);
        });


    }///////DONE////////
    public void quiz() throws FileNotFoundException {
        Text show = new Text();
        Button backBtn = new Button("backBtn");
        backBtn.setOnAction(e->{
            window.setScene(mainUIscene);
        });


        Pane quizPane = new Pane();
        quizPane.setStyle("-fx-background-color: linear-gradient(to bottom right, #1e90ff, #00FFFF)");
        quizUIscene = new Scene(quizPane, 500 ,500);
        window.setScene(quizUIscene);
        window.setTitle("Quiz Questions");

        ArrayList arrayQuestion = new ArrayList();
        for(int i = 0; i< mainArrayOfQuestionObjects.size(); i++){
            arrayQuestion.add(mainArrayOfQuestionObjects.get(i).getQuestionText());
        }

        ComboBox questionsTextCBX = new ComboBox();
        questionsTextCBX.getItems().addAll(arrayQuestion);
        RadioButton answer1RDBTN = new RadioButton();
        RadioButton answer2RDBTN = new RadioButton();
        RadioButton answer3RDBTN = new RadioButton();
        RadioButton answer4RDBTN = new RadioButton();
        Text correctAnswerTxt = new Text();
        ToggleGroup TG = new ToggleGroup();
        Button Confirm = new Button("Submit");
        backBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");
        Confirm.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");

        answer1RDBTN.setToggleGroup(TG);
        answer2RDBTN.setToggleGroup(TG);
        answer3RDBTN.setToggleGroup(TG);
        answer4RDBTN.setToggleGroup(TG);
        quizPane.getChildren().addAll(questionsTextCBX, answer1RDBTN, answer2RDBTN, answer3RDBTN, answer4RDBTN, Confirm , show , backBtn);
        show.setLayoutX(210);
        show.setLayoutY(330);
        show.setStyle("    -fx-fill: white;\n" +
                "    -fx-font-size: 23px;");
        show.setFont(Font.font(24));
        Confirm.setLayoutY(450);
        Confirm.setLayoutX(285);
        backBtn.setLayoutX(400);
        backBtn.setLayoutY(450);
        backBtn.setMinSize(70,30);
        Confirm.setMinSize(70,30);
        questionsTextCBX.setLayoutX(22);
        questionsTextCBX.setLayoutY(42);
        questionsTextCBX.setPrefSize(400,40);
        questionsTextCBX.setStyle("-fx-font-size: 18px;");
        //questionsTextCBX.
        Confirm.setOnMouseEntered(e-> Confirm.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        Confirm.setOnMouseExited(e-> Confirm.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));
        backBtn.setOnMouseEntered(e-> backBtn.setStyle("   -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-font-weight:bold;"));
        backBtn.setOnMouseExited(e-> backBtn.setStyle(" -fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00FFFF),\n" +
                "            linear-gradient(#1e90ff, #00BFFF);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;"));

        answer1RDBTN.setLayoutX(25);
        answer1RDBTN.setLayoutY(120);
        answer1RDBTN.setStyle("-fx-font-size: 18px;");

        answer2RDBTN.setLayoutX(25);
        answer2RDBTN.setLayoutY(160);
        answer2RDBTN.setStyle("-fx-font-size: 18px;");

        answer3RDBTN.setLayoutX(25);
        answer3RDBTN.setLayoutY(200);
        answer3RDBTN.setStyle("-fx-font-size: 18px;");

        answer4RDBTN.setLayoutX(25);
        answer4RDBTN.setLayoutY(240);
        answer4RDBTN.setStyle("-fx-font-size: 18px;");


        AtomicInteger indexOfQuestion = new AtomicInteger();
        questionsTextCBX.setOnAction(e->{
            //set the index of question to contain the value of the combo box index
            indexOfQuestion.set(questionsTextCBX.getSelectionModel().getSelectedIndex());
            //Set the radio buttons texts to be the answers of the question
            answer1RDBTN.setText(mainArrayOfQuestionObjects.get(indexOfQuestion.intValue()).getAnswer1Text());
            answer2RDBTN.setText(mainArrayOfQuestionObjects.get(indexOfQuestion.intValue()).getAnswer2Text());
            answer3RDBTN.setText(mainArrayOfQuestionObjects.get(indexOfQuestion.intValue()).getAnswer3Text());
            answer4RDBTN.setText(mainArrayOfQuestionObjects.get(indexOfQuestion.intValue()).getAnswer4Text());
            //set the correct answer in a variable to use in answer selection validation
            correctAnswerTxt.setText(mainArrayOfQuestionObjects.get(indexOfQuestion.intValue()).getAnswer5Text());

        });

        Confirm.setOnAction(e->{
            if (answer1RDBTN.isSelected() && answer1RDBTN.getText().equals(correctAnswerTxt.getText())){
                show.setText("Correct Answer!");
            }
            else if (answer2RDBTN.isSelected() && answer2RDBTN.getText().equals(correctAnswerTxt.getText())) {
                show.setText("Correct Answer!");

            }else if (answer3RDBTN.isSelected() && answer3RDBTN.getText().equals(correctAnswerTxt.getText())){
                show.setText("Correct Answer!");
            }else if (answer4RDBTN.isSelected() && answer4RDBTN.getText().equals(correctAnswerTxt.getText())){
                show.setText("Correct Answer!");
            }
            else{
                show.setText("Wrong Answer!");
            }
        });



    }///////DONE////////

    public static void main(String[] args) {
        launch(args);
    }

}
class Questions{
    private String questionText, answer1Text, answer2Text, answer3Text, answer4Text, answer5Text;
    Questions(String Q , String A1 ,String A2, String A3 ,String A4 , String A5){
        this.questionText = Q;
        this.answer1Text = A1;
        this.answer2Text = A2;
        this.answer3Text = A3;
        this.answer4Text = A4;
        this.answer5Text = A5;
    }


    //write getters ans setters:
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setAnswer1Text(String answer1Text) {
        this.answer1Text = answer1Text;
    }

    public void setAnswer2Text(String answer2Text) {
        this.answer2Text = answer2Text;
    }

    public void setAnswer3Text(String answer3Text) {
        this.answer3Text = answer3Text;
    }

    public void setAnswer4Text(String answer4Text) {
        this.answer4Text = answer4Text;
    }

    public void setAnswer5Text(String answer5Text) {
        this.answer5Text = answer5Text;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer1Text() {
        return answer1Text;
    }

    public String getAnswer2Text() {
        return answer2Text;
    }

    public String getAnswer3Text() {
        return answer3Text;
    }

    public String getAnswer4Text() {
        return answer4Text;
    }

    public String getAnswer5Text() {
        return answer5Text;
    }

}