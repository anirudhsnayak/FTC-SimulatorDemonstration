package com.example.lib.server;

import com.example.lib.robotcore.Autonomous;
import com.example.lib.robotcore.LinearOpMode;

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
        Set<Class<?>> set = new Reflections("com.example.lib").getTypesAnnotatedWith(Autonomous.class);
        for (Class<?> annotatedClass: set) {
            return (LinearOpMode) annotatedClass.newInstance();
        }
        return null;
    }
}
