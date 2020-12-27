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

/**
 * DcMotor interface provides access to full-featured motor functionality.
 */
public interface DcMotor extends DcMotorSimple
{
    default MotorConfigurationType getMotorType(){return null;};

    default void setMotorType(MotorConfigurationType motorType){}; //Does nothing

    @SuppressWarnings("DcMotorController is not supported")
    default DcMotorController getController(){return null;}

    @SuppressWarnings("Ports are not supported")
    default int getPortNumber(){return 0;}


    enum ZeroPowerBehavior
    {
        UNKNOWN,
        BRAKE,
        FLOAT
    }

    void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior);

    ZeroPowerBehavior getZeroPowerBehavior();

    @SuppressWarnings("setPowerFloat is not supported")
    @Deprecated default void setPowerFloat(){};

    @SuppressWarnings("getPowerFloat is not supported")
    default boolean getPowerFloat(){return false;}

    @SuppressWarnings("setTargetPosition is not supported")
    default void setTargetPosition(int position){};

    @SuppressWarnings("getTargetPosition is not supported")
    default int getTargetPosition(){return 0;}

    @SuppressWarnings("isBusy is not supported")
    default boolean isBusy(){return false;}

    int getCurrentPosition();

    enum RunMode
    {

        RUN_WITHOUT_ENCODER,

        RUN_USING_ENCODER,

        RUN_TO_POSITION,

        STOP_AND_RESET_ENCODER,

        /** @deprecated Use {@link #RUN_WITHOUT_ENCODER} instead */
        @Deprecated RUN_WITHOUT_ENCODERS,

        /** @deprecated Use {@link #RUN_USING_ENCODER} instead */
        @Deprecated RUN_USING_ENCODERS,

        /** @deprecated Use {@link #STOP_AND_RESET_ENCODER} instead */
        @Deprecated RESET_ENCODERS;

        /** Returns the new new constant corresponding to old constant names.
         * @deprecated Replace use of old constants with new */
        @Deprecated
        public RunMode migrate()
        {
            switch (this)
            {
                case RUN_WITHOUT_ENCODERS: return RUN_WITHOUT_ENCODER;
                case RUN_USING_ENCODERS: return RUN_USING_ENCODER;
                case RESET_ENCODERS: return STOP_AND_RESET_ENCODER;
                default: return this;
            }
        }

        public boolean isPIDMode()
        {
            return this==RUN_USING_ENCODER || this==RUN_USING_ENCODERS || this==RUN_TO_POSITION;
        }
    }

    void setMode(RunMode mode);

    RunMode getMode();
}
