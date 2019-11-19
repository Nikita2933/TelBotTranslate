//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class Bot extends TelegramLongPollingBot {
    private Setter setter = new Setter();
    private Translate tr = new Translate();

    public Bot() {
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();

        try {
            botapi.registerBot(new Bot());
        } catch (TelegramApiException var3) {
            var3.printStackTrace();
        }

    }

    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);

        try {
            this.execute(s);
        } catch (TelegramApiException var5) {
            var5.printStackTrace();
        }

    }

    public String getBotUsername() {
        return this.setter.botname;
    }

    public String getBotToken() {
        return this.setter.tokenBot;
    }

    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage();
        String txt = msg.getText();
        String eng = null;
        switch (txt) {
            case "/start":
                eng = "Приветствую, для использования переводчика необходимо выбрать направление перевода /ruen с Русского на Английский  /enru - с Английского на Русский(По умолчанию /ruen) . Если Вам необходимо узнать значение слова введите /dictionary(анг. словарь)";
                sendMsg(msg, eng);
                break;
            case "/ruen":
                tr.url = this.setter.urlTrans;
                tr.apiKey = this.setter.apikeytrans;
                tr.lang = this.tr.translangruen;


                this.sendMsg(msg, "Выбран перевод с Русского на Английский");
                break;
            case "/enru":
                tr.url = setter.urlTrans;
                tr.apiKey = this.setter.apikeytrans;
                tr.lang = this.tr.translangenru;


                this.sendMsg(msg, "Выбран перевод с Английского на Русский");
                break;
            case "/dictionary":
                tr.url = this.setter.urldict;
                tr.apiKey = this.setter.apikeydict;
                tr.lang = this.tr.dictlangru;
                sendMsg(msg, "Выбран режим словаря: По умолчанию для изменения на Русский словарь команда /ChangeRu  )");

                break;
            case "/ChangeRu":
                tr.url = this.setter.urldict;
                tr.apiKey = this.setter.apikeydict;
                tr.lang = this.tr.dictlangruru;
                sendMsg(msg, "Выбран режим словаря: Русский словарь");

                break;
            default:
                try {
                    eng = this.tr.getJsonStringYandex(txt);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                sendMsg(msg, eng);
                break;
        }
    }

}


