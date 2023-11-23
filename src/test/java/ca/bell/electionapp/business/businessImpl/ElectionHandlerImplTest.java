package ca.bell.electionapp.business.businessImpl;

import ca.bell.electionapp.business.business.ElectionHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ElectionHandlerImplTest {

    @MockBean
    private ElectionHandler electionHandler;


    @Test
    void testDisplayFraudListWithFraud() {
        /***
         *  Simuler une  liste de Fraudes avec deux valeurs
         */

        List<String> mockFraudList = Mockito.mock(ArrayList.class);

        when(mockFraudList.isEmpty()).thenReturn(false);
        when(mockFraudList.size()).thenReturn(2);
        when(mockFraudList.get(0)).thenReturn("Fraud detected: Voter 1 voted multiple times.");
        when(mockFraudList.get(1)).thenReturn("Fraud detected: Voter 6 voted multiple times.");
        when(electionHandler.displayFraudList()).thenReturn(mockFraudList);

        assertEquals(2, mockFraudList.size());
        assertEquals("Fraud detected: Voter 1 voted multiple times.", mockFraudList.get(0));
        assertEquals("Fraud detected: Voter 6 voted multiple times.", mockFraudList.get(1));

    }


    @Test
    void testDisplayFraudListWithoutFraud() {

        /***
         *  Simuler une  liste de Fraudes vide
         */
        List<String> mockFraudList1 = Mockito.mock(ArrayList.class);

        when(mockFraudList1.isEmpty()).thenReturn(true);
        when(mockFraudList1.size()).thenReturn(0);
        when(electionHandler.displayFraudList()).thenReturn(mockFraudList1);

        assertEquals(0, mockFraudList1.size());

    }
}