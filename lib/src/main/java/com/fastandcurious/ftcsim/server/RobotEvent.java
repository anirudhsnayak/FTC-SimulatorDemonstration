package com.fastandcurious.ftcsim.server;

public class RobotEvent {
        int eventId;
        String[]eventArgs;
        public RobotEvent(RobotAction action, String[] args){
            eventId=action.ordinal();
            eventArgs=args;
        }
        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder(eventId + ";");
            for (String arg: eventArgs)
            {
                builder.append(arg+";");
            }
            return builder.toString();
        }
}
