package main.java.simulado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Pessoa> pessoas = new ArrayList<>();
        Map<Integer, List<String[]>> enderecos = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("Pessoas.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int codigo = Integer.parseInt(parts[0]);
                String nome = parts[1];
                pessoas.add(new Pessoa(codigo, nome));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("Endereços.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String rua = parts[0];
                String cidade = parts[1];
                int codigo = Integer.parseInt(parts[2]);
                enderecos.computeIfAbsent(codigo, k -> new ArrayList<>()).add(new String[]{rua, cidade});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("PessoasComEndereco.csv"))) {
            for (Pessoa pessoa : pessoas) {
                List<String[]> enderecoList = enderecos.get(pessoa.getCodigo());
                if (enderecoList != null) {
                    for (String[] endereco : enderecoList) {
                        PessoaEndereco pessoaEndereco = new PessoaEndereco(pessoa.getCodigo(), pessoa.getNome(), endereco[0], endereco[1]);
                        bw.write(pessoaEndereco.toCsv());
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
