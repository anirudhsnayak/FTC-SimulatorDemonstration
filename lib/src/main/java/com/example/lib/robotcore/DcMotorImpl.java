/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.example.lib.robotcore;

import com.example.lib.server.RobotAction;
import com.example.lib.server.RobotEvent;
import com.example.lib.server.RobotServer;


public class DcMotorImpl implements DcMotor {
    final double maxMotorSpeed = 360.0; // Deg/Sec

    String Tag;
    ZeroPowerBehavior powerBehavior;
    RunMode mode;
    Direction direction = Direction.FORWARD;
    long zeroIndex = 0;
    double Power;

    public DcMotorImpl(String tag){
        Tag=tag;
    }
    public void setPower(double power){
        Power = power;
        if (direction==Direction.FORWARD) {
            RobotServer.SendCommand(new RobotEvent(RobotAction.SET_POWER,
                    new String[]{Tag, "100000.0", String.valueOf(GetSpeed(power))}));
        }else{
            RobotServer.SendCommand(new RobotEvent(RobotAction.SET_POWER,
                    new String[]{Tag, "100000.0", String.valueOf(GetSpeed(-power))}));
        }
        //RobotController.AddEvent(RobotAction.SET_POWER, new String[]{Tag, "100000.0", String.valueOf(power)});
        //need to make some implementation that takes in the power as a value [-1, 1], based on the value entered in the robot builder
        //but for now the spin force is virtually infinite and the speed is the power
    }
    public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior){
        powerBehavior = zeroPowerBehavior;
    }
    public ZeroPowerBehavior getZeroPowerBehavior(){
        return powerBehavior;
    }
    public int getCurrentPosition(){
        if (!DeviceMapping.EncoderValues.containsKey(Tag)){
            return 0;
        }
        int currentPosition = (int) (DeviceMapping.EncoderValues.get(Tag)-zeroIndex);
        if (direction==Direction.FORWARD) {
            return currentPosition;
        }
        return -currentPosition;
    }
    public void setMode(RunMode runMode){
        mode = runMode;
        if (mode == RunMode.STOP_AND_RESET_ENCODER){
            zeroIndex = DeviceMapping.EncoderValues.get(Tag);
        }
    }
    public RunMode getMode(){
        return mode;
    }
    public void setDirection(Direction direction_){
        direction= direction_;
    }
    public Direction getDirection(){
        return direction;
    }
    public double getPower(){
        return Power;
    }

    //put this in utils if possible
    double GetSpeed (double power){
        return Math.max(Math.min(power, 1.0), -1.0) * maxMotorSpeed;
    }
}
