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
package com.fastandcurious.ftcsim.robotcore;

import androidx.annotation.NonNull;

public class BNO055IMUImpl implements BNO055IMU {
    String Tag;
    Parameters params;
    public BNO055IMUImpl(String tag){ Tag=tag; }
    @Override
    public boolean initialize(@NonNull Parameters parameters) {
        params=parameters;
        return true;
    }
    public void close(){}
    /** Returns the absolute orientation of the sensor as a set three angles
     * @see #getQuaternionOrientation()
     * @return the absolute orientation of the sensor
     * @see Orientation
     * @see #getAngularOrientation(AxesReference, AxesOrder, AngleUnit)
     */
    public Orientation getAngularOrientation(){
        Double[] transform = DeviceMapping.IMUValues.get(Tag);
        if (transform == null){
            return new Orientation();
        }
        Orientation angularOrientation = new Orientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES,
                (float)(double)transform[3], (float)(double)transform[4], (float)(double)transform[5], 0);
        angularOrientation = angularOrientation.toAngleUnit(AngleUnit.RADIANS);
        return angularOrientation;
    }
    /**
     * Returns the absolute orientation of the sensor as a set three angles with indicated parameters.
     * @param reference the axes reference in which the result will be expressed
     * @param order     the axes order in which the result will be expressed
     * @param angleUnit the angle units in which the result will be expressed
     * @return the absolute orientation of the sensor
     * @see Orientation
     * @see #getAngularOrientation()
     */
    public Orientation getAngularOrientation(AxesReference reference, AxesOrder order, AngleUnit angleUnit) throws Exception{
        throw new Exception("getAngularOrientation(AxesReference reference, AxesOrder order, AngleUnit angleUnit) is only supported with no parameters");
    }
    public Acceleration getOverallAcceleration() throws Exception{
        throw new Exception("getOverallAcceleration is not supported");
    }
    public AngularVelocity getAngularVelocity() throws Exception{
        throw new Exception("getAngularVelocity is not supported");
    }
    public Acceleration getLinearAcceleration() throws Exception{
        throw new Exception("getLinearAcceleration is not supported");
    }
    public Acceleration getGravity() throws Exception{
        throw new Exception("getGravity is not supported");
    }
    public Position getPosition() throws Exception{
        throw new Exception("getGravity is not supported");
    }
    public Velocity getVelocity() throws Exception{
        throw new Exception("getVelocity is not supported");
    }
    public Acceleration getAcceleration() throws Exception{
        throw new Exception("getAcceleration is not supported");
    }
}
