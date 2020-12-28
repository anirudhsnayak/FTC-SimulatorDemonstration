/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.example.lib.robotcore;


import com.example.lib.RobotAction;
import com.example.lib.RobotController;
import com.example.lib.RobotEvent;
import com.example.lib.RobotServer;

/**
 * {@link DcMotorImplEx} is a motor that supports the {@link DcMotorEx} interface in addition
 * to simply {@link DcMotor}.
 */
@SuppressWarnings("WeakerAccess")
public class DcMotorImplEx extends DcMotorImpl implements DcMotorEx
{
    double Velocity;

    public DcMotorImplEx(String tag) {
        super(tag);
    }

    public void setVelocity(double velocity){
        Velocity = velocity;
        RobotServer.SendCommand(new RobotEvent(RobotAction.SET_POWER, new String[]{Tag, "100000.0", String.valueOf(velocity)}));
    }
    public void setVelocity(double velocity, AngleUnit angleUnit){ //need to implement the angle unit with the velocity
        Velocity = velocity;
        RobotServer.SendCommand(new RobotEvent(RobotAction.SET_POWER, new String[]{Tag, "100000.0", String.valueOf(velocity)}));
   }
    public double getVelocity(){
        return Velocity;
    }
    public double getVelocity (AngleUnit angleUnit){
        return Velocity; //express in angleUnit
    }

}
