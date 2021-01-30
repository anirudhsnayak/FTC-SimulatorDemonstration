package com.fastandcurious.ftcsim.server;

import com.fastandcurious.ftcsim.robotcore.Autonomous;
import com.fastandcurious.ftcsim.robotcore.LinearOpMode;

import org.reflections.Reflections;

import java.util.Set;

public class RobotController extends Thread{

    public void run(){
        try {
            getLinearOpMode().runOpMode();
            RobotServer.SendCommand(new RobotEvent(RobotAction.IDLING, new String[]{}));
        } catch (InterruptedException | IllegalAccessException | InstantiationException e) {
            System.out.println(e.toString());
        }
    }
    public LinearOpMode getLinearOpMode () throws IllegalAccessException, InstantiationException {
        Set<Class<?>> set = new Reflections("com.fastandcurious.ftcsim").getTypesAnnotatedWith(Autonomous.class);
        for (Class<?> annotatedClass: set) {
            return (LinearOpMode) annotatedClass.newInstance();
        }
        return null;
    }
}
