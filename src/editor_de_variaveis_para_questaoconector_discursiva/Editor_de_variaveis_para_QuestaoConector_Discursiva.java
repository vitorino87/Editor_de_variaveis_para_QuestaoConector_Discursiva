/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor_de_variaveis_para_questaoconector_discursiva;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Win7
 */
public class Editor_de_variaveis_para_QuestaoConector_Discursiva {
public File abrirArquivo() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(fc);
        File f = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
        }else{
            System.exit(0);
        }
        return f;
    }

@SuppressWarnings("empty-statement")
    public void realizarLeituraDaLinhaDoArquivo(File file) throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream(file.getAbsolutePath())) {
            Charset cs = Charset.forName("UTF-8");
            InputStreamReader isr = new InputStreamReader(fis, cs);
            int ch;
            int contador = 0;
            //String resposta = "";
            String text = "";
            File arquivoParaSalvar = selecionarLocalParaSalvar();
            while ((ch = isr.read()) != -1) {
                if (ch != 10) {
                    text += String.valueOf((char) ch);
                } else {
                    text += String.valueOf((char) ch);
                    contador = processarLinha(text, arquivoParaSalvar, contador);
                    text = "";
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }

    }
    
    public int processarLinha(String text, File arquivo, int contador){
        String text2 = "";
        if (text.indexOf("e\">e)") > 0 || text.indexOf("e\">xe)") > 0) {            
            if (text.indexOf("e\">xe)") > 0) {
                text = text.replace("xe)", "e)");
                text2 += "b[" + contador + "][5]=4;\n";
                salvarLinhasProcessadas(arquivo.getAbsolutePath() + "/conector.java", text2);
                text2 = "";
            }            
            String[] text3 = {"a", "b", "c", "d", "e"};
            text2 += "a[" + contador + "]=R.string.q" + contador + ";\n";
            for (int contador2 = 0; contador2 < 5; contador2++) {
                text2 += "b[" + contador + "][" + contador2 + "]=R.string.q" + contador + text3[contador2] + ";\n";
            }            
            contador++;
            //JOptionPane.showMessageDialog(null, text2);
            salvarLinhasProcessadas(arquivo.getAbsolutePath() + "/conector.java", text2);
        } else if (text.indexOf("xa)") > 0) {
            text = text.replace("xa)", "a)");
            text2 += "b[" + contador + "][5]=0;\n";
            salvarLinhasProcessadas(arquivo.getAbsolutePath() + "/conector.java", text2);
            //text=text.substring(0, text.indexOf("xa)")) + 
            //        text.substring(text.indexOf("xa)")+1, text.length());
        } else if (text.indexOf("xb)") > 0) {
            text = text.replace("xb)", "b)");
            text2 += "b[" + contador + "][5]=1;\n";
            salvarLinhasProcessadas(arquivo.getAbsolutePath() + "/conector.java", text2);
            //text=text.substring(0, text.indexOf("xb)")) + 
            //    text.substring(text.indexOf("xb)")+1, text.length());
        } else if (text.indexOf("xc)") > 0) {
            text = text.replace("xc)", "c)");
            text2 += "b[" + contador + "][5]=2;\n";
            salvarLinhasProcessadas(arquivo.getAbsolutePath() + "/conector.java", text2);
            //text=text.substring(0, text.indexOf("xc)")) + 
            //    text.substring(text.indexOf("xc)")+1, text.length());
        } else if (text.indexOf("xd)") > 0) {
            text = text.replace("xd)", "d)");
            text2 += "b[" + contador + "][5]=3;\n";
            salvarLinhasProcessadas(arquivo.getAbsolutePath() + "/conector.java", text2);
            //text=text.replace("xe)", "e)");
            //text=text.substring(0, text.indexOf("xd)")) + 
            // text.substring(text.indexOf("xd)")+1, text.length());
        } else if (text.indexOf("xe)") > 0) {
//                            text=text.replace("xe)", "e)");                            
//                            text2+="b["+contador+"][5]=4\n";  
//                            salvarLinhasProcessadas(arquivo.getAbsolutePath()+"/conector.java", text2); 
        }

        //JOptionPane.showMessageDialog(null, text);
        salvarLinhasProcessadas(arquivo.getAbsolutePath() + "/questoes.xml", text);
        return contador;
    }
    
    public void salvarLinhasProcessadas(String file, String textoParaSalvar) {
        try {
            if (file != null) {
                try (FileWriter fw = new FileWriter(file, true)) {
                    fw.write(textoParaSalvar);
                }
            }
        } catch (Exception ex) {

        }

    }

    public File selecionarLocalParaSalvar() {
        JFileChooser fc = new JFileChooser();
        JOptionPane.showMessageDialog(null, "Escolha o local onde salvar");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnval = fc.showSaveDialog(fc);
        File file=null;
        if (returnval == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(null, "O programa será encerrado!");
            System.exit(0);
        }
        return file;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
