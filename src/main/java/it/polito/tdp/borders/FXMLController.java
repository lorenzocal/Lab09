
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Model;
import it.polito.tdp.borders.model.Country;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    private ComboBox<Country> countriesCmbBox;
    
    @FXML
    private Label labelStato;
    
    @FXML
    private Button statiRaggiungibili;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	try {
    		Integer anno = Integer.parseInt(this.txtAnno.getText());
    		if (anno < 1816 || anno > 2016) {
    			this.txtResult.appendText("L'anno inserito non è valido. Inserire un anno dal 1816 al 2016.\n");
    		}
    		else {
    			
    			this.model.creaGrafo(anno);
    			
    			Integer nVertices = this.model.getGraph().vertexSet().size();
    			Integer nEdges = this.model.getGraph().edgeSet().size();
    			if (nVertices > 0) {
    				this.txtResult.appendText("Il grafo è stato correttamente creato.\n" +
    										  "Numero di vertici: " + nVertices + "\n" + 
    										  "Numero di archi: " + nEdges + "\n\n");
    			}
    			
    			for (Country country : this.model.getGraph().vertexSet()) {
    				this.txtResult.appendText(country.toString() + ": " + this.model.getGraph().degreeOf(country) +" confinanti\n");
    			}
    			
    			this.txtResult.appendText("\nNumero di componenti connesse del grafo: " + this.model.getNumberOfConnectedSets() + "\n\n");
    		
    			this.countriesCmbBox.setDisable(false);
    			this.statiRaggiungibili.setDisable(false);
    			this.labelStato.setDisable(false);
    			this.countriesCmbBox.getItems().addAll(this.model.getGraph().vertexSet());
    		}
    	} catch (NumberFormatException nfe) {
    		this.txtResult.appendText("La data inserita non è valida. Solo caratteri numerici sono ammessi.\n");
    	}
 
    }
    
    @FXML
    void handleStatiRaggiungibili(ActionEvent event) {
    	Country country = this.countriesCmbBox.getValue();
    	this.txtResult.appendText("Stati raggiungibili via terra partendo da " + country.toString() + ":\n");
    	for (Country connectedCountry : this.model.connectedSetOf(country)) {
    		this.txtResult.appendText(connectedCountry.toString() + "\n");
    	}
    }
    
    @FXML
    void handlePulisci(ActionEvent event) {
    	this.txtResult.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert countriesCmbBox != null : "fx:id=\"countriesCmbBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert statiRaggiungibili != null : "fx:id=\"statiRaggiungibili\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelStato != null : "fx:id=\"labelStato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
