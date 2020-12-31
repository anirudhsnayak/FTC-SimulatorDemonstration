package com.example.lib;

import com.example.lib.robotcore.Autonomous;

import org.reflections.Reflections;

import java.util.Set;

public class RobotController extends Thread{

    public void run(){
        try {
            getLinearOpMode().runOpMode();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
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
