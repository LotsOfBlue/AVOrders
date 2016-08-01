package avorders;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import avorders.comparators.*;

/**
 * Controller for the main window.
 * Displays the list of Orders and an additional pane
 * for viewing/editing/creating orders.
 * @author Johan Blomberg
 */
public class WindowController {
	
	@FXML private Button newButton;
	@FXML private Button editButton;
	@FXML private Button deleteButton;
	@FXML private AnchorPane infoPane;
	@FXML private ChoiceBox<String> sortModeBox;
	@FXML private ListView<Order> listView;
	@FXML private ImageView image;

	private ArrayList<Comparator<Order>> comparators = new ArrayList<>();

	private Order lastSelected;

	/**
	 * Set the window to its initial state
	 */
	@FXML
	private void initialize() {
		image.setImage(new Image(this.getClass().getResourceAsStream("AVlogo.png")));

		deleteButton.setDisable(true);
		editButton.setDisable(true);

		//Add strings representing the sorting modes to the choice box
		sortModeBox.getItems().addAll("ID (stigande)", "ID (fallande)", "Kund");
		sortModeBox.setValue(sortModeBox.getItems().get(0));

		//Call refreshList() whenever a new sorting mode is selected
		sortModeBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			refreshList();
		});

		//Store all available comparators for easy access
		comparators.add(new IDSort());
		comparators.add(new ReverseIDSort());
		comparators.add(new CustomerSort());

		refreshList();
	}

	/**
	 * Empties the listview and adds all orders anew, while
	 * rearranging the list as necessary and saving all orders.
	 */
	private void refreshList() {
		//Sorts in the regular way before saving to file
		OrderUtils.getOrders().sort(new IDSort());
		OrderUtils.saveToFile();

		//Empty the pane
		listView.getItems().removeAll(OrderUtils.getOrders());

		//Rearrange the list of orders if the sorting mode is not the default
		int i = sortModeBox.getSelectionModel().getSelectedIndex();
		OrderUtils.getOrders().sort(comparators.get(i));

		//Fill the pane with the new orders
		listView.getItems().addAll(OrderUtils.getOrders());
	}

	/**
	 * Store the Order that was last clicked, and display it
	 */
	public void getSelectedOrder() {
		lastSelected = listView.getSelectionModel().getSelectedItem();
		displayOrder();
	}

	/**
	 * Prepare the window for displaying the last selected order
	 */
	private void displayOrder() {
		//Remove anything currently in infoPane
		while (infoPane.getChildren().size() > 0) {
			infoPane.getChildren().remove(0);
		}

		if (lastSelected != null) {
			//Enable the edit and delete buttons
			editButton.setDisable(false);
			deleteButton.setDisable(false);

			//Place the correct pane in infoPane
			try {
				FXMLLoader loader = new FXMLLoader();
				AnchorPane displayOrderPane = loader.load(getClass().getResource("displayOrder.fxml").openStream());
				displayOrderPane.setPrefWidth(infoPane.getWidth());
				displayOrderPane.setPrefHeight(infoPane.getHeight());
				infoPane.getChildren().add(displayOrderPane);

				//Display the order's info
				DisplayOrderController controller = loader.getController();
				controller.populateLabels(lastSelected);
			}
			catch (IOException e) {
				System.out.println("Kunde inte ladda displayOrder.fxml");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Prepare the window for creating a new order
	 * @param event
	 */
	public void newOrder(ActionEvent event) {
		//Remove anything currently in infoPane
		while (infoPane.getChildren().size() > 0) {
			infoPane.getChildren().remove(0);
		}

		//Disable the buttons and the listview
		newButton.setDisable(true);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		listView.setDisable(true);

		//Place the correct pane in infoPane
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane newOrderPane = loader.load(getClass().getResource("newOrder.fxml").openStream());
			newOrderPane.setPrefWidth(infoPane.getWidth());
			newOrderPane.setPrefHeight(infoPane.getHeight());
			infoPane.getChildren().add(newOrderPane);

			//Gives input focus to the name field
			NewOrderController controller = loader.getController();
			controller.getNameField().requestFocus();
		}
		catch (IOException e) {
			System.out.println("Kunde inte ladda newOrder.fxml");
			e.printStackTrace();
		}
	}

	/**
	 * Prepare the window for editing an order
	 * @param event
	 */
	public void editOrder(ActionEvent event) {
		//Remove anything currently in infoPane
		while (infoPane.getChildren().size() > 0) {
			infoPane.getChildren().remove(0);
		}

		//Disable the buttons and listview
		deleteButton.setDisable(true);
		editButton.setDisable(true);
		newButton.setDisable(true);
		listView.setDisable(true);

		//Place the correct pane in infoPane
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane editOrderPane = loader.load(getClass().getResource("editOrder.fxml").openStream());
			editOrderPane.setPrefWidth(infoPane.getWidth());
			editOrderPane.setPrefHeight(infoPane.getHeight());
			infoPane.getChildren().add(editOrderPane);

			//Display the order's info
			EditOrderController controller = loader.getController();
			controller.populateFields(lastSelected);
		}
		catch (IOException e) {
			System.out.println("Kunde inte ladda editOrder.fxml");
			e.printStackTrace();
		}
	}

	/**
	 * Delete the currently selected Order, both from the visible
	 * listview and internally. Asks the user for confirmation via a dialog box.
	 * @param event
	 */
	public void deleteOrder(ActionEvent event) {
		//Asks the user for confirmation
		Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
		confirmDelete.setTitle(null);
		confirmDelete.setHeaderText("Vill du ta bort beställningen?");
		confirmDelete.setContentText(lastSelected.toString());
		Optional<ButtonType> choice = confirmDelete.showAndWait();

		//If the user confirmed the deletion, delete the order
		if (choice.get() == ButtonType.OK) {
			OrderUtils.getOrders().remove(lastSelected);
			listView.getItems().remove(lastSelected);

			//Remove anything currently in infoPane
			while (infoPane.getChildren().size() > 0) {
				infoPane.getChildren().remove(0);
			}

			//Disable buttons
			deleteButton.setDisable(true);
			editButton.setDisable(true);

			refreshList();
		}
		//If the user chose to cancel, go back to displaying the order
		else {
			displayOrder();
		}
	}

	/**
	 * Cancel the creation of a new order,
	 * and reset the buttons to their default.
	 */
	void exitNewOrder() {
		//Remove anything currently in infoPane
		while (infoPane.getChildren().size() > 0) {
			infoPane.getChildren().remove(0);
		}

		//Enable the "new" button
		newButton.setDisable(false);
		//Enable the listview
		listView.setDisable(false);

		refreshList();
	}

	/**
	 * Cancel/finish editing an order,
	 * reset buttons to default and display the order again.
	 */
	void exitEditOrder() {
		//Remove anything currently in infoPane
		while (infoPane.getChildren().size() > 0) {
			infoPane.getChildren().remove(0);
		}

		//Enable the "new" button and the listview
		newButton.setDisable(false);
		listView.setDisable(false);

		refreshList();
		displayOrder();
	}
}