package com.example.lib.server;

public class RobotEvent {
        int eventId;
        String[]eventArgs;
        public RobotEvent(RobotAction action, String[] args){
            eventId=action.ordinal();
            eventArgs=args;
        }
        @Override
        public String toString(){
            String str = "";
            str += eventId + ";";
            for (String arg: eventArgs)
            {
                str+= arg + ";";
            }
            return (str);
        }
}
