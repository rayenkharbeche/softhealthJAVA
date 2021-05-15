/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class Image {
   public static String path;
    String filename=null;
 public void filen()
    {
        
    try{
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selectionner une image");
        fileChooser.setApproveButtonText("Ajouter image");
        fileChooser.showOpenDialog(null);
        File file=fileChooser.getSelectedFile();
        filename=file.getAbsolutePath();
        this.path=(filename);
    }
    catch(Exception e){
        System.out.println(e);
        JOptionPane.showMessageDialog(null,"Veuiller choisir une image");
    }
     
}
    public String getp()
    {
        return path;
    }     
}
