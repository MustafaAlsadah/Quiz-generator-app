
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.application.Application.launch;

public class MainStarter extends Application {
    static FileInputStream f1;
    public static void openWebpage(String url) {
        try {
            new ProcessBuilder("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe", url).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            f1 = new FileInputStream(new File("src/Competitions Participations.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static XSSFWorkbook h1;

    static {
        try {
            h1 = new XSSFWorkbook(f1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Competition> mainDataBase;
    private static ArrayList<TeamMemberCompetitor> mainDataBaseTeam;
    AtomicInteger in1 = new AtomicInteger();
    Stage home;
    Scene scene1;

    public static void main(String args [] ) throws IOException, URISyntaxException {
        launch(args);
    }
    public static ArrayList<Competition> readAndSaveCompetitionsFromDataBase() throws IOException {
        ArrayList<Competition> DB = new ArrayList<>();
        FileInputStream MAIN_DATA_BASE_FILE = new FileInputStream("src/Competitions Participations.xlsx" );
        XSSFWorkbook ourExcelDB = new XSSFWorkbook(MAIN_DATA_BASE_FILE);
        int numberOfSheets = ourExcelDB.getNumberOfSheets();

        for (int i=0; i<numberOfSheets; i++){
            XSSFSheet sheet = ourExcelDB.getSheetAt(i);
            boolean isTeamBased = sheet.getRow(5).getLastCellNum()==5?false:true;
            Competition c = new Competition(sheet.getSheetName(), isTeamBased);
            DB.add(c);
        }
        return DB;


        }

    public void show() throws IOException {
        mainDataBase = readAndSaveCompetitionsFromDataBase();

        //

        home.setTitle("Show all competitions");
        Pane paneAdd = new Pane();
        Scene sceneAdd = new Scene(paneAdd,450,450);
        home.setScene(sceneAdd);
        Button back = new Button("Back");
        back.setMinSize(90,40);
        back.setLayoutX(250);
        back.setLayoutY(380);
        Button confirm = new Button("Confirm");
        confirm.setMinSize(90,40);
        confirm.setLayoutX(350);
        confirm.setLayoutY(380);
        Button visitWebsiteBtn = new Button("Visit website");
        visitWebsiteBtn.setMinSize(90,40);
        visitWebsiteBtn.setLayoutX(150);
        visitWebsiteBtn.setLayoutY(380);
        Button details = new Button("Details");
        details.setLayoutY(200);
        details.setLayoutX(100);
        back.setOnAction(ex->{

            home.setScene(scene1);
            home.setTitle("SWE-206 Group 2");

        });

        ComboBox cb = new ComboBox();
        ArrayList names = new ArrayList();
        cb.setMinWidth(400);
        AtomicInteger index = new AtomicInteger() ;
        TextField nameOfComp = new TextField();
        TextField dateOfComp = new TextField();
        TextField websiteOfComp= new TextField();
        nameOfComp.setEditable(false);
        dateOfComp.setEditable(false);
        websiteOfComp.setEditable(false);
        nameOfComp.setLayoutX(100);
        nameOfComp.setLayoutY(105);
        nameOfComp.setMinWidth(200);
        dateOfComp.setLayoutX(100);
        dateOfComp.setLayoutY(150);
        dateOfComp.setMinWidth(200);
        websiteOfComp.setLayoutX(100);
        websiteOfComp.setLayoutY(200);
        websiteOfComp.setMinWidth(200);
        ArrayList dates   = new ArrayList();
        ArrayList websites = new ArrayList();
        TextField isItBasedOnTeam = new TextField();
        isItBasedOnTeam.setLayoutX(200);
        isItBasedOnTeam.setLayoutY(250);

        Label nameLabel = new Label("Name:");
        nameLabel.setFont(new Font(20));
        nameLabel.setLayoutX(20);
        nameLabel.setLayoutY(100);
        Label dateLabel = new Label("Date:");
        dateLabel.setFont(new Font(20));
        dateLabel.setLayoutX(20);
        dateLabel.setLayoutY(145);
        Label webLabel = new Label("Website:");
        webLabel.setFont(new Font(20));
        webLabel.setLayoutX(20);
        webLabel.setLayoutY(195);
        Label teamLabel = new Label("Teams or Indivsuals:");
        teamLabel.setFont(new Font(20));
        teamLabel.setLayoutX(20);
        teamLabel.setLayoutY(245);
        isItBasedOnTeam.setEditable(false);

        for (int i = 0; i< mainDataBase.size(); i++){
            names.add(mainDataBase.get(i).nameOfCompetition);
            dates.add(mainDataBase.get(i).date);
            websites.add(mainDataBase.get(i).link);
        }
        paneAdd.getChildren().addAll(nameOfComp , dateOfComp , websiteOfComp, isItBasedOnTeam, nameLabel , dateLabel , webLabel , teamLabel);

        System.out.println(index.intValue());
        cb.getItems().addAll(names);
        Stage stageWebsite = new Stage();
        //Pane paneWebsite = new Pane();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        Scene sceneWebsite = new Scene(webView, 720 , 720);
        stageWebsite.setScene(sceneWebsite);
        Button button = new Button();
        //paneWebsite.getChildren().addAll(webView , button);



        cb.setOnAction(e->{
            index.set(cb.getSelectionModel().getSelectedIndex());
            if ( mainDataBase.get(index.intValue()).isTeamBased ==true)
            {
                System.out.println(index.intValue());
                nameOfComp.setText(mainDataBase.get(index.intValue()).nameOfCompetition);
                dateOfComp.setText(mainDataBase.get(index.intValue()).date);
                websiteOfComp.setText(mainDataBase.get(index.intValue()).link);
                isItBasedOnTeam.setText("Team Competition");
            }
            else {
                System.out.println(index.intValue());
                nameOfComp.setText(mainDataBase.get(index.intValue()).nameOfCompetition);
                dateOfComp.setText(mainDataBase.get(index.intValue()).date);
                websiteOfComp.setText(mainDataBase.get(index.intValue()).link);
                isItBasedOnTeam.setText("Individual Competition");

            }
        });
        button.setOnAction(e->{
            webEngine.load("https://twitter.com/CyberhubSa");
        });
        visitWebsiteBtn.setOnAction(e->{

            try{
                System.out.println();
                webEngine.load(mainDataBase.get(index.intValue()).link);
                stageWebsite.show();
                //-Dcom.sun.webkit.useHTTP2Loader=false;
            }
            catch (Exception ee){
            };

        });

        confirm.setOnAction(ex->{

            Stage detail = new Stage();
            Pane pane2 = new Pane( );
            detail.setScene(new Scene(pane2, 720,500));
            detail.show();
            if (mainDataBase.get(cb.getSelectionModel().getSelectedIndex()).isTeamBased == true){
                final ObservableList data =
                        FXCollections.observableArrayList(
                        );
                TableView table = new TableView();
                TableColumn<TeamMemberCompetitor, String > name = new TableColumn<>("name" );
                TableColumn <TeamMemberCompetitor, String >IDs = new TableColumn<>("ID");
                TableColumn<TeamMemberCompetitor, String > ranks = new TableColumn<>("Team Rank");
                TableColumn<TeamMemberCompetitor, String > tName = new TableColumn<>("Team Name");
                TableColumn <TeamMemberCompetitor, String >tNumber = new TableColumn<>("Team number");
                TableColumn <TeamMemberCompetitor, String >major = new TableColumn<>("Major");


                table.getColumns().addAll(name,IDs,ranks,tName,tNumber , major);
                table.setMinSize(515,720);
                table.setLayoutX(50);
                table.setLayoutY(20);


                detail.setTitle("Details of "+ names.get(cb.getSelectionModel().getSelectedIndex()) + " Competition");ArrayList<Competitor> competitorss = new ArrayList<>();
                ArrayList teamComp = new ArrayList();
                ArrayList teamName = new ArrayList();

                ArrayList nameOfCompetitors = new ArrayList();
                ArrayList rankOfCompetitors = new ArrayList();
                ArrayList IDOfCompetitors = new ArrayList();
                ArrayList MajorOfCompetitors = new ArrayList();
                ArrayList TeamCompetitiors = new ArrayList();
                competitorss = mainDataBase.get(cb.getSelectionModel().getSelectedIndex()).listOfCompetitors;
                System.out.println(competitorss.size());
                //For Teams
                // ArrayList<TeamMemberCompetitor> teamCompetitiors = mainDataBaseTeam.get(cb.getSelectionModel().getSelectedIndex()).teamName;

                ListView lvName = new ListView();
                ListView lvRank = new ListView();
                name.setCellValueFactory(
                        cellData -> new SimpleStringProperty(cellData.getValue().getName())

                );
                tName.setCellValueFactory(
                        cellData -> new SimpleStringProperty(cellData.getValue().getTeamName())

                );
                tNumber.setCellValueFactory(
                        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTeamNumber()))

                );
                major.setCellValueFactory(
                        cellData -> new SimpleStringProperty(cellData.getValue().getMajor())

                );
                IDs.setCellValueFactory(
                        cellData -> new SimpleStringProperty(cellData.getValue().getID())

                );
                ranks.setCellValueFactory(
                        cellData -> new SimpleStringProperty(cellData.getValue().getTeamRank())

                );



                data.addAll(competitorss);
                table.setItems(data);


                for( int i=0 ; i< nameOfCompetitors.size(); i++){
                    teamComp.add( ((TeamMemberCompetitor) competitorss.get(i)).teamNumber);
                    teamName.add(((TeamMemberCompetitor) competitorss.get(i)).teamName);
                }
                //for (int i =0 ; i< mainDataBase.get(cb.getSelectionModel().getSelectedIndex()).listOfCompetitors.size();i++){
                //    pane2.getChildren().addAll(lvTeam);
                // }




//                pane2.getChildren().addAll(lvName, lvRank , lvMajor,lvID , lvTeam , teamNamee );}
                pane2.getChildren().addAll(table);}

            else{

                detail.setTitle("Details of "+ names.get(cb.getSelectionModel().getSelectedIndex()) + " Competition");ArrayList<Competitor> competitorss = new ArrayList<>();
                final ObservableList data2 =
                        FXCollections.observableArrayList(
                        );
                TableView table2 = new TableView();
                TableColumn<Competitor, String > name2 = new TableColumn<>("name" );
                TableColumn <Competitor, String >IDs2 = new TableColumn<>("ID");
                TableColumn<Competitor, String > ranks2 = new TableColumn<>("Rank");
                TableColumn <Competitor, String >major2 = new TableColumn<>("Major");



                table2.getColumns().addAll(name2,IDs2,ranks2 , major2);
                table2.setMinSize(300,720);
                table2.setMaxSize(300,720);

                table2.setLayoutX(50);
                table2.setLayoutY(20);

                detail.setTitle("Details of "+ names.get(cb.getSelectionModel().getSelectedIndex()) + " Competition");
                ArrayList teamComp = new ArrayList();
                ArrayList teamName = new ArrayList();

                ArrayList nameOfCompetitors = new ArrayList();
                ArrayList rankOfCompetitors = new ArrayList();
                ArrayList IDOfCompetitors = new ArrayList();
                ArrayList MajorOfCompetitors = new ArrayList();
                ArrayList TeamCompetitiors = new ArrayList();
                competitorss = mainDataBase.get(cb.getSelectionModel().getSelectedIndex()).listOfCompetitors;
                System.out.println(competitorss.size());
                //For Teams
                // ArrayList<TeamMemberCompetitor> teamCompetitiors = mainDataBaseTeam.get(cb.getSelectionModel().getSelectedIndex()).teamName;




                for (int i =0 ; i< competitorss.size();i++){
                    nameOfCompetitors.add(competitorss.get(i).name);
                    rankOfCompetitors.add(competitorss.get(i).rank);
                    MajorOfCompetitors.add(competitorss.get(i).major);
                    IDOfCompetitors.add(competitorss.get(i).ID);

                }
                ListView lvName = new ListView();
                ListView lvRank = new ListView();
                lvName.getItems().addAll(nameOfCompetitors);
                lvRank.getItems().addAll(rankOfCompetitors);
                lvName.setLayoutX(20);
                lvName.setMaxWidth(150);
                lvRank.setLayoutX(340);
                lvRank.setMaxWidth(30);
                lvName.setLayoutY(40);
                lvRank.setLayoutY(40);

                ListView lvMajor = new ListView();
                lvMajor.getItems().addAll(MajorOfCompetitors);
                lvMajor.setLayoutX(270);
                lvMajor.setMaxWidth(45);


                ListView lvID = new ListView();
                lvID.setLayoutX(170);
                lvID.setMaxWidth(100);
                lvMajor.setLayoutY(40);
                lvID.setLayoutY(40);
                lvID.getItems().addAll(IDOfCompetitors);
                name2.setCellValueFactory(
                        cellData -> new SimpleStringProperty(cellData.getValue().getName())

                );


                major2.setCellValueFactory(
                        cellData -> new SimpleStringProperty(cellData.getValue().getMajor())

                );
                IDs2.setCellValueFactory(
                        cellData -> new SimpleStringProperty(cellData.getValue().getID())

                );
                ranks2.setCellValueFactory(
                        cellData -> new SimpleStringProperty(cellData.getValue().getRank())

                );



                data2.addAll(competitorss);
                table2.setItems(data2);
                pane2.getChildren().addAll(table2);

            }






        });

        paneAdd.getChildren().addAll(back , confirm , cb, visitWebsiteBtn);


    }
    void add() throws IOException {

        Stage detail = new Stage();
        Pane pane2 = new Pane( );
        detail.setScene(new Scene(pane2, 500,450));
        detail.show();
        TextField name = new TextField();
        TextField date = new TextField();
        TextField website = new TextField();
        //
        pane2.getChildren().addAll(name , date , website);
        //
        name.setLayoutX(100);
        name.setLayoutY(20);
        //
        date.setLayoutX(100);
        date.setLayoutY(70);
        //
        website.setLayoutX(100);
        website.setLayoutY(120);
        // MFAM MOHAMMED FAISAL

        RadioButton radioButton = new RadioButton("Single Competition");// if chosen the competiton will be single

        RadioButton radioButtonTeam = new RadioButton("Team Competition"); //If chosen the competiton will be team
        Label chosenTeam = new Label("Team or Single");
        Label namelabel = new Label("Name");
        Label dateLabel = new Label("Date");
        Label webSiteLabel = new Label("Website");
        namelabel.setLayoutY(20);
        namelabel.setLayoutX(10);
        //
        dateLabel.setLayoutX(10);
        dateLabel.setLayoutY(70);
        //
        webSiteLabel.setLayoutY(120);
        webSiteLabel.setLayoutX(10);


        radioButton.setLayoutX(100);
        radioButton.setLayoutY(250);
        chosenTeam.setLayoutY(200);
        chosenTeam.setLayoutX(100);
        radioButtonTeam.setLayoutX(100);
        radioButtonTeam.setLayoutY(270);

        ToggleGroup TG = new ToggleGroup();
        radioButton.setToggleGroup(TG);
        radioButtonTeam.setToggleGroup(TG);
        Button back = new Button("Back");
        back.setMinSize(90,40);
        back.setLayoutX(300);
        back.setLayoutY(380);
        Button confirm = new Button("Confirm");
        confirm.setMinSize(90,40);
        confirm.setLayoutX(400);
        confirm.setLayoutY(380);

        if (radioButton.isSelected()){
            FileInputStream MAIN_DATA_BASE_FILE = new FileInputStream("C:\\Users\\Moham\\IdeaProjects\\off\\src\\Competitions Participations.xlsx" );
            XSSFWorkbook MAIN_DATA_BASE_WORK_BOOK = new XSSFWorkbook(MAIN_DATA_BASE_FILE);
            String competitionSheetName;
            boolean competitionIsTeamBased;
            XSSFSheet competitionSheet;
            int countTeam=0;
            XSSFWorkbook ourExcelDB = new XSSFWorkbook(MAIN_DATA_BASE_FILE);
            int numberOfSheets = ourExcelDB.getNumberOfSheets();
            XSSFSheet s1 = MAIN_DATA_BASE_WORK_BOOK.createSheet(name.getText());
        }
        back.setOnAction(ex->{

            detail.close();

        });
        confirm.setOnAction(ex->{

            detail.close();

        });
        pane2.getChildren().addAll(back , confirm);


        pane2.getChildren().addAll(radioButton,radioButtonTeam , chosenTeam , dateLabel,webSiteLabel,namelabel);


    }

    void updateCompetition() throws IOException {

        mainDataBase = readAndSaveCompetitionsFromDataBase();

        Stage detail = new Stage();
        Pane pane2 = new Pane( );
        detail.setScene(new Scene(pane2, 500,450));
        detail.show();
        ComboBox cb = new ComboBox();
        ArrayList names = new ArrayList();
        cb.setMinWidth(400);
        AtomicInteger index = new AtomicInteger() ;
        TextField nameOfComp = new TextField();
        TextField dateOfComp = new TextField();
        TextField websiteOfComp= new TextField();
        nameOfComp.setEditable(false);
        dateOfComp.setEditable(false);
        websiteOfComp.setEditable(false);
        nameOfComp.setLayoutX(100);
        nameOfComp.setLayoutY(105);
        nameOfComp.setMinWidth(200);
        dateOfComp.setLayoutX(100);
        dateOfComp.setLayoutY(150);
        dateOfComp.setMinWidth(200);
        websiteOfComp.setLayoutX(100);
        websiteOfComp.setLayoutY(200);
        websiteOfComp.setMinWidth(200);
        ArrayList dates   = new ArrayList();
        ArrayList websites = new ArrayList();
        TextField isItBasedOnTeam = new TextField();
        isItBasedOnTeam.setLayoutX(200);
        isItBasedOnTeam.setLayoutY(250);

        Label nameLabel = new Label("Name:");
        nameLabel.setFont(new Font(20));
        nameLabel.setLayoutX(20);
        nameLabel.setLayoutY(100);
        Label dateLabel = new Label("Date:");
        dateLabel.setFont(new Font(20));
        dateLabel.setLayoutX(20);
        dateLabel.setLayoutY(145);
        Label webLabel = new Label("Website:");
        webLabel.setFont(new Font(20));
        webLabel.setLayoutX(20);
        webLabel.setLayoutY(195);
        Label teamLabel = new Label("Teams or Indivsuals:");
        teamLabel.setFont(new Font(20));
        teamLabel.setLayoutX(20);
        teamLabel.setLayoutY(245);
        isItBasedOnTeam.setEditable(false);

        for (int i = 0; i< mainDataBase.size(); i++){
            names.add(mainDataBase.get(i).nameOfCompetition);
            dates.add(mainDataBase.get(i).date);
            websites.add(mainDataBase.get(i).link);
        }
        pane2.getChildren().addAll(nameOfComp , dateOfComp , websiteOfComp, isItBasedOnTeam, nameLabel , dateLabel , webLabel , teamLabel);

        System.out.println(index.intValue());
        cb.getItems().addAll(names);


        cb.setOnAction(e->{
            index.set(cb.getSelectionModel().getSelectedIndex());
            if ( mainDataBase.get(index.intValue()).isTeamBased ==true)
            {
                System.out.println(index.intValue());
                nameOfComp.setText(mainDataBase.get(index.intValue()).nameOfCompetition);
                dateOfComp.setText(mainDataBase.get(index.intValue()).date);
                websiteOfComp.setText(mainDataBase.get(index.intValue()).link);
                isItBasedOnTeam.setText("Team Competition");
            }
            else {
                System.out.println(index.intValue());
                nameOfComp.setText(mainDataBase.get(index.intValue()).nameOfCompetition);
                dateOfComp.setText(mainDataBase.get(index.intValue()).date);
                websiteOfComp.setText(mainDataBase.get(index.intValue()).link);
                isItBasedOnTeam.setText("Individual Competition");

            }
        });
        Button showCompititors = new Button("Edit Competitors");
        showCompititors.setLayoutX(200);
        showCompititors.setLayoutY(300);
        pane2.getChildren().addAll(cb , showCompititors);
        showCompititors.setOnAction(e->{
            try {
                editCompetitiors(index.intValue());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }
    void editCompetitiors(int indexOfCompetitionInDB) throws IOException {
        Stage edit = new Stage();
        Pane editPane = new Pane();
        Scene scenePane = new Scene(editPane, 500 ,500);
        edit.setScene(scenePane);
        edit.show();
        mainDataBase = readAndSaveCompetitionsFromDataBase();
        ArrayList name = new ArrayList();
        ArrayList id = new ArrayList();
        ArrayList major = new ArrayList();
        ArrayList rank = new ArrayList();
        ArrayList teamName = new ArrayList();
        ArrayList teamNumber = new ArrayList();
        ArrayList indexInDataBaseList = new ArrayList();

        ArrayList<Competitor> competitorss = mainDataBase.get(indexOfCompetitionInDB).listOfCompetitors;
        if (mainDataBase.get(indexOfCompetitionInDB).isTeamBased){
            for (int i = 0 ; i<competitorss.size(); i++){
                name.add(((TeamMemberCompetitor)(competitorss.get(i))).name );
                id.add(((TeamMemberCompetitor)(competitorss.get(i))).getID());
                major.add(((TeamMemberCompetitor)(competitorss.get(i))).major);
                rank.add(((TeamMemberCompetitor)(competitorss.get(i))).teamRank);
                teamName.add(((TeamMemberCompetitor)(competitorss.get(i))).teamName);
                teamNumber.add(((TeamMemberCompetitor)(competitorss.get(i))).teamNumber);
                ComboBox comboBox = new ComboBox();
                comboBox.getItems().addAll(name);
                int choseen = comboBox.getSelectionModel().getSelectedIndex();
                comboBox.setMinWidth(400);
                TextField nameOfComp = new TextField();
                TextField idOfComp = new TextField();
                TextField rankOfComp= new TextField();
                TextField majorOfComp = new TextField();
                TextField teamNameOfComp = new TextField();
                TextField teamNumberOfComp = new TextField();
                teamNameOfComp.setEditable(true);
                teamNumberOfComp.setEditable(true );

                nameOfComp.setEditable(true);
                idOfComp.setEditable(true);
                rankOfComp.setEditable(true);
                majorOfComp.setEditable(true);
                AtomicInteger r= new AtomicInteger();
                comboBox.setOnAction(e->{
                    r.set(comboBox.getSelectionModel().getSelectedIndex());
                    nameOfComp.setText(name.get(r.intValue()).toString());
                    idOfComp.setText(id.get(r.intValue()).toString());
                    rankOfComp.setText(rank.get(r.intValue()).toString());
                    majorOfComp.setText(major.get(r.intValue()).toString());
                    teamNameOfComp.setText(teamName.get(r.intValue()).toString());
                    teamNumberOfComp.setText(teamNumber.get(r.intValue()).toString());
                });
                teamNameOfComp.setLayoutX(100);
                teamNameOfComp.setLayoutY(300);
                teamNumberOfComp.setLayoutX(100);
                teamNumberOfComp.setLayoutY(350);
                nameOfComp.setLayoutX(100);
                nameOfComp.setLayoutY(105);
                nameOfComp.setMinWidth(200);
                idOfComp.setLayoutX(100);
                idOfComp.setLayoutY(150);
                idOfComp.setMinWidth(200);
                rankOfComp.setLayoutX(100);
                rankOfComp.setLayoutY(200);
                rankOfComp.setMinWidth(200);
                majorOfComp.setLayoutY(250);
                majorOfComp.setLayoutX(100);
                ArrayList dates   = new ArrayList();
                ArrayList websites = new ArrayList();
                TextField isItBasedOnTeam = new TextField();
                isItBasedOnTeam.setLayoutX(200);
                isItBasedOnTeam.setLayoutY(250);

                Label nameLabel = new Label("Name:");
                nameLabel.setFont(new Font(20));
                nameLabel.setLayoutX(20);
                nameLabel.setLayoutY(100);
                Label dateLabel = new Label("ID:");
                dateLabel.setFont(new Font(20));
                dateLabel.setLayoutX(20);
                dateLabel.setLayoutY(145);
                Label webLabel = new Label("Rank:");
                webLabel.setFont(new Font(20));
                webLabel.setLayoutX(20);
                webLabel.setLayoutY(195);
                Label teamLabel = new Label("Major:");
                teamLabel.setFont(new Font(20));
                teamLabel.setLayoutX(20);
                teamLabel.setLayoutY(245);
                Label team = new Label("Team Name");
                team.setFont(new Font(20));

                //
                Label teamN = new Label("Team Number:");
                teamN.setFont(new Font(20));
                team.setLayoutX(20);
                team.setLayoutY(295);
                teamN.setLayoutX(20);
                teamN.setLayoutY(345);
                Button close = new Button("Close");
                Button confirm = new Button("Confirm");

                confirm.setLayoutX(400);
                confirm.setLayoutY(400);
                close.setLayoutX(350);
                close.setLayoutY(400);

                confirm.setOnAction(e->{

                    //Here should update the data by the function
                    edit.close();

                });
                close.setOnAction(e->{
                    edit.close();
                });


                editPane.getChildren().addAll(comboBox , nameOfComp, rankOfComp , idOfComp , majorOfComp , nameLabel , dateLabel , webLabel , teamLabel , confirm, close , teamNameOfComp , teamNumberOfComp , team ,teamN);


            }

        }
        else{
            Button sendCongratsMailBtn = new Button("Send congrats mail");
            sendCongratsMailBtn.setLayoutX(150);
            sendCongratsMailBtn.setLayoutY(400);
            for (int i = 0 ; i<competitorss.size(); i++){
                name.add((competitorss.get(i)).name );
                id.add(competitorss.get(i).getID());
                major.add(competitorss.get(i).major);
                rank.add(competitorss.get(i).rank);
                indexInDataBaseList.add(competitorss.get(i).indexInDataBase);

            }
            ComboBox comboBox = new ComboBox();
            comboBox.getItems().addAll(name);
            int choseen = comboBox.getSelectionModel().getSelectedIndex();
            comboBox.setMinWidth(400);
            TextField nameOfComp = new TextField();
            TextField idOfComp = new TextField();
            TextField rankOfComp= new TextField();
            TextField majorOfComp = new TextField();
            nameOfComp.setEditable(true);
            idOfComp.setEditable(true);
            rankOfComp.setEditable(true);
            majorOfComp.setEditable(true);
            AtomicInteger r= new AtomicInteger();
            comboBox.setOnAction(e->{
                r.set(comboBox.getSelectionModel().getSelectedIndex());
                nameOfComp.setText(name.get(r.intValue()).toString());
                idOfComp.setText(id.get(r.intValue()).toString());
                rankOfComp.setText(rank.get(r.intValue()).toString());
                majorOfComp.setText(major.get(r.intValue()).toString());
            });

            nameOfComp.setLayoutX(100);
            nameOfComp.setLayoutY(105);
            nameOfComp.setMinWidth(200);
            idOfComp.setLayoutX(100);
            idOfComp.setLayoutY(150);
            idOfComp.setMinWidth(200);
            rankOfComp.setLayoutX(100);
            rankOfComp.setLayoutY(200);
            rankOfComp.setMinWidth(200);
            majorOfComp.setLayoutY(250);
            majorOfComp.setLayoutX(100);
            ArrayList dates   = new ArrayList();
            ArrayList websites = new ArrayList();
            TextField isItBasedOnTeam = new TextField();
            isItBasedOnTeam.setLayoutX(200);
            isItBasedOnTeam.setLayoutY(250);

            Label nameLabel = new Label("Name:");
            nameLabel.setFont(new Font(20));
            nameLabel.setLayoutX(20);
            nameLabel.setLayoutY(100);
            Label dateLabel = new Label("ID:");
            dateLabel.setFont(new Font(20));
            dateLabel.setLayoutX(20);
            dateLabel.setLayoutY(145);
            Label webLabel = new Label("Rank:");
            webLabel.setFont(new Font(20));
            webLabel.setLayoutX(20);
            webLabel.setLayoutY(195);
            Label teamLabel = new Label("Major:");
            teamLabel.setFont(new Font(20));
            teamLabel.setLayoutX(20);
            teamLabel.setLayoutY(245);
            Button close = new Button("Close");
            Button confirm = new Button("Confirm");
            confirm.setLayoutX(400);
            confirm.setLayoutY(400);
            close.setLayoutX(350);
            close.setLayoutY(400);
            sendCongratsMailBtn.setOnAction(e->{
                if (!rank.get(r.intValue()).toString().equals("-")) {
                    Desktop desktop;
                    if (Desktop.isDesktopSupported()
                            && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                        String mailtoID = id.get(r.intValue()).toString();
                        String mailtoCompetitionName = mainDataBase.get(indexOfCompetitionInDB).nameOfCompetition;
                        String mailtoCompetitorName = name.get(r.intValue()).toString();
                        String mailtoFormat = "mailto:s" + mailtoID + "@kfupm.edu.sa?subject=Conguratulations%20on%20your%20achievement%20in%20" + mailtoCompetitionName + "&body=Dear%20" + mailtoCompetitorName.split(" ")[0] + "%20" + mailtoCompetitorName.split(" ")[1] + "%2C%0D%0A%0D%0ACongratulations%20on%20your%20achievement%20in%20" + mailtoCompetitionName + ".%20This%20achievement%20is%20deeply%20appreciated%20by%20the%20university%20and%20we%20will%20announce%20it%20in%20the%20appropriate%20media.%0D%0A%0D%0AIn%20case%20you%20have%20Photos%20you%20want%20to%20share%20with%20the%20news%20post%2C%20reply%20to%20this%20email%20with%20the%20photos.%0D%0A%0D%0ARegards%20and%20Congrats%2C%0D%0AKFUPM%20News%20Team";
                        URI mailto = null;
                        try {
                            mailto = new URI(mailtoFormat);
                        } catch (URISyntaxException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            desktop.mail(mailto);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
                    }
                }
            });
            confirm.setOnAction(e->{
                //Here should update the data by the function
                String editedName = nameOfComp.getText();
                String editedID = idOfComp.getText();
                String editedRank = rankOfComp.getText();
                String editedMajor = majorOfComp.getText();
                int indexInDataBase = Integer.parseInt(indexInDataBaseList.get(r.intValue()).toString());  //TODO
                Competition competitionToBeEdited = mainDataBase.get(indexOfCompetitionInDB);
                competitionToBeEdited.updateCompetitorDetails(indexInDataBase, new String[]{"ID;"+editedID, "Name;"+editedName, "Major;"+editedMajor, "Rank;"+editedRank});
                try {
                    competitionToBeEdited.updateCompetitorsDataInExcelFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                edit.close();

            });
            close.setOnAction(e->{
                edit.close();
            });


            editPane.getChildren().addAll(comboBox , nameOfComp, rankOfComp , idOfComp , majorOfComp , nameLabel , dateLabel , webLabel , teamLabel , confirm, close, sendCongratsMailBtn);


        }






    }

    public void start(Stage stage) throws Exception {
        home = stage;


        XSSFSheet sheet1 = h1.getSheetAt(0);
        System.out.println();
        String name;
        Pane pane = new Pane();
        Scene scene = new Scene(pane , 500,430);
        scene1 = scene;
        stage.setTitle("SWE-206 Group 2");
        stage.setScene(scene);
        Button show = new Button("Show competetions");
        show.setMinSize(140,40);
        show.setLayoutX(100);
        show.setLayoutY(100);
        show.setOnAction(e->{
            try {
                show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        //
        Button add = new Button("add new competetions");
        add.setMinSize(140,40);
        add.setLayoutX(250);
        add.setLayoutY(100);
        add.setOnAction(e->{
            try {
                add();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //
        Button updateWinners = new Button("Update competetions \nwinners");
        updateWinners.setMinSize(140,40);
        updateWinners.setMaxSize(140,80);
        updateWinners.setLayoutX(100);
        updateWinners.setLayoutY(150);
        updateWinners.setOnAction(e->{
            try {
                updateCompetition();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        });
        //
        Button updateDetails = new Button("Update competetions \ndetails");
        updateDetails.setMinSize(140,40);
        updateDetails.setMaxSize(140,80);
        updateDetails.setLayoutX(250);
        updateDetails.setLayoutY(150);
        updateDetails.setOnAction(e->{
            stage.setTitle("Update competitions details");
            Pane paneAdd = new Pane();
            Scene sceneAdd = new Scene(paneAdd,500,430);
            home.setScene(sceneAdd);
            Button back = new Button("Back");
            back.setMinSize(90,40);

            back.setLayoutX(300);
            back.setLayoutY(380);
            Button confirm = new Button("Confirm");
            confirm.setMinSize(90,40);
            confirm.setLayoutX(400);
            confirm.setLayoutY(380);
            back.setOnAction(ex->{

                home.setScene(scene);
                stage.setTitle("SWE-206 Group 2");

            });
            confirm.setOnAction(ex->{

                home.setScene(scene);
                stage.setTitle("SWE-206 Group 2");

            });
            paneAdd.getChildren().addAll(back , confirm);

        });
        //
        Button addST = new Button("add new Student or Team to Competetion");
        addST.setMinSize(290,40);
        addST.setLayoutX(100);
        addST.setLayoutY(250);
        addST.setOnAction(e->{

            try {
                addNew();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        });
        //
        Button sendEmailsToWinners = new Button("Send Emails to \nWinners");
        sendEmailsToWinners.setMinSize(140,40);
        sendEmailsToWinners.setMaxSize(140,80);
        sendEmailsToWinners.setLayoutX(100);
        sendEmailsToWinners.setLayoutY(200);
        sendEmailsToWinners.setOnAction(e->{
            stage.setTitle("Send emails to Winners");
            Pane paneAdd = new Pane();
            Scene sceneAdd = new Scene(paneAdd,500,430);
            home.setScene(sceneAdd);
            Button back = new Button("Back");
            back.setMinSize(90,40);
            back.setLayoutX(300);
            back.setLayoutY(380);
            Button confirm = new Button("Confirm");
            confirm.setMinSize(90,40);
            confirm.setLayoutX(400);
            confirm.setLayoutY(380);
            back.setOnAction(ex->{

                home.setScene(scene);
                stage.setTitle("SWE-206 Group 2");

            });
            confirm.setOnAction(ex->{

                home.setScene(scene);
                stage.setTitle("SWE-206 Group 2");

            });
            paneAdd.getChildren().addAll(back , confirm);

        });
        //
        Button showAllCompetitors = new Button("Show All Competitors");
        showAllCompetitors.setMinSize(140,40);
        showAllCompetitors.setMaxSize(140,80);
        showAllCompetitors.setLayoutX(250);
        showAllCompetitors.setLayoutY(200);
        showAllCompetitors.setOnAction(e->{
            stage.setTitle("Show all competitions");
            Pane paneAdd = new Pane();
            Scene sceneAdd = new Scene(paneAdd,500,430);
            home.setScene(sceneAdd);
            Button back = new Button("Back");
            back.setMinSize(90,40);
            back.setLayoutX(300);
            back.setLayoutY(380);
            Button confirm = new Button("Confirm");
            confirm.setMinSize(90,40);
            confirm.setLayoutX(400);
            confirm.setLayoutY(380);
            back.setOnAction(ex->{

                home.setScene(scene);
                stage.setTitle("SWE-206 Group 2");

            });
            confirm.setOnAction(ex->{

                home.setScene(scene);
                stage.setTitle("SWE-206 Group 2");

            });
            paneAdd.getChildren().addAll(back , confirm);

        });

        pane.getChildren().addAll(show,add , addST ,updateDetails , updateWinners , sendEmailsToWinners, showAllCompetitors);

        stage.show();
    }
    void addNew() throws IOException {

        mainDataBase = readAndSaveCompetitionsFromDataBase();

        Stage detail = new Stage();
        Pane pane2 = new Pane( );
        detail.setScene(new Scene(pane2, 500,450));
        detail.show();
        ComboBox cb = new ComboBox();
        ArrayList names = new ArrayList();
        cb.setMinWidth(400);
        AtomicInteger index = new AtomicInteger() ;
        TextField nameOfComp = new TextField();
        TextField dateOfComp = new TextField();
        TextField websiteOfComp= new TextField();
        nameOfComp.setEditable(false);
        dateOfComp.setEditable(false);
        websiteOfComp.setEditable(false);
        nameOfComp.setLayoutX(100);
        nameOfComp.setLayoutY(105);
        nameOfComp.setMinWidth(200);
        dateOfComp.setLayoutX(100);
        dateOfComp.setLayoutY(150);
        dateOfComp.setMinWidth(200);
        websiteOfComp.setLayoutX(100);
        websiteOfComp.setLayoutY(200);
        websiteOfComp.setMinWidth(200);
        ArrayList dates   = new ArrayList();
        ArrayList websites = new ArrayList();
        TextField isItBasedOnTeam = new TextField();
        isItBasedOnTeam.setLayoutX(200);
        isItBasedOnTeam.setLayoutY(250);

        Label nameLabel = new Label("Name:");
        nameLabel.setFont(new Font(20));
        nameLabel.setLayoutX(20);
        nameLabel.setLayoutY(100);
        Label dateLabel = new Label("Date:");
        dateLabel.setFont(new Font(20));
        dateLabel.setLayoutX(20);
        dateLabel.setLayoutY(145);
        Label webLabel = new Label("Website:");
        webLabel.setFont(new Font(20));
        webLabel.setLayoutX(20);
        webLabel.setLayoutY(195);
        Label teamLabel = new Label("Teams or Indivsuals:");
        teamLabel.setFont(new Font(20));
        teamLabel.setLayoutX(20);
        teamLabel.setLayoutY(245);
        isItBasedOnTeam.setEditable(false);

        for (int i = 0; i< mainDataBase.size(); i++){
            names.add(mainDataBase.get(i).nameOfCompetition);
            dates.add(mainDataBase.get(i).date);
            websites.add(mainDataBase.get(i).link);
        }
        pane2.getChildren().addAll(nameOfComp , dateOfComp , websiteOfComp, isItBasedOnTeam, nameLabel , dateLabel , webLabel , teamLabel);

        System.out.println(index.intValue());
        cb.getItems().addAll(names);
        TextField getName = new TextField();
        TextField getRank = new TextField();
        TextField getID = new TextField();
        TextField getMajor = new TextField();
        //
        TextField getNameTeam = new TextField();
        TextField getRankTeam = new TextField();
        TextField getIDTeam = new TextField();
        TextField getMajorTeam = new TextField();
        TextField getTeamName = new TextField();
        TextField getTeamNumber = new TextField();
        Stage add = new Stage();
        Pane pane = new Pane();
        Pane paneTeam = new Pane();
        Scene scene = new Scene(pane, 500 ,500);
        Scene sceneTeam = new Scene(paneTeam, 500,500);
        add.setTitle("Add new Competitor");
        //
        Label nameLabelS = new Label("Name");
        nameLabelS.setFont(new Font(20));
        Label IDLabelS = new Label("ID");
        IDLabelS.setFont(new Font(20));
        Label majorLabelS = new Label("Major");
        majorLabelS.setFont(new Font(20));
        Label rankLabelS = new Label("Rank");
        rankLabelS.setFont(new Font(20));
        nameLabelS.setLayoutX(20);
        nameLabelS.setLayoutY(95);
        IDLabelS.setLayoutX(20);
        IDLabelS.setLayoutY(145);
        majorLabelS.setLayoutX(20);
        majorLabelS.setLayoutY(195);
        rankLabelS.setLayoutX(20);
        rankLabelS.setLayoutY(245);
        //
        Label nameLabelT = new Label("Name");
        nameLabelT.setFont(new Font(20));
        Label IDLabelT = new Label("ID");
        IDLabelT.setFont(new Font(20));
        Label majorLabelT = new Label("Major");
        majorLabelT.setFont(new Font(20));
        Label rankLabelT = new Label("Team Rank");
        rankLabelT.setFont(new Font(20));
        Label nameTeamLabelT = new Label("Team name");
        nameTeamLabelT.setFont(new Font(20));
        Label numberLabelT = new Label("Team number");
        numberLabelT.setFont(new Font(20));
        nameLabelT.setLayoutX(20);
        nameLabelT.setLayoutY(95);
        IDLabelT.setLayoutX(20);
        IDLabelT.setLayoutY(145);
        majorLabelT.setLayoutX(20);
        majorLabelT.setLayoutY(195);
        rankLabelT.setLayoutX(20);
        rankLabelT.setLayoutY(245);
        nameTeamLabelT.setLayoutX(20);
        nameTeamLabelT.setLayoutY(295);
        numberLabelT.setLayoutX(20);
        numberLabelT.setLayoutY(345);





        getName.setLayoutX(100);
        getName.setLayoutY(100);
        getNameTeam.setLayoutX(100);
        getNameTeam.setLayoutY(100);
        getID.setLayoutX(100);
        getID.setLayoutY(150);
        getIDTeam.setLayoutX(100);
        getIDTeam.setLayoutY(150);
        getMajor.setLayoutX(100);
        getMajor.setLayoutY(200);
        getMajorTeam.setLayoutX(100);
        getMajorTeam.setLayoutY(200);
        getRank.setLayoutX(100);
        getRank.setLayoutY(250);
        getRankTeam.setLayoutX(100);
        getRankTeam.setLayoutY(250);
        getTeamName.setLayoutY(300);
        getTeamName.setLayoutX(100);
        getTeamNumber.setLayoutX(100);
        getTeamNumber.setLayoutY(350);
        //
        Button close = new Button("Close");
        Button addBtn = new Button("Add");
        close.setLayoutX(300);
        close.setLayoutY(400);
        addBtn.setLayoutX(350);
        addBtn.setLayoutY(400);
        Button closeTM = new Button("Close");
        Button addBtnTM = new Button("Add");
        closeTM.setLayoutX(300);
        closeTM.setLayoutY(400);
        addBtnTM.setLayoutX(350);
        addBtnTM.setLayoutY(400);
        paneTeam.getChildren().addAll(getNameTeam,getIDTeam,getMajorTeam,getTeamName, getTeamNumber, getRankTeam , closeTM,addBtnTM , nameLabelT , IDLabelT , majorLabelT , rankLabelT , numberLabelT, nameTeamLabelT);
        pane.getChildren().addAll(getName,getID,getMajor, getRank , close,addBtn , majorLabelS , nameLabelS  ,rankLabelS , IDLabelS);




        cb.setOnAction(e->{
            index.set(cb.getSelectionModel().getSelectedIndex());
            if (mainDataBase.get(index.intValue()).isTeamBased ==true){
                nameOfComp.setText(mainDataBase.get(index.intValue()).nameOfCompetition);
                dateOfComp.setText(mainDataBase.get(index.intValue()).date);
                websiteOfComp.setText(mainDataBase.get(index.intValue()).link);
                isItBasedOnTeam.setText("Team Competition");}
            else{
                nameOfComp.setText(mainDataBase.get(index.intValue()).nameOfCompetition);
                dateOfComp.setText(mainDataBase.get(index.intValue()).date);
                websiteOfComp.setText(mainDataBase.get(index.intValue()).link);
                isItBasedOnTeam.setText("Indevisual Competition");
            }


        });

        Button showCompititors = new Button("Add new Competitor");
        showCompititors.setLayoutX(200);
        showCompititors.setLayoutY(300);
        pane2.getChildren().addAll(cb , showCompititors);
        showCompititors.setOnAction(e->{
            if ( mainDataBase.get(index.intValue()).isTeamBased ==true)
            {
                add.setTitle("Add new Competitior to "+(mainDataBase.get(index.intValue()).nameOfCompetition));

                add.setScene(sceneTeam);
                add.show();


            }
            else {
                add.setTitle("Add new Competitior to "+(mainDataBase.get(index.intValue()).nameOfCompetition));
                add.setScene(scene);
                add.show();
            }
        });

    }
}