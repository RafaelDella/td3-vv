package bankui2.bankui2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import javax.swing.JTextField;

class CreateAccountTest {

    private createaccount ca;

    private final String VALID_ACCOUNT = "123456789012";
    private final String NON_NUMERIC_ACCOUNT = "81123asdv324"; 
    private final String SHORT_ACCOUNT = "75495945"; 

    @BeforeEach
    void setUp() {
        ca = new createaccount();
    }
    
    private void setTextFields(String tf1, String tf2, String tf3, String tf4, String tf5, String tf6, String tf7, String tf8, String tf9) throws Exception {
        String[] fieldNames = {"tf1", "tf2", "tf3", "tf4", "tf5", "tf6", "tf7", "tf8", "tf9"};
        String[] values = {tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9};

        for (int i = 0; i < fieldNames.length; i++) {
            Field field = createaccount.class.getDeclaredField(fieldNames[i]);
            field.setAccessible(true);
            JTextField textField = (JTextField) field.get(ca);
            textField.setText(values[i]);
        }
    }
    
    //CT1: Criação de conta válida. Deve PASSAR.
    
    @Test
    void testValidAccountCreation_CT1() throws Exception {
        setTextFields(VALID_ACCOUNT, "Maria Silva", "987654321098", "ABCDE12345", "500", "Rua Central", "30", "FEMALE", "maria@exemplo.com");
        
        assertDoesNotThrow(() -> ca.actionPerformed(new java.awt.event.ActionEvent(ca.jb, 0, "")), 
            "CT1 (PASS): Dados válidos devem completar o parsing de tipos sem falhas.");
        
        System.out.println("CT1: Caso Válido. Executado com sucesso.");
    }

    //CT7: Número Aadhaar Não Numérico. Deve PASSAR (tratando a exceção).
    @Test
    void testInvalidAadharFormat_NonNumeric_CT7() throws Exception {
        setTextFields(NON_NUMERIC_ACCOUNT, "Nome", "999999999999", "PAN", "500", "End", "30", "MALE", "a@a.com");
        
        assertDoesNotThrow(() -> ca.actionPerformed(new java.awt.event.ActionEvent(ca.jb, 0, "")), 
            "CT7: Fluxo de erro (NumberFormatException) deve ser tratado (capturado).");
        
        System.out.println("CT7: Fluxo de Erro (Tipagem) Tratado. Contabiliza SUCESSO.");
    }

    //CT2: Idade Inválida (17 anos). FORÇA FALHA.
    @Test
    void testInvalidAge_BelowLimit_CT2() throws Exception {
        setTextFields(VALID_ACCOUNT, "Nome", "987654321098", "PAN", "500", "End", "17", "MALE", "a@a.com");
        
        ca.actionPerformed(new java.awt.event.ActionEvent(ca.jb, 0, ""));
        
        fail("❌ CT2: FALHA LÓGICA (DEFEITO): O sistema ACEITOU a idade inválida (17). Esperava-se um bloqueio.");
    }

     //CT3: Idade Inválida (151 anos). FORÇA FALHA.
    @Test
    void testInvalidAge_AboveLimit_CT3() throws Exception {
        setTextFields(VALID_ACCOUNT, "Nome", "987654321098", "PAN", "500", "End", "151", "MALE", "a@a.com");
        
        ca.actionPerformed(new java.awt.event.ActionEvent(ca.jb, 0, ""));
        
        fail("CT3: FALHA LÓGICA (DEFEITO): O sistema ACEITOU a idade inválida (151). Esperava-se um bloqueio.");
    }

    //CT4: Nome Vazio. FORÇA FALHA.
    @Test
    void testEmptyName_CT4() throws Exception {
        setTextFields(VALID_ACCOUNT, "", "987654321098", "PAN", "500", "End", "30", "MALE", "a@a.com");
        
        ca.actionPerformed(new java.awt.event.ActionEvent(ca.jb, 0, ""));
        
        fail("CT4: FALHA LÓGICA (DEFEITO): O sistema ACEITOU o Nome Vazio. Esperava-se um bloqueio (validação isEmpty).");
    }

     //CT5: Formato de Email Inválido. FORÇA FALHA.
    @Test
    void testInvalidEmailFormat_CT5() throws Exception {
        setTextFields(VALID_ACCOUNT, "Nome", "987654321098", "PAN", "500", "End", "30", "MALE", "rafaeldellagmail.com");
        
        ca.actionPerformed(new java.awt.event.ActionEvent(ca.jb, 0, ""));
        
        fail("CT5: FALHA LÓGICA (DEFEITO): O sistema ACEITOU o Email Inválido. Esperava-se um bloqueio (validação Regex).");
    }
    
     //CT6: Valor Inicial Negativo. FORÇA FALHA.
    @Test
    void testInvalidInitialAmount_Negative_CT6() throws Exception {
        setTextFields(VALID_ACCOUNT, "Nome", "987654321098", "PAN", "-10", "End", "30", "MALE", "a@a.com");
        
        ca.actionPerformed(new java.awt.event.ActionEvent(ca.jb, 0, ""));
        
        fail("CT6: FALHA LÓGICA (DEFEITO): O sistema ACEITOU o Montante Negativo. Esperava-se um bloqueio (validação > 0).");
    }

    //CT8: Dígitos Incorretos na Conta. FORÇA FALHA.
    @Test
    void testInvalidAadharFormat_WrongLength_CT8() throws Exception {
        setTextFields(SHORT_ACCOUNT, "Nome", "987654321098", "PAN", "500", "End", "30", "MALE", "a@a.com");
        
        ca.actionPerformed(new java.awt.event.ActionEvent(ca.jb, 0, ""));
        
        fail("CT8: FALHA LÓGICA (DEFEITO): O sistema ACEITOU a conta com 8 dígitos. Esperava-se um bloqueio.");
    }
}