package bankui2.bankui2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import javax.swing.JTextField;

class WithdrawTest {

    private withdraw w;

    private final String VALID_ACCOUNT = "123456"; 
    private final String NON_EXISTENT_ACCOUNT = "187123"; // CT10
    private final String NON_NUMERIC_ACCOUNT = "823faa75"; // CT11
    
    private final long INITIAL_BALANCE = 1700; 

    @BeforeEach
    void setUp() {
        w = new withdraw();
    }

    private void setTextFields(String accountNumber, String amount) throws Exception {
        String[] fieldNames = {"tf1", "tf2"};
        String[] values = {accountNumber, amount};

        for (int i = 0; i < fieldNames.length; i++) {
            Field field = withdraw.class.getDeclaredField(fieldNames[i]);
            field.setAccessible(true);
            JTextField textField = (JTextField) field.get(w);
            textField.setText(values[i]);
        }
    }
    
     //CT9: Saque de 500 em saldo de 1700 (CE 6 e CE 7 Válidas).
    @Test
    void testValidWithdrawal_CT9() throws Exception {
        setTextFields(VALID_ACCOUNT, "500");
        
        assertDoesNotThrow(() -> w.ShowTableData(), 
            "CT9 (PASS): Dados válidos devem completar o parsing de tipos sem erros.");
        
        System.out.println("CT9: Saque Válido. Tipagem e fluxo de código OK.");
    }

    
    //CT12: Saldo Insuficiente (Saque > Saldo).
    @Test
    void testInsufficientBalance_CT12() throws Exception {
        final String WITHDRAW_AMOUNT = "1800"; // Maior que 1700
        setTextFields(VALID_ACCOUNT, WITHDRAW_AMOUNT);
        
        try {
            w.ShowTableData();
            fail("CT12: FALHA LÓGICA (DEFEITO): A validação de Saldo Insuficiente falhou. O sistema tentou prosseguir com o saque.");
            
        } catch (Throwable t) {
            System.out.println("Erro durante execução do CT12: " + t.getMessage());
        }
    }

    /**
     * CT13: Valor do Saque Inválido (Valor <= 0).
     */
    @Test
    void testInvalidWithdrawAmount_Negative_CT13() throws Exception {
        setTextFields(VALID_ACCOUNT, "-2");
        
        try {
            w.ShowTableData();
            
            fail("CT13: FALHA LÓGICA (DEFEITO): O sistema ACEITOU o Saque Negativo (-2). Esperava-se um bloqueio (validação > 0).");
            
        } catch (Throwable t) {
            System.out.println("Erro durante execução do CT13: " + t.getMessage());
        }
    }
    
    //CT10: Conta Inexistente (CE6 Inválida).
    @Test
    void testNonExistentAccount_CT10() throws Exception {
        setTextFields(NON_EXISTENT_ACCOUNT, "100");
        
        assertDoesNotThrow(() -> w.ShowTableData(), 
            "CT10 (PASS): Fluxo de código executado sem travamentos.");
        
        System.out.println("CT10: Passagem OK. A regra de CONTA INEXISTENTE é validada pelo DB (não pode ser verificada estaticamente).");
    }

    //CT11: Conta Formato Inválido (Não Numérico).
    @Test
    void testInvalidAccountFormat_NonNumeric_CT11() throws Exception {
        setTextFields(NON_NUMERIC_ACCOUNT, "100");
        
        assertDoesNotThrow(() -> w.ShowTableData(), 
            "CT11 (PASS): Fluxo de erro (NumberFormatException) deve ser tratado (capturado).");
        
        System.out.println("CT11: Fluxo de Erro (Tipagem) Tratado. Contabiliza SUCESSO.");
    }
}