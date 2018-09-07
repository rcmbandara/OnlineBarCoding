package ob.app.util;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Tooltip;

import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

public class ComboBoxAutoCompleteTest extends Application {

	private static final String[] LISTA = { "Abacate", "Abacaxi", "Ameixa", "Amora", "Araticum", "Atemoia", "Avocado",

			"Banana prata", "Caju", "Cana descascada", "Caqui", "Caqui Fuyu", "Carambola", "Cereja", "Coco verde",

			"Figo", "Figo da �ndia", "Framboesa", "Goiaba", "Graviola", "Jabuticaba", "Jambo", "Jambo rosa", "Jambol�o",

			"Kino (Kiwano)", "Kiwi", "Laranja Bahia", "Laranja para suco", "Laranja seleta", "Laranja serra d��gua",

			"Laranjinha kinkan", "Lichia", "Lima da p�rsia", "Lim�o galego", "Lim�o Taiti", "Ma�� argentina",

			"Ma�� Fuji", "Ma�� gala", "Ma�� verde", "Mam�o formosa", "Mam�o Hava�", "Manga espada", "Manga Haden",

			"Manga Palmer", "Manga Tommy", "Manga Ub�", "Mangostim", "Maracuj� doce", "Maracuj� para suco", "Melancia",

			"Melancia sem semente", "Mel�o", "Mel�o Net", "Mel�o Orange", "Mel�o pele de sapo", "Mel�o redinha",

			"Mexerica carioca", "Mexerica Murcote", "Mexerica Ponkan", "Mirtilo", "Morango", "Nectarina",

			"N�spera ou ameixa amarela", "Noni", "Pera asi�tica", "Pera portuguesa", "P�ssego", "Physalis", "Pinha",

			"Pitaia", "Rom�", "Tamarilo", "Tamarindo", "Uva red globe", "Uva rosada", "Uva Rubi", "Uva sem semente",

			"Abobora moranga", "Abobrinha italiana", "Abobrinha menina", "Alho", "Alho descascado",

			"Batata baroa ou cenoura amarela", "Batata bolinha", "Batata doce", "Batata inglesa", "Batata yacon",

			"Berinjela", "Beterraba", "Cebola bolinha", "Cebola comum", "Cebola roxa", "Cenoura", "Cenoura baby",

			"Couve flor", "Ervilha", "Fava", "Gengibre", "Inhame", "Jil�", "Massa de alho", "Maxixe", "Milho",

			"Pimenta biquinho fresca", "Pimenta de bode fresca", "Piment�o amarelo", "Piment�o verde",

			"Piment�o vermelho", "Quiabo", "Repolho", "Repolho roxo", "Tomate cereja", "Tomate salada",

			"Tomate sem acidez", "Tomate uva", "Vagem", "Agri�o", "Alcachofra", "Alface", "Alface americana",

			"Almeir�o", "Br�colis", "Broto de alfafa", "Broto de bambu", "Broto de feij�o", "Cebolinha", "Coentro",

			"Couve", "Espinafre", "Hortel�", "Mostarda", "R�cula", "Salsa", "Ovos brancos", "Ovos de codorna",

			"Ovos vermelhos" };

	public static void main(String[] args) {

		launch();

	}

	@Override

	public void start(Stage stage) throws Exception {

		ComboBox<String> cmb = new ComboBox<>();

		cmb.setTooltip(new Tooltip());

		cmb.getItems().addAll(LISTA);

		stage.setScene(new Scene(new StackPane(cmb)));

		stage.show();

		stage.setTitle("Filtrando um ComboBox");

		stage.setWidth(300);

		stage.setHeight(300);

		new ComboBoxAutoComplete<String>(cmb);

	}

}
