package projetosql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProjetoSQL {

    public static Connection conexao;
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static String sql;
    public static String usuario = "root";
    public static String senha = "";


    public static void main(String[] args) throws SQLException  {

      int op;
      int saida = 1;
      
       Scanner in = new Scanner(System.in);
       
 while (saida != 0){         
        System.out.println("1-insert");
        System.out.println("2-update");
        System.out.println("3-delete");
        System.out.println("4-select");
        System.out.println("0-sair");
        
        System.out.println("Digite a opcao: ");
        op = Integer.parseInt(in.nextLine());
      
        switch (op){
        
            case 1:
                inserir();
                break;
            case 2:
                atualizar();
                break;
            case 3:
                deletar();
                break;
            case 4:
                consultar();
                break;
            case 0:
                System.exit(0);
                break;
                default:
                System.out.println("Opção Invalida");
                break;
     }
    }
    }
    public static void consultar () throws SQLException{
        
           // Leitura do Banco de Dados
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro", usuario, senha);
        sql = "SELECT * FROM contato ORDER BY nome";
        ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
   System.out.println("\n\nListando os Registros Gravados");
        while (rs.next()) {
            System.out.println("Id..:" + rs.getInt("id"));
            System.out.println("Nome:" + rs.getString("nome"));
            System.out.println("Cpf.:" + rs.getString("cpf"));
            System.out.println("");
            
           
        }
        
        ps.close();
        conexao.close();

       
}

    public static void inserir() throws SQLException{
        int id;
        String nome;
        String cpf;

        Scanner in = new Scanner(System.in);

        System.out.println("Digite o Id");
        id = Integer.parseInt(in.nextLine());

        System.out.println("Digite o Nome");
        nome = in.nextLine();

        System.out.println("Digite o Cpf");
        cpf = in.nextLine();

        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro", usuario, senha);

        sql = "INSERT INTO contato (id, nome, cpf) VALUES (?, ?, ?)";
        ps = conexao.prepareStatement(sql);

        ps.setInt(1, id);
        ps.setString(2, nome);
        ps.setString(3, cpf);

        ps.execute();
        ps.close();
        conexao.close();

    }
    public static void atualizar() throws SQLException{
        int id;
        String nome;
        String cpf;

        Scanner in = new Scanner(System.in);

        System.out.println("Digite o Id");
        id = Integer.parseInt(in.nextLine());
        System.out.println("Digite os dados para atualizar");
        System.out.println(" ");
        System.out.println("Digite o Nome");
        nome = in.nextLine();

        System.out.println("Digite o Cpf");
        cpf = in.nextLine();

        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro", usuario, senha);

        sql =  "UPDATE contato SET nome = ?, cpf = ? where id = ? ";
        ps = conexao.prepareStatement(sql);
        ps.setInt(3, id);
        ps.setString(1, nome);
        ps.setString(2, cpf);

        ps.execute();
        ps.close();
        conexao.close();

    }
        public static void deletar() throws SQLException{
        int id;

        Scanner in = new Scanner(System.in);

        System.out.println("Digite o Id");
        id = Integer.parseInt(in.nextLine());

        
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro", usuario, senha);

        sql = "DELETE FROM contato WHERE id=? ";
        ps = conexao.prepareStatement(sql);

        ps.setInt(1, id);
        

        ps.execute();
        ps.close();
        conexao.close();

    }

}