/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.x9_jsf.testes;

import com.mycompany.x9_jsf.entidades.Aluno;
import com.mycompany.x9_jsf.entidades.Endereco;
import com.mycompany.x9_jsf.entidades.Professor;
import com.mycompany.x9_jsf.entidades.RelatorioParental;
import com.mycompany.x9_jsf.entidades.Responsavel;
import com.mycompany.x9_jsf.entidades.Turma;
import com.mycompany.x9_jsf.entidades.Usuario;
import com.mycompany.x9_jsf.excecao.ExcecaoNegocio;
import com.mycompany.x9_jsf.servico.AlunoServico;
import com.mycompany.x9_jsf.servico.ProfessorServico;
import com.mycompany.x9_jsf.servico.ResponsavelServico;
import com.mycompany.x9_jsf.servico.TurmaServico;
import com.mycompany.x9_jsf.servico.UsuarioServico;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.persistence.NoResultException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

/**
 *
 * @author aldo_neto
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EJBTests {

    private static EJBContainer container;
    private UsuarioServico usuarioServico;
    private ProfessorServico professorServico;
    private TurmaServico turmaServico;
    private AlunoServico alunoServico;
    private ResponsavelServico responsavelServico;

    public EJBTests() {
    }

    @BeforeClass
    public static void setUpClass() {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
    }

    @AfterClass
    public static void tearDownClass() {
        container.close();
    }

    @Before
    public void setUp() throws NamingException {
        usuarioServico = (UsuarioServico) container.getContext().lookup("java:global/classes/UsuarioServico");
        professorServico = (ProfessorServico) container.getContext().lookup("java:global/classes/ProfessorServico");
        turmaServico = (TurmaServico) container.getContext().lookup("java:global/classes/TurmaServico");
        alunoServico = (AlunoServico) container.getContext().lookup("java:global/classes/AlunoServico");
        responsavelServico = (ResponsavelServico) container.getContext().lookup("java:global/classes/ResponsavelServico");
    }

    @After
    public void tearDown() {
    }

    private Date getData(int dia, int mes, int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dia);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, ano);
        return calendar.getTime();
    }

    /**
     * Test of login method, of class UsuarioServico.
     *
     * /**
     * Test of salvarAdmin method, of class UsuarioServico.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void t01_buscaAdmin() throws Exception {
        Usuario admin;
        admin = usuarioServico.login("administrador", ".Ae12345");
        assertNotNull(admin);
    }

    /**
     * Test of salvarProfessor method, of class UsuarioServico.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void t02_buscarProfessorValidoPorCPF() throws Exception {
        Professor prof = professorServico.getProfessor("104.707.064-22"); 
        assertNotNull(prof);
    }

    @Test
    public void t03_inserirProfessor() throws Exception {

       Professor prof = new Professor();

        Endereco end = new Endereco();
        end.setCep("55.800-000");
        end.setCidade("Nazare");
        end.setNumeroEndereco(50);
        end.setRua("Rua do teste ejb");
        end.setUf("PE");

        prof.setNome("Marcos");
        prof.setEmail("professor500@hotmail.com");
        prof.setLogin("professor500");
        prof.setSenha(".Ae12345");
        prof.setTelefone("(81)99284-9408");
        prof.setEndereco(end);
        prof.setCpf("080.881.674-85");
        prof.setDataNascimento(getData(10, Calendar.JANUARY, 1998));


        professorServico.salvar(prof);

        assertNotNull(prof.getIdUsuario());

    }
    
    

    //  T04 -Excecao esperada pois já existe um professor com o CPF
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void t04_insereProfRepetido() throws ExcecaoNegocio {
        
         thrown.expect(ExcecaoNegocio.class);
        
        Professor prof = new Professor();

        Endereco end = new Endereco();
        end.setCep("55.800-000");
        end.setCidade("Nazare");
        end.setNumeroEndereco(50);
        end.setRua("Rua do teste ejb");
        end.setUf("PE");

        prof.setNome("Marcos");
        prof.setEmail("professor500@hotmail.com");
        prof.setLogin("professor500");
        prof.setSenha(".Ae12345");
        prof.setTelefone("(81)99284-9408");
        prof.setEndereco(end);
        prof.setCpf("080.881.674-85");
        prof.setDataNascimento(getData(10, Calendar.JANUARY, 1998));


            professorServico.salvar(prof);
      
       

    }

    @Test
    public void t05_buscarTurmas() throws NamingException {
        List<Turma> turmas = turmaServico.getTurmas();
        assertEquals(turmas.size(), 2);
    }

    @Test
    public void t06_removeProfessor() throws ExcecaoNegocio {
        Professor prof = professorServico.getProfessor("080.881.674-85");
        
        professorServico.remover(prof);
        try {
            professorServico.getProfessor("080.881.674-85");
            assertTrue(false);
        } catch (Exception ex) {
            assertTrue(ex.getCause() instanceof NoResultException);
        }
    }

    @Test
    public void t07_buscarAlunoPorMatricula() {

        Aluno aln = alunoServico.getAluno("Y1234567");

        assertEquals("Joao", aln.getNome());
    }
    
    
    @Test
    public void t08_buscarAlunosPorResponsavel() {
        Responsavel resp = responsavelServico.getResponsavel("965.462.320-06");
        
        List<Aluno> alunos = resp.getAlunos();
        
        
        assertEquals(alunos.size(), 2);
        
    }
    
    @Test
    public void t09_editarDadosDoAluno() throws ExcecaoNegocio{
       Aluno aln = alunoServico.getAluno("Y1234567");
       
       aln.setNome("Novonome");
       aln.setDeficiencia("Sim");
       
       alunoServico.atualizar(aln);
       
       Aluno alnAlterado = alunoServico.getAluno("Y1234567");
       
        assertEquals("Novonome", alnAlterado.getNome());
    
    }
    
    @Test
    public void t10_InsereNotaValida() throws ExcecaoNegocio{
       Aluno aln = alunoServico.getAluno("20291019");
       
       RelatorioParental rp = aln.getRelatorioParental();
       
       rp.setCriatividade(2.0);
       
       alunoServico.atualizar(aln);
       
       Aluno alnRelatorioAlterado = alunoServico.getAluno("20291019");
        
       rp = alnRelatorioAlterado.getRelatorioParental();
    
       
       //AssertEquals com Delta
        assertEquals(2.0, alnRelatorioAlterado.getRelatorioParental().getCriatividade(), 0);
       
    }
    
  

}
