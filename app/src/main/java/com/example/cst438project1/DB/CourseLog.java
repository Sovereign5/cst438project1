package com.example.cst438project1.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cst438project1.DB.CourseDatabase;

import java.util.Objects;

/* I'll be honest, this file might be temporary while I
* learn Room and how it works
* */

@Entity(tableName = CourseDatabase.COURSELOG_TABLE)
public class CourseLog {

    @PrimaryKey(autoGenerate = true)
    private int mCourseID;

    private String mInstructor;
    private String mTitle;
    private String mDescription;
    private String mStartDate;
    private String mEndDate;

    // Constructor
    public CourseLog(String instructor, String title, String description, String startDate,
                     String endDate)
    {
        /** JavaDoc for CourseLog.java
         * @param mInstructor is for the name of the course's instructor
         * @param mTitle is for the course's title
         * @param mDescription is for the course's description
         * @param mStartDate is for the course start date
         * @param mEndDate is for the course end date
         * @param mCourseID is for the course's ID, which is autogeneratem
         */
        mInstructor = instructor;
        mTitle = title;
        mDescription = description;
        mStartDate = startDate;
        mEndDate = endDate;
    }

    /** JavaDoc for Getters and Setters
     * ALL The getters and setters for variables used by database
     * Getter for CourseID added
     */
    public String getmInstructor() {
        return mInstructor;
    }

    public void setmInstructor(String mInstructor) {
        this.mInstructor = mInstructor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    /** JavaDoc for equals, hashcode, and toString
     * I'm not sure if we'll need these, but I generated them just in case
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseLog courseLog = (CourseLog) o;
        return mCourseID == courseLog.mCourseID &&
                Objects.equals(mInstructor, courseLog.mInstructor) &&
                Objects.equals(mTitle, courseLog.mTitle) &&
                Objects.equals(mDescription, courseLog.mDescription) &&
                Objects.equals(mStartDate, courseLog.mStartDate) &&
                Objects.equals(mEndDate, courseLog.mEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mCourseID, mInstructor, mTitle, mDescription, mStartDate, mEndDate);
    }

    @Override
    public String toString() {
        return "CourseLog{" +
                "mCourseID=" + mCourseID +
                ", mInstructor='" + mInstructor + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mStartDate='" + mStartDate + '\'' +
                ", mEndDate='" + mEndDate + '\'' +
                '}';
    }
}
