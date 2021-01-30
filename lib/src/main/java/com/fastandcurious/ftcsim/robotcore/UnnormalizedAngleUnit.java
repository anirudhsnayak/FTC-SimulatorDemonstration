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

@SuppressWarnings("WeakerAccess")
public enum UnnormalizedAngleUnit //Done
{
    DEGREES(0), RADIANS(1);
    public final byte bVal;

    UnnormalizedAngleUnit(int i)
    {
        bVal = (byte) i;
    }

    //----------------------------------------------------------------------------------------------
    // Primitive operations
    //----------------------------------------------------------------------------------------------

    public double fromDegrees(double degrees)
    {
        switch (this)
        {
            default:
            case RADIANS:  return (degrees / 180.0 * Math.PI);
            case DEGREES:  return (degrees);
        }
    }

    public float fromDegrees(float degrees)
    {
        switch (this)
        {
            default:
            case RADIANS:  return (degrees / 180.0f * AngleUnit.Pif);
            case DEGREES:  return (degrees);
        }
    }

    public double fromRadians(double radians)
    {
        switch (this)
        {
            default:
            case RADIANS:  return (radians);
            case DEGREES:  return (radians / Math.PI * 180.0);
        }
    }

    public float fromRadians(float radians)
    {
        switch (this)
        {
            default:
            case RADIANS:  return (radians);
            case DEGREES:  return (radians / AngleUnit.Pif * 180.0f);
        }
    }

    public double fromUnit(UnnormalizedAngleUnit them, double theirs)
    {
        switch (them)
        {
            default:
            case RADIANS:  return this.fromRadians(theirs);
            case DEGREES:  return this.fromDegrees(theirs);
        }
    }

    public float fromUnit(UnnormalizedAngleUnit them, float theirs)
    {
        switch (them)
        {
            default:
            case RADIANS:  return this.fromRadians(theirs);
            case DEGREES:  return this.fromDegrees(theirs);
        }
    }

    //----------------------------------------------------------------------------------------------
    // Derived operations
    //----------------------------------------------------------------------------------------------

    public double toDegrees(double inOurUnits)
    {
        switch (this)
        {
            default:
            case RADIANS:      return DEGREES.fromRadians(inOurUnits);
            case DEGREES:      return DEGREES.fromDegrees(inOurUnits);
        }
    }

    public float toDegrees(float inOurUnits)
    {
        switch (this)
        {
            default:
            case RADIANS:      return DEGREES.fromRadians(inOurUnits);
            case DEGREES:      return DEGREES.fromDegrees(inOurUnits);
        }
    }

    public double toRadians(double inOurUnits)
    {
        switch (this)
        {
            default:
            case RADIANS:      return RADIANS.fromRadians(inOurUnits);
            case DEGREES:      return RADIANS.fromDegrees(inOurUnits);
        }
    }

    public float toRadians(float inOurUnits)
    {
        switch (this)
        {
            default:
            case RADIANS:      return RADIANS.fromRadians(inOurUnits);
            case DEGREES:      return RADIANS.fromDegrees(inOurUnits);
        }
    }

    //----------------------------------------------------------------------------------------------
    // Normalization
    //----------------------------------------------------------------------------------------------

    public AngleUnit getNormalized()
    {
        switch (this)
        {
            default:
            case RADIANS:  return AngleUnit.RADIANS;
            case DEGREES:  return AngleUnit.DEGREES;
        }
    }
}
