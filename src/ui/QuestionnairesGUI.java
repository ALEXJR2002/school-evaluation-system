package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Activity;
import model.Course;
import model.EvaluationSystem;
import model.Questionnaire;

import java.io.File;
import java.time.LocalDate;

public class QuestionnairesGUI {

    private EvaluationSystem evaluationSystem;

    @FXML
    private TableView<Questionnaire> questionnairesTableView;

    @FXML
    private TableColumn<Questionnaire, String> tcQuestionnairesName;

    @FXML
    private TableColumn<Questionnaire, String> tcQuestionnairesPercentage;

    @FXML
    private TableColumn<Questionnaire, String> tcInitialDate;

    @FXML
    private TableColumn<Questionnaire, String> tcQuestionnairesAttempts;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField percentageTextField;

    @FXML
    private TextArea questionnaireContentTextArea;

    @FXML
    private Spinner<Integer> attemptsSpinner;

    @FXML
    private DatePicker initialDatePicker;

    @FXML
    private ComboBox<Course> coursesComboBox;

    public QuestionnairesGUI(EvaluationSystem evaluationSystem) {
        this.evaluationSystem = evaluationSystem;
    }

    private void initializeQuestionnaireTableView (Course course) {

        ObservableList<Questionnaire> list = FXCollections.observableArrayList(course.getQuestionnaires());
        questionnairesTableView.setItems(list);
        tcQuestionnairesName.setCellValueFactory(new PropertyValueFactory<Questionnaire, String>("topic"));
        tcQuestionnairesPercentage.setCellValueFactory(new PropertyValueFactory<Questionnaire, String>("percentage"));
        tcInitialDate.setCellValueFactory(new PropertyValueFactory<Questionnaire, String>("assignmentDate"));
        tcQuestionnairesAttempts.setCellValueFactory(new PropertyValueFactory<Questionnaire, String>("attempts"));
        questionnairesTableView.refresh();
    }


    @FXML
    void cleanList(ActionEvent event) {

    }

    @FXML
    void createNewQuestionnaire(ActionEvent event) {

    }

    @FXML
    void deleteQuestionnaire(ActionEvent event) {

    }

    @FXML
    void exportQuestionnairesList(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("Reporte-Cuestionarios.csv");

        //Show save file dialog

        File file = fileChooser.showSaveDialog((Stage)((Node)event.getSource()).getScene().getWindow());

        if (file != null) {

        }
    }

    @FXML
    void importQuestionnairesList(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());

        if (file != null) {

        }
    }

    @FXML
    void showQuestionnaireInfo(MouseEvent event) {
        Questionnaire questionnaire = questionnairesTableView.getSelectionModel().getSelectedItem();
        if (questionnaire != null) {
            //Set Topic
            nameTextField.setDisable(false);
            nameTextField.setText(questionnaire.getTopic());
            //Set percentage
            percentageTextField.setDisable(false);
            percentageTextField.setText(questionnaire.getPercentage() + "%");
            //Set initial date
            initialDatePicker.setDisable(false);
            initialDatePicker.setValue(questionnaire.getAssignmentDate());
            //Set attempts
            attemptsSpinner.setDisable(false);
            attemptsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, questionnaire.getAttempts()));
            //Set content
            questionnaireContentTextArea.setDisable(false);
            questionnaireContentTextArea.setText(questionnaire.getContent());
        }else {
            //Disable the objects
            nameTextField.setDisable(true);
            percentageTextField.setDisable(true);
            initialDatePicker.setDisable(true);
            attemptsSpinner.setDisable(true);
            questionnaireContentTextArea.setDisable(true);

        }
    }

    @FXML
    void showQuestionnaires(ActionEvent event) {
        Course course = coursesComboBox.getSelectionModel().getSelectedItem();
        initializeQuestionnaireTableView(course);
    }
}
