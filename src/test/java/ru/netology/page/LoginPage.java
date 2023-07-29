package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement login = $("[data-test-id=\"login\"] input");
    private SelenideElement password = $("[data-test-id=\"password\"] input");
    private SelenideElement actionLogin = $("[data-test-id=\"action-login\"]");

    public VerificationPage validLogin(DataHelper.AuhtInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        actionLogin.click();
        return new VerificationPage();

    }
}
