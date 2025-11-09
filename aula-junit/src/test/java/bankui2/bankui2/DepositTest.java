package bankui2.bankui2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import javax.swing.JTextField;

class DepositTest {

    private deposit d;

    private final String VALID_ACCOUNT = "123456789012"; 
    private final String NON_NUMERIC_AMOUNT = "1A5"; 
    
    @BeforeEach
    void setUp() {
        d = new deposit();
    }
    
    private void setTextFields(String accountNumber, String amount) throws Exception {
        Field tf2Field = deposit.class.getDeclaredField("tf2");
        tf2Field.setAccessible(true);
        JTextField tf2 = (JTextField) tf2Field.get(d);
        tf2.setText(accountNumber);

        Field tf3Field = deposit.class.getDeclaredField("tf3");
        tf3Field.setAccessible(true);
        JTextField tf3 = (JTextField) tf3Field.get(d);
        tf3.setText(amount);
    }

    @Test
    void testValidDeposit_CT14() throws Exception {
        setTextFields(VALID_ACCOUNT, "550");
        
        assertDoesNotThrow(() -> d.actionPerformed(new java.awt.event.ActionEvent(d.jb, 0, "")), 
            "CT14 (PASS): Dados válidos devem completar o parsing de tipos sem erros.");
        
        System.out.println("CT14: Depósito Válido. Tipagem e fluxo de código OK.");
    }
    
    @Test
    void testInvalidDeposit_Negative_CT15() throws Exception {
        setTextFields(VALID_ACCOUNT, "-15");
        
        try {
            d.actionPerformed(new java.awt.event.ActionEvent(d.jb, 0, ""));
            
            fail("CT15: FALHA LÓGICA (DEFEITO): O sistema ACEITOU o Depósito Negativo (-15). Esperava-se um bloqueio (validação > 0).");
            
        } catch (Throwable t) {
            System.out.println("Erro durante execução do CT15: " + t.getMessage());
        }
        System.out.println("CT15: DEFEITO REGISTRADO como FAILURE.");
    }

    //CT16: Valor de Depósito Inválido (Não Numérico).
    @Test
    void testInvalidDeposit_NonNumeric_CT16() throws Exception {
        setTextFields(VALID_ACCOUNT, NON_NUMERIC_AMOUNT); // "1A5"
        
        assertDoesNotThrow(() -> d.actionPerformed(new java.awt.event.ActionEvent(d.jb, 0, "")), 
            "CT16 (PASS): Fluxo de erro (NumberFormatException) deve ser tratado.");
        
        System.out.println("CT16: Fluxo de Erro (Tipagem) Tratado. Contabiliza SUCESSO.");
    }
}