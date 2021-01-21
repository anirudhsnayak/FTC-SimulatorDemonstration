package com.example.lib.robotcore;

import androidx.annotation.NonNull;

import java.util.Vector;

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
        Orientation angularOrientation = new Orientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, (float)(double)transform[3], (float)(double)transform[4], (float)(double)transform[5], 0);
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
        throw new Exception("getAngularOrientation is only supported with no parameters");
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
