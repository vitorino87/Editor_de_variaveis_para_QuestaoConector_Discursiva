/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor_de_variaveis_para_questaoconector_discursiva;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
        } else {
            System.exit(0);
        }
        return f;
    }

    @SuppressWarnings("empty-statement")
    public void realizarLeituraDaLinhaDoArquivo(File file) throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream(file.getAbsolutePath())) {
            //Charset cs = Charset.forName("Cp1252");
            
            String charset = JOptionPane.showInputDialog(null, "Escolha a codificação, exemplo:"
                    + " UTF-8, Cp1252, etc.:");
            Charset cs = Charset.forName(charset);
            InputStreamReader isr = new InputStreamReader(fis, cs);
            int ch;
            int contador = 0;
            //String resposta = "";
            String text = "";
            File arquivoParaSalvar = selecionarLocalParaSalvar();
            while ((ch = isr.read()) != -1) {
                if (ch != 13) {
                    if (ch != 10) {
                        text += String.valueOf((char) ch);
                    }else{
                        contador = processarLinha2(text, arquivoParaSalvar, contador);
                        text = "";
                    }
                } else {
                    contador = processarLinha2(text, arquivoParaSalvar, contador);
                    text = "";
                } //text += String.valueOf((char) ch);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }

    }

    public int processarLinha(String text, File arquivo, int contador) {
        if (text.contains("Resp.:")) {
            text = "a[" + contador + "]=R.string.q" + contador + ";\n";
            text += "b[" + contador + "][0]=R.string.q" + contador + "a;\n";            
            salvarLinhasProcessadas(arquivo.getAbsolutePath() + "/conector.java", text);
        }
        return contador;
    }

    public int processarLinha2(String text, File arquivo, int contador) {
        String textAux = text;        
        if (text.contains("Resp.:")) {
            text = "<string name=\"q" + contador + "a\">" + text + "</string>\n";
            processarLinha(textAux, arquivo, contador);
            contador++;
        }else{
            text = "<string name=\"q" + contador + "\">" + text + "</string>\n";
        }
        salvarLinhasProcessadas(arquivo.getAbsolutePath() + "/questoes.xml", text);
        return contador;
    }

    public void salvarLinhasProcessadas(String file, String textoParaSalvar) {
        //try {
            if (file != null) {
                try (Writer fw2 = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8)) {
                   //JOptionPane.showConfirmDialog(null, fw.getEncoding());   
                   //Writer fw2 = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8);
                   //BufferedWriter bw = new BufferedWriter(fw2);
                  fw2.write(textoParaSalvar);
                //FileWriter fw = new FileWriter(file, true);
                //fw.write(textoParaSalvar);
                //}
                //try{
                //   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8));
                //  bw.write(textoParaSalvar);
                //}catch(Exception ex){
                        
                //}
            //melhorias foram feitas
        } catch (Exception ex) {

        }

    }
    }

    public File selecionarLocalParaSalvar() {
        JFileChooser fc = new JFileChooser();
        JOptionPane.showMessageDialog(null, "Escolha o local onde salvar");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnval = fc.showSaveDialog(fc);
        File file = null;
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
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        JOptionPane.showMessageDialog(null, "Esse projeto consiste em criar dois arquivos: "
                + "questao.xml e conector.java\n\nPara usá-lo corretamente siga os seguintes passos:\n"
                + "1º Crie as questões num arquivo qualquer seguindo o padrão Questionation 2(padrão próprio);\n"
                + "2º Não precisa rodar macro nenhum, mas é importante prestar a atenção nas respostas (deve ter \"Resp.:\" escrito desse jeito);\n"
                );
        JOptionPane.showMessageDialog(null, "Escolha o arquivo");
        Editor_de_variaveis_para_QuestaoConector_Discursiva editor = new Editor_de_variaveis_para_QuestaoConector_Discursiva();
        File file = editor.abrirArquivo();
        editor.realizarLeituraDaLinhaDoArquivo(file);
        JOptionPane.showMessageDialog(null, "O programa terminou o serviço!");
    }

}
