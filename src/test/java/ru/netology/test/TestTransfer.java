package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTransfer {

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuhtInfo();
        var verificationPage = LoginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecond() {
        var dashboardPage = new DashboardPage();
        var transferPage = new TransferPage();
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var balanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var balanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        var moneyTransfer = dashboardPage.secondCardButton();
        var infoCard = DataHelper.getFirstCardInfo();
        String sum = "100";
        var moneyTransferDataInput = transferPage.transferForm(sum, infoCard);

        assertEquals(balanceFirstCard - Integer.parseInt(sum), dashboardPage.getCardBalance(firstCardInfo));
        assertEquals(balanceSecondCard + Integer.parseInt(sum), dashboardPage.getCardBalance(secondCardInfo));

    }

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        var dashboardPage = new DashboardPage();
        var transferPage = new TransferPage();
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var balanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var balanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        var moneyTransfer = dashboardPage.firstCardButton();
        var infoCard = DataHelper.getSecondCardInfo();
        String sum = "100";
        var moneyTransferDataInput = transferPage.transferForm(sum, infoCard);

        assertEquals(balanceFirstCard + Integer.parseInt(sum), dashboardPage.getCardBalance(firstCardInfo));
        assertEquals(balanceSecondCard - Integer.parseInt(sum), dashboardPage.getCardBalance(secondCardInfo));

    }

    @Test
    void shouldCancellationOfMoneyTransfer() {
        var dashboardPage = new DashboardPage();
        var transferPage = new TransferPage();
        var moneyTransfer = dashboardPage.firstCardButton();
        var cancelingTransfer = transferPage.cancelButton();
    }

    @Test
    void shouldTransferMoreThanBalance() {
        var dashboardPage = new DashboardPage();
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var balanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var balanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        var moneyTransfer = dashboardPage.firstCardButton();
        var infoCard = DataHelper.getSecondCardInfo();
        String sum = "20_000";
        moneyTransfer.transferForm(sum, infoCard);
        moneyTransfer.errorMessage("Ошибка! Произошла ошибка");
    }

    @Test
    void shouldNotEnterAmount() {
        var dashboardPage = new DashboardPage();
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var balanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var balanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        var moneyTransfer = dashboardPage.firstCardButton();
        var infoCard = DataHelper.getSecondCardInfo();
        String sum = "";
        moneyTransfer.transferForm(sum, infoCard);
        moneyTransfer.errorMessage("Ошибка! Произошла ошибка");
    }

    @Test
    void shouldChooseOneTransferAndReceiptCard() {
        var dashboardPage = new DashboardPage();
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var balanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var balanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        var moneyTransfer = dashboardPage.firstCardButton();
        var infoCard = DataHelper.getFirstCardInfo();
        String sum = "100";
        moneyTransfer.transferForm(sum, infoCard);
        moneyTransfer.errorMessage("Ошибка! Произошла ошибка");
    }
}
