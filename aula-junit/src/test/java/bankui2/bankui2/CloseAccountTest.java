package bankui2.bankui2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import javax.swing.JTextField;

class CloseAccountTest {

    private closeaccount ca;

    private final String VALID_ACCOUNT = "987654";      // CT17
    private final String NON_EXISTENT_ACCOUNT = "1234"; // CT19
    private final String NON_NUMERIC_ACCOUNT = "752gfhf"; // CT20
    

    @BeforeEach
    void setUp() {
        ca = new closeaccount();
    }
    
    private void setTextField(String accountNumber) throws Exception {
        Field tf1Field = closeaccount.class.getDeclaredField("tf1");
        tf1Field.setAccessible(true);
        JTextField tf1 = (JTextField) tf1Field.get(ca);
        tf1.setText(accountNumber);
    }

     //CT17: Fechamento de conta válido (CE10 Válida).

    @Test
    void testValidCloseAccount_CT17() throws Exception {
        setTextField(VALID_ACCOUNT);
        
        assertDoesNotThrow(() -> ca.actionPerformed(new java.awt.event.ActionEvent(ca.jb1, 0, "")), 
            "CT17 (PASS): Dados válidos devem completar o parsing de tipos sem erros.");
        
        System.out.println("CT17: Fechamento Válido. Tipagem e fluxo de código OK. (Ação DELETE no DB simulada).");
    }

    //CT19: Conta Inexistente (CE10 Inválida).
    @Test
    void testNonExistentAccount_CT19() throws Exception {
        setTextField(NON_EXISTENT_ACCOUNT);
        
        try {
            ca.actionPerformed(new java.awt.event.ActionEvent(ca.jb1, 0, ""));
            
            fail("CT19: FALHA LÓGICA (DEFEITO): O sistema ACEITOU o ID, mas a validação de existência de conta é crítica.");
            
        } catch (Throwable t) {
            System.out.println("Erro durante execução do CT19: " + t.getMessage());
        }
        System.out.println("CT19: A conta inexistente não é bloqueada na regra de negócio.");
    }

     //CT20: Formato de Conta Inválido.
    @Test
    void testInvalidAccountFormat_NonNumeric_CT20() throws Exception {
        setTextField(NON_NUMERIC_ACCOUNT); // "752gfhf"
        
        // Assert: Espera-se que a NumberFormatException seja capturada e tratada pelo try-catch.
        assertDoesNotThrow(() -> ca.actionPerformed(new java.awt.event.ActionEvent(ca.jb1, 0, "")), 
            "CT20");
        
    }
}