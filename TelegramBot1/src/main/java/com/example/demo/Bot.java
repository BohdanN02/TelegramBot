package com.example.demo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Component
public class Bot extends TelegramLongPollingBot {

    private  static final Logger LOGGER = LogManager.getLogger(Bot.class);



    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    private final UserServise userServise;


    public Bot(UserServise userServise) {
        this.userServise = userServise;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
    public void onUpdateReceived(Update update) {
        if(!update.hasMessage() || !update.getMessage().hasText())
            return;

        final String text = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();

        User user = userServise.findByChatId(chatId);


        Context context;
        State state;

        if (user == null) {
            state = State.getInitialState();

            user = new User(chatId, state.ordinal());
            userServise.addUser(user);

            context = Context.of(this, user, text);
            state.enter(context);
            LOGGER.info("New user registered:" + chatId);
        } else {
            context = Context.of(this, user,text);
            state = State.byId(user.getStateId());

            LOGGER.info("Update received for user in state:" + state);
        }
        state.handleInput(context);

        do{
            state = state.nextState();
            state.enter(context);
        } while (!state.isInputNeeded());

        user.setStateId(state.ordinal());
        userServise.updateUser(user);


    }



    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}

