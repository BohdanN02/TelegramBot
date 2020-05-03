package com.example.demo;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



public enum State {

    Start {
        @Override
        public void enter(Context context) {
            sendMessage(context, "Вас вітає @InfopulseVacancies_Bot!!!");
        }
        @Override
        public State nextState() {
            return City;
        }

    },
    City {
        @Override
        public void enter(Context context) {
            sendMessage(context, " Оберіть місто у якому, ви, бажаєте знайти роботу:" +
                    "\r\n "+" - "+" Kyiv "+
                    "\r\n "+" - "+" Kharkiv "+
                    "\r\n "+" - "+" Lviv "+
                    "\r\n "+" - "+" Odesa"+
                    "\r\n "+" - "+" Vinnitsa "+
                    "\r\n "+" - "+" Zhitomir "+
                    "\r\n "+" - "+" Chernihiv ");
        }
        @Override
        public void handleInput(Context context) {
            context.getUser().setCity( context.getInput());
        }
        @Override
        public State nextState() {
            return Level;
        }
    },
    Level {
        @Override
        public void enter(Context context) {
            sendMessage(context, "Оберіть рівень ваших знань:" +
                    "\r\n "+" - "+" Trainee " +
                    "\r\n "+" - "+" Junior " +
                    "\r\n "+ "- "+" Midlle " +
                    "\r\n "+ " - "+" Senior " +
                    "\r\n "+" - "+" Team Lead " +
                    "\r\n "+" - "+" Expert ");
        }

        @Override
        public void handleInput(Context context) {
            context.getUser().setLevel(context.getInput());
        }

        @Override
        public State nextState() {
            return Profession;
        }
    },
    Profession {
        @Override
        public void enter(Context context) {
            sendMessage(context, "Оберіть спеціальність по якій шукаєте роботу:"+
                    "\r\n "+" - "+" Business Analyst " +
                    "\r\n"+" - "+" C/C++ "+
                    "\r\n "+" - "+" C#/.Net "+
                    "\r\n "+" - "+ " DevOps "+
                    "\r\n "+" - "+" Front End "+
                    "\r\n "+" - "+" Java "+
                    "\r\n "+" - "+" Project Management "+
                    "\r\n "+" - "+" QA "+
                    "\r\n "+" - "+" SAP "+
                    "\r\n "+" - "+" Solution Architects "+
                    "\r\n "+" - "+" Telecom ");
        }

        @Override
        public  void handleInput(Context context) {
            context.getUser().setProfession(context.getInput());
        }
        @Override
        public State nextState() {
            return Final;
        }
    },
    Final(false) {
        public void enter(Context context) {
            sendMessage(context, "Знайдені наступні вакансії:");
        }



        @Override
        public State nextState(){
            return Start;
        }

    };

    private static State[] states;
    private final boolean inputNeeded;


    State() {
        this.inputNeeded = true;
    }

    State(boolean inputNeeded) {
        this.inputNeeded = inputNeeded;
    }

    public static State getInitialState() {
        return byId(0);
    }

    public static State byId(int id) {

        if(states ==null)

    {
        states = State.values();
    }
        return states[id];

    }
    protected void sendMessage(Context context, String text){
            SendMessage message=new SendMessage()
            .setChatId(context.getUser().getChatId()).setText(text);
            try{
            context.getBot().execute(message);
            }catch(TelegramApiException e){
            e.printStackTrace();
            }
            }
            public boolean isInputNeeded(){
                return inputNeeded;
    }
    public void handleInput(Context context) {
    }

    public abstract  void enter (Context context);
    public abstract State nextState();



}

