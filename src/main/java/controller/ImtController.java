package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class ImtController {

    @FXML private TextField txtTinggi;
    @FXML private TextField txtBerat;
    
    @FXML private Label lblImt;
    @FXML private Label lblKategori;
    @FXML private Label lblSelisih;
    @FXML private Label lblBobotNormal;
    
    @FXML private Polygon jarumIndikator;

    @FXML
    public void hitungIMT(ActionEvent event) {
        try {
            String inputTinggi = txtTinggi.getText().replace(",", ".");
            String inputBerat = txtBerat.getText().replace(",", ".");
            
            double tinggiCm = Double.parseDouble(inputTinggi);
            double berat = Double.parseDouble(inputBerat);
            
            if (tinggiCm <= 0 || berat <= 0) {
                tampilkanError("Angka harus > 0");
                return;
            }
            
            double tinggiM = tinggiCm / 100;
            double imt = berat / (tinggiM * tinggiM);
            
            String kategori;
            String warnaHex;
            if (imt < 18.5) {
                kategori = "Kurang Bobot";
                warnaHex = "-fx-text-fill: #2196F3;";
            } else if (imt >= 18.5 && imt <= 24.9) {
                kategori = "Normal";
                warnaHex = "-fx-text-fill: #4CAF50;";
            } else if (imt >= 25.0 && imt <= 29.9) {
                kategori = "Kelebihan Bobot";
                warnaHex = "-fx-text-fill: #F44336;";
            } else {
                kategori = "Obesitas";
                warnaHex = "-fx-text-fill: #F44336;";
            }
            
            double batasBawah = 18.5 * (tinggiM * tinggiM);
            double batasAtas = 24.9 * (tinggiM * tinggiM);
            
            double selisih = 0;
            if (berat < batasBawah) {
                selisih = batasBawah - berat;
            } else if (berat > batasAtas) {
                selisih = berat - batasAtas;
            }
            
            lblImt.setText(String.format("%.1f", imt));
            lblImt.setStyle(warnaHex + " -fx-font-weight: bold; -fx-font-size: 24;");
            
            lblKategori.setText(kategori);
            lblKategori.setStyle(warnaHex + " -fx-font-weight: bold; -fx-font-size: 14;");
            
            if (selisih == 0) {
                lblSelisih.setText("Ideal");
            } else {
                lblSelisih.setText(String.format("%.1f kg", selisih));
            }
            lblSelisih.setStyle(warnaHex + " -fx-font-weight: bold; -fx-font-size: 14;");
            
            lblBobotNormal.setText(String.format("%.1f - %.1f kg", batasBawah, batasAtas));
            
            double imtDibatasi = Math.max(15, Math.min(imt, 40));
            double sudutPutar = ((imtDibatasi - 15) / (40 - 15)) * 180 - 90;
            
            jarumIndikator.getTransforms().clear();
            jarumIndikator.getTransforms().add(new Rotate(sudutPutar, 0, 0));

        } catch (NumberFormatException e) {
            tampilkanError("Input tidak valid");
        }
    }
    
    private void tampilkanError(String pesan) {
        lblImt.setText("-");
        lblKategori.setText(pesan);
        lblKategori.setStyle("-fx-text-fill: #F44336; -fx-font-weight: bold; -fx-font-size: 14;");
        lblSelisih.setText("-");
        lblBobotNormal.setText("-");
        jarumIndikator.getTransforms().clear();
    }
}