package com.willstay.atm;

import com.willstay.banknote.BankNote;

import java.util.ArrayList;
import java.util.List;

public class AtmDepartment {
    private final List<Atm> atmList;

    public AtmDepartment(List<Atm> atmList) {
        this.atmList = atmList;
    }

    public List<BankNote> getAllBankNotes() {
        List<BankNote> bankNoteList = new ArrayList<>();
        for (Atm atm : atmList) {
            bankNoteList.addAll(atm.getAllBankNotes());
        }
        return bankNoteList;
    }

    public void setAllAtmToDefault() {
        for (Atm atm : atmList) {
            atm.setContainersValueToDefault();
        }
    }

    public int getAllBalance() {
        int balance = 0;
        for (Atm atm : atmList) {
            balance += atm.getATMBalance();
        }
        return balance;
    }

}
