package com.example.demo;

public class Context  {
    private final Bot bot;
    private final User user;
    private final String input;

    public static Context of(Bot bot, User user, String text) {
        return new Context(bot, user, text);
    }

    private Context(Bot bot, User user, String input) {
        this.bot = bot;
        this.user = user;

        this.input = input;
    }


    public String getInput() {
        return input;
    }

    public User getUser() {
        return user;
    }

    public Bot getBot() {
        return bot;
    }
}
