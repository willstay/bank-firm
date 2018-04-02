package com.willstay;

import com.willstay.atm.Atm;
import com.willstay.atm.AtmDepartment;
import com.willstay.banknote.BankNote;
import com.willstay.getbanknotesstrategy.GetMaxBankNotesStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private Atm atm;
    private Atm atm2;
    private AtmDepartment atmDepartment;

    @Before
    public void init() {
        BankNote bankNote200 = new BankNote(200);
        BankNote bankNote500 = new BankNote(500);
        BankNote bankNote1000 = new BankNote(1000);
        BankNote bankNote5000 = new BankNote(5000);
        List<BankNote> bankNotes = new ArrayList<>();
        bankNotes.add(bankNote200);
        bankNotes.add(bankNote500);
        bankNotes.add(bankNote500);
        bankNotes.add(bankNote1000);
        bankNotes.add(bankNote5000);
        bankNotes.add(bankNote5000);

        atm = new Atm(bankNotes);
        atm2 = new Atm(bankNotes);

        List<Atm> atmList = new ArrayList<>();
        atmList.add(atm);
        atmList.add(atm2);

        atmDepartment = new AtmDepartment(atmList);
    }

    @Test
    public void BalanceAfterInitEquals12200() {
        assertThat(atm.getATMBalance()).isEqualTo(12200);
    }

    @Test
    public void WhenTakes1000ThereIs1000BankNote() {
        List<BankNote> bankNoteList = atm.getBankNotes(new GetMaxBankNotesStrategy(), 1000);
        assertThat(bankNoteList.get(0).getValue()).isEqualTo(1000);
    }

    @Test
    public void WhenTakes10500From11200BalanceEquals1700() {
        atm.getBankNotes(new GetMaxBankNotesStrategy(), 10500);
        assertThat(atm.getATMBalance()).isEqualTo(1700);
    }

    @Test
    public void WhenGives200To11200BalanceEquals12400() {
        atm.addBankNotes(Arrays.asList(new BankNote(200)));
        assertThat(atm.getATMBalance()).isEqualTo(12400);
    }

    @Test
    public void WhenGetBalanceFromAtmEquals24400() {
        assertThat(atmDepartment.getAllBalance()).isEqualTo(24400);
    }
}