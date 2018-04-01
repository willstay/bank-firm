package com.willstay;

import com.willstay.atm.Atm;
import com.willstay.atm.AtmDepartment;
import com.willstay.banknote.BankNote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        BankNote bankNote500 = new BankNote(500);
        BankNote bankNote5000 = new BankNote(5000);
        BankNote bankNote200 = new BankNote(200);
        List<BankNote> bankNotes = new ArrayList<>();
        bankNotes.add(bankNote500);
        bankNotes.add(bankNote500);
        bankNotes.add(bankNote5000);
        bankNotes.add(bankNote5000);
        bankNotes.add(bankNote200);

        Atm atm = new Atm(bankNotes);
        System.out.println("Устанока дефолтного значения наличных в 11200");

        AtmDepartment atmDepartment = new AtmDepartment(Arrays.asList(atm));
        System.out.println("Текущий баланс: " + atm.getATMBalance());

        atm.getBankNotes(10500);
        System.out.println("Баланс после выдачи 10500: " + atm.getATMBalance());

        atm.setContainersValueToDefault();
        System.out.println("Баланс после возвращения к дефолтным настройкам: " + atm.getATMBalance());

        atm.getBankNotes(500);
        System.out.println("Баланс после выдачи 500: " + atm.getATMBalance());

        atm.addBankNotes(Arrays.asList(new BankNote(200)));
        System.out.println("Баланс после приёма 200: " + atm.getATMBalance());

        atmDepartment.getAll();
        System.out.println("Баланс после всеобщего сбора: " + atm.getATMBalance());

        atmDepartment.setAllAtmToDefault();
        System.out.println("Баланс после всеобщего возвращения к дефотным настройкам: " + atm.getATMBalance());
    }
}
