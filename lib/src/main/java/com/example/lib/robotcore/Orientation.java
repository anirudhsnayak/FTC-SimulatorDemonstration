
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
 * Instances of {@link Orientation} represent a rotated stance in three-dimensional space
 * by way of a set of three successive rotations.
 *
 * <p>There are several ways that a particular orientation in three-space can be represented.
 * One way is by specifying a (unit) directional vector about which the orientation is to occur,
 * together with a rotation angle about that axis. This representation is unique up to the sign of the
 * direction and angle; that is a rotation {@code a} about a vector {@code v} produces the same
 * rotation as a rotation {@code -a} about the vector {@code -v}. While this manner of specifying a
 * rotation is easy to visualize if the vector in question is one of the cardinal axes (ie: X,Y, or Z),
 * many find it more difficult to visualize more complex rotations in this manner.</p>
 *
 * <p>An alternative, more common, way to represent a particular orientation in three-space is by means
 * of indicating three angles of rotation about three successive axes. You might for example be familiar
 * with the notions of heading, elevation, and bank angles for aircraft. Unfortunately, there are 24
 * different yet equivalent ways that a set of three rotational angles about three axes can represent
 * the same effective rotation. As might be expected, this can be the source of much confusion. The
 * 24 different representations break down as follows.</p>
 *
 * <p>First is the matter of the axes reference: is the coordinate system in which the referred-to rotational
 * axes reside a coordinate system that moves with (and so remains fixed relative to) the object being rotated,
 * or do the axes remain fixed relative to the world around the object and are unaffected by the
 * object's rotational motion? The former situation is referred to as an {@link AxesReference#INTRINSIC intrinsic}
 * reference perspective while the latter is an {@link AxesReference#EXTRINSIC extrinsic} perspective.
 * Both points of view are equally valid methodologies, but one or the other may be more understandable
 * or useful in a given application situation.
 * </p>
 *
 * <p>The extrinsic-vs-intrinsic difference accounts for a factor of two in our list of 24 different
 * representations. The remaining factor of 12 breaks down into whether the three rotations all use
 * different axes (and so are a permutation of X, Y, and Z, of which there are six in number), or whether
 * the first and last axes are the same and the middle one different (e.g. Z-Y-Z); this has three
 * choices for the first axis (which is also used for the last) and two remaining choices for the
 * second axis, for a total, again, of six possibilities. The geometry of three-space is such that these
 * twelve choices are the only distinct representational possibilities. As with the extrinsic-vs-
 * intrinsic difference, all twelve of these axis {@link AxesOrder order}s are equally valid ways of
 * indicating orientation, but in any given application, one way may be more useful or easier to
 * understand than another.
 * </p>
 *
 * <p>Even on top of all that, for a given intrinsic-vs-extrinsic distinction, and a given axes
 * ordering, there are two sets of angle rotation that will produce the same orientation. For example,
 * an extrinsic, XZX rotation of (in degrees) 90, -90, 0 is equivalent to an extrinsic, XZX rotation
 * of -90, 90, -180.</p>
 *
 * <p>As was mentioned, much confusion has historically arisen from talking about an orientation as
 * a set of three angles without also clearly indicating which of the 24 representational possibilities
 * one is working within. One aim of {@link Orientation} is to reduce that confusion by being explicitly
 * clear about this issue: an {@link Orientation} always carries along with it the indication of the
 * {@link AxesReference} and {@link AxesOrder} of the orientation. Methods are provided for converting
 * an {@link Orientation} to and from its associated rotation matrix.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Euler_angles">Euler Angles</a>
 * @see <a href="https://en.wikipedia.org/wiki/Axis%E2%80%93angle_representation">Axis-Angle Representation</a>
 * @see <a href="https://en.wikipedia.org/wiki/Axes_conventions">Axes Conventions</a>
 * @see <a href="https://en.wikipedia.org/wiki/Rotation_matrix">Rotation Matrix</a>
 */
public class Orientation
{
    //----------------------------------------------------------------------------------------------
    // State
    //----------------------------------------------------------------------------------------------

    /**
     * whether we have extrinsic or intrinsic rotations
     *
     * @see #axesOrder
     */
    public AxesReference axesReference;

    /**
     * the order of axes around which our three rotations occur
     *
     * @see #axesReference
     */
    public AxesOrder axesOrder;

    /**
     * the unit in which the angles are expressed
     */
    public AngleUnit angleUnit;

    /**
     * the chronologically first rotation made in the {@link AxesOrder}
     */
    public float firstAngle;
    /**
     * the chronologically second rotation made in the {@link AxesOrder}
     */
    public float secondAngle;
    /**
     * the chronologically third rotation made in the {@link AxesOrder}
     */
    public float thirdAngle;

    /**
     * the time on the System.nanoTime() clock at which the data was acquired. If no
     * timestamp is associated with this particular set of data, this value is zero.
     */
    public long acquisitionTime;

    //----------------------------------------------------------------------------------------------
    // Construction
    //----------------------------------------------------------------------------------------------

    public Orientation()
    {
        this(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS, 0, 0, 0, 0);
    }

    public Orientation(AxesReference axesReference, AxesOrder axesOrder, AngleUnit angleUnit, float firstAngle, float secondAngle, float thirdAngle, long acquisitionTime)
    {
        this.axesReference = axesReference;
        this.axesOrder = axesOrder;
        this.angleUnit = angleUnit;
        this.firstAngle = firstAngle;
        this.secondAngle = secondAngle;
        this.thirdAngle = thirdAngle;
        this.acquisitionTime = acquisitionTime;
    }

    /**
     * Converts this {@link Orientation} to one with the indicated angular units.
     *
     * @param angleUnit the units to use in the returned [@link Orientation}
     * @return a new [@link Orientation} with the same data but in the indicated units
     */
    public Orientation toAngleUnit(AngleUnit angleUnit)
    {
        if (angleUnit != this.angleUnit)
        {
            return new Orientation(this.axesReference, this.axesOrder, angleUnit,
                    angleUnit.fromUnit(this.angleUnit, firstAngle),
                    angleUnit.fromUnit(this.angleUnit, secondAngle),
                    angleUnit.fromUnit(this.angleUnit, thirdAngle),
                    this.acquisitionTime);
        }
        else
            return this;
    }

    //----------------------------------------------------------------------------------------------
    // Accessing
    //----------------------------------------------------------------------------------------------

    @Override public String toString()
    {
        if (this.angleUnit == AngleUnit.DEGREES)
            return String.format("{%s %s %.0f %.0f %.0f}", this.axesReference.toString(), this.axesOrder.toString(), this.firstAngle, this.secondAngle, this.thirdAngle);
        else
            return String.format("{%s %s %.3f %.3f %.3f}", this.axesReference.toString(), this.axesOrder.toString(), this.firstAngle, this.secondAngle, this.thirdAngle);
    }

}













































