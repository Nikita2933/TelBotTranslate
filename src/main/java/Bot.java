import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;


public  class Bot  extends TelegramLongPollingBot {
    private Setter setter = new Setter();
    private Translate tr = new Translate();
    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private  void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            execute(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return setter.botname ;

    }
    @Override
    public String getBotToken() {
        return setter.tokenBot;

    }

    @Override
    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage();
        String txt = msg.getText();
        try {
            String eng = tr.getJsonStringYandex(txt);
            sendMsg(msg,eng);

        } catch (IOException ex) {

            ex.printStackTrace();

        }
    }
}

