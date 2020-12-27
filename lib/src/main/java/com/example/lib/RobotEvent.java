package com.example.lib;

public class RobotEvent {
        int eventId;
        String[]eventArgs;
        public RobotEvent(RobotAction action, String[] args){
            eventId=action.ordinal();
            eventArgs=args;
        }
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
