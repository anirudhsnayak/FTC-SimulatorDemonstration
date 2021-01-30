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

public interface BNO055IMU {
    boolean initialize(@NonNull Parameters parameters);
    /**
     * Shut down the sensor. This doesn't do anything in the hardware device itself, but rather
     * shuts down any resources (threads, etc) that we use to communicate with it. It is rare
     * that user code has a need to call this method.
     */
    void close();
    class Parameters implements Cloneable
    {
        /** the address at which the sensor resides on the I2C bus.  */
        public Object          i2cAddr             = null;

        /** the mode we wish to use the sensor in */
        public Object      mode                = null;

        /** whether to use the external or internal 32.768khz crystal. External crystal
         * use is recommended by the BNO055 specification. */
        public boolean          useExternalCrystal  = true;

        /** units in which temperature are measured. See Section 3.6.1 (p31) of the BNO055 specification */
        public Object        temperatureUnit     = null;
        /** units in which angles and angular rates are measured. See Section 3.6.1 (p31) of the BNO055 specification */
        public AngleUnit        angleUnit           = AngleUnit.RADIANS;
        /** units in which accelerations are measured. See Section 3.6.1 (p31) of the BNO055 specification */
        public AccelUnit        accelUnit           = AccelUnit.METERS_PERSEC_PERSEC;
        /** directional convention for measuring pitch angles. See Section 3.6.1 (p31) of the BNO055 specification */
        public Object       pitchMode           = null;    // Section 3.6.2

        /** accelerometer range. See Section 3.5.2 (p27) and Table 3-4 (p21) of the BNO055 specification */
        public Object       accelRange          = null;
        /** accelerometer bandwidth. See Section 3.5.2 (p27) and Table 3-4 (p21) of the BNO055 specification */
        public Object  accelBandwidth      = null;
        /** accelerometer power mode. See Section 3.5.2 (p27) and Section 4.2.2 (p77) of the BNO055 specification */
        public Object  accelPowerMode      = null;

        /** gyroscope range. See Section 3.5.2 (p27) and Table 3-4 (p21) of the BNO055 specification */
        public Object        gyroRange           = null;
        /** gyroscope bandwidth. See Section 3.5.2 (p27) and Table 3-4 (p21) of the BNO055 specification */
        public Object     gyroBandwidth       = null;
        /** gyroscope power mode. See Section 3.5.2 (p27) and Section 4.4.4 (p78) of the BNO055 specification */
        public Object    gyroPowerMode       = null;

        /** magnetometer data rate. See Section 3.5.3 (p27) and Section 4.4.3 (p77) of the BNO055 specification */
        public Object           magRate             = null;
        /** magnetometer op mode. See Section 3.5.3 (p27) and Section 4.4.3 (p77) of the BNO055 specification */
        public Object        magOpMode           = null;
        /** magnetometer power mode. See Section 3.5.3 (p27) and Section 4.4.3 (p77) of the BNO055 specification */
        public Object      magPowerMode        = null;

        /** Calibration data with which the BNO055 should be initialized. If calibrationData is non-null,
         * it is used. Otherwise, if calibrationDataFile is non-null, it is used. Otherwise, only the default
         * automatic calibration of the IMU is used*/
        public String           calibrationDataFile = null;

        /** the algorithm to use for integrating acceleration to produce velocity and position.
         * If not specified, a simple but not especially effective internal algorithm will be used.
        public Object accelerationIntegrationAlgorithm = null;

        /** debugging aid: enable logging for this device? */
        public boolean          loggingEnabled      = false;
        /** debugging aid: the logging tag to use when logging */
        public String           loggingTag          = "AdaFruitIMU";

        public Parameters clone()
        {
            try {
                Parameters result = (Parameters)super.clone();
                return result;
            }
            catch (CloneNotSupportedException e)
            {
                throw new RuntimeException("internal error: Parameters can't be cloned");
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    // Reading sensor output
    //----------------------------------------------------------------------------------------------

    /** Returns the absolute orientation of the sensor as a set three angles
     * @see #getQuaternionOrientation()
     * @return the absolute orientation of the sensor
     * @see Orientation
     * @see #getAngularOrientation(AxesReference, AxesOrder, AngleUnit)
     */
    Orientation getAngularOrientation();

    /**
     * Returns the absolute orientation of the sensor as a set three angles with indicated parameters.
     * @param reference the axes reference in which the result will be expressed
     * @param order     the axes order in which the result will be expressed
     * @param angleUnit the angle units in which the result will be expressed
     * @return the absolute orientation of the sensor
     * @see Orientation
     * @see #getAngularOrientation()
     */
    Orientation getAngularOrientation(AxesReference reference, AxesOrder order, AngleUnit angleUnit) throws Exception;

    /**
     * Returns the overall acceleration experienced by the sensor. This is composed of
     * a component due to the movement of the sensor and a component due to the force of gravity.
     * @return  the overall acceleration vector experienced by the sensor
     * @see #getLinearAcceleration()
     * @see #getGravity()
     */
    Acceleration getOverallAcceleration() throws Exception;

    /**
     * Returns the rate of change of the absolute orientation of the sensor.
     * @return the rate at which the orientation of the sensor is changing.
     * @see #getAngularOrientation()
     */
    AngularVelocity getAngularVelocity() throws Exception;

    /**
     * Returns the acceleration experienced by the sensor due to the movement of the sensor.
     * @return  the acceleration vector of the sensor due to its movement
     * @see #getOverallAcceleration()
     * @see #getGravity()
     */
    Acceleration getLinearAcceleration() throws Exception;

    /**
     * Returns the direction of the force of gravity relative to the sensor.
     * @return  the acceleration vector of gravity relative to the sensor
     * @see #getOverallAcceleration()
     * @see #getLinearAcceleration()
     */
    Acceleration getGravity() throws Exception;

    /**
     * Returns the current temperature.
     * @return  the current temperature
     */
    default Object getTemperature() throws Exception
    {throw new Exception("getTemperature is not supported");}

    /**
     * Returns the magnetic field strength experienced by the sensor. See Section 3.6.5.2 of
     * the BNO055 specification.
     * @return  the magnetic field strength experienced by the sensor
     */
    default Object getMagneticFieldStrength() throws Exception
    {throw new Exception("getMagneticFieldStrength is not supported");}

    /** Returns the absolute orientation of the sensor as a quaternion.
     * @see #getAngularOrientation()
     *
     * @return  the absolute orientation of the sensor
     */
    default Object getQuaternionOrientation() throws Exception
    {throw new Exception("getQuaternionOrientation is not supported");}

    //----------------------------------------------------------------------------------------------
    // Position and velocity management
    //----------------------------------------------------------------------------------------------

    /**
     * Returns the current position of the sensor as calculated by doubly integrating the observed
     * sensor accelerations.
     * @return  the current position of the sensor.
     */
    Position getPosition() throws Exception;

    /**
     * Returns the current velocity of the sensor as calculated by integrating the observed
     * sensor accelerations.
     * @return  the current velocity of the sensor
     */
    Velocity getVelocity() throws Exception;

    /**
     * Returns the last observed acceleration of the sensor. Note that this does not communicate
     * with the sensor, but rather returns the most recent value reported to the acceleration
     * integration algorithm.
     * @return  the last observed acceleration of the sensor
     * @see #getLinearAcceleration()
     */
    Acceleration getAcceleration() throws Exception;

    enum AccelUnit { METERS_PERSEC_PERSEC(0), MILLI_EARTH_GRAVITY(1); public final byte bVal; AccelUnit(int i) { bVal =(byte)i; }}
}
