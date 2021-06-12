package ui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Course;
import model.EvaluationSystem;
import model.Exam;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ManagerGUI {

    private EvaluationSystem evaluationSystem;
    private LoginGUI loginGUI;
    private AssessmentsGUI assessmentsGUI;
    private QuestionnairesGUI questionnairesGUI;
    private ExamsGUI examsGUI;
    private CoursesGUI coursesGUI;
    private ArrayList<Course> courses;

    @FXML
    private Label timeLabel;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ComboBox<Course> coursesComboBox;

    public ManagerGUI(EvaluationSystem evaluationSystem, LoginGUI loginGUI) {
        this.evaluationSystem = evaluationSystem;
        this.loginGUI = loginGUI;
        assessmentsGUI = new AssessmentsGUI(evaluationSystem);
        questionnairesGUI = new QuestionnairesGUI(evaluationSystem);
        examsGUI = new ExamsGUI(evaluationSystem);
        coursesGUI = new CoursesGUI(evaluationSystem);
    }

    private void initializeComboBox () {
        courses = evaluationSystem.getLogged().getCourses();
        ObservableList<Course> list = FXCollections.observableArrayList(courses);
        System.out.println("Lista: " + list);
        if (list != null) {
            coursesComboBox.setItems(list);
        }
    }

    @FXML
    void createNewCourse(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Curso");
        dialog.setHeaderText("Crear Nuevo Curso");
        dialog.setContentText("Por favor, ingresa el nombre del curso a crear:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            evaluationSystem.getLogged().addCourse(result.get());
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));

        fxmlLoader.setController(loginGUI);
        Parent LoginWindow = null;
        try {
            LoginWindow = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene loginScene = new Scene (LoginWindow);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    @FXML
    void showAssessmentsPane(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/assessment-pane.fxml"));

        fxmlLoader.setController(assessmentsGUI);
        Parent assessmentPane = null;
        try {
            assessmentPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(assessmentPane);
        AnchorPane.setTopAnchor(assessmentPane, 0.0);
        AnchorPane.setBottomAnchor(assessmentPane, 0.0);
        AnchorPane.setLeftAnchor(assessmentPane, 0.0);
        AnchorPane.setRightAnchor(assessmentPane, 0.0);
    }

    @FXML
    void showCoursesPane(ActionEvent event) {
        Course selectedCourse = coursesComboBox.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/course-pane.fxml"));

        fxmlLoader.setController(coursesGUI);
        Parent coursesPane = null;
        try {
            coursesPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(coursesPane);
        AnchorPane.setTopAnchor(coursesPane, 0.0);
        AnchorPane.setBottomAnchor(coursesPane, 0.0);
        AnchorPane.setLeftAnchor(coursesPane, 0.0);
        AnchorPane.setRightAnchor(coursesPane, 0.0);

        if(selectedCourse != null)
            coursesGUI.initialize(selectedCourse);

    }

    @FXML
    void showExamsPane(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/exam-pane.fxml"));

        fxmlLoader.setController(examsGUI);
        Parent examPane = null;
        try {
            examPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(examPane);
        AnchorPane.setTopAnchor(examPane, 0.0);
        AnchorPane.setBottomAnchor(examPane, 0.0);
        AnchorPane.setLeftAnchor(examPane, 0.0);
        AnchorPane.setRightAnchor(examPane, 0.0);
    }

    @FXML
    void showQuestionnairesPane(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/questionnaire-pane.fxml"));

        fxmlLoader.setController(questionnairesGUI);
        Parent questionnairePane = null;
        try {
            questionnairePane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(questionnairePane);
        AnchorPane.setTopAnchor(questionnairePane, 0.0);
        AnchorPane.setBottomAnchor(questionnairePane, 0.0);
        AnchorPane.setLeftAnchor(questionnairePane, 0.0);
        AnchorPane.setRightAnchor(questionnairePane, 0.0);
    }

    public void timer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Date date = new Date ();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
                Platform.runLater(() -> timeLabel.setText(sdf.format(date)));
            }
        }, 0, 1000);
    }

    @FXML
    void updateCoursesComboBox(MouseEvent event) {
        initializeComboBox();
    }

}
